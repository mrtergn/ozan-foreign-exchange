package com.mrtergn.exchangerate.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@Service
public class LogService implements ILogService {

    @Autowired
    @Qualifier("loadBalancedRestTemplate")
    private RestTemplate restTemplate;

    public void error(String serviceName, String message) {
        try {
            URI uri = new URIBuilder("http://logger/log/error").build().toURL().toURI();

            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);

            Map<String, String> log = new HashMap<>();
            log.put("service", serviceName);
            log.put("message", message);

            restTemplate.postForObject(uri, new HttpEntity<>(log, headers), String.class);
        } catch (URISyntaxException | MalformedURLException e) {
            //nothing
        }
    }
}
