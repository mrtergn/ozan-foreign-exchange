package com.mrtergn.exchangerate.service;

import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class RatesApiService implements IRatesApiService {

    @Autowired
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    public RatesApiResponse getLatestExchange(String base, String symbol) throws URISyntaxException, MalformedURLException {
        URI uri = new URIBuilder("https://api.ratesapi.io/api/latest")
                .addParameter("base", base)
                .addParameter("symbols", symbol)
                .build()
                .toURL()
                .toURI();

        ResponseEntity<RatesApiResponse> ratesApiResponse = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return ratesApiResponse.getBody();
    }

}
