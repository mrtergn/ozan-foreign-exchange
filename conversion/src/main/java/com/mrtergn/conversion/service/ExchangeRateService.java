package com.mrtergn.conversion.service;

import com.mrtergn.conversion.model.entity.ExchangeRate;
import com.mrtergn.conversion.model.response.BaseResponse;
import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

@Service
public class ExchangeRateService implements IExchangeRateService{

    @Autowired
    private RestTemplate restTemplate;

    public ExchangeRate getExchangeRate(String base, String symbol) throws URISyntaxException, MalformedURLException {
        URI uri = new URIBuilder("http://exchange-rate/exchangeRate")
                .addParameter("base", base)
                .addParameter("symbol", symbol)
                .build()
                .toURL()
                .toURI();

        ResponseEntity<BaseResponse<ExchangeRate>> exchangeRate = restTemplate.exchange(uri, HttpMethod.GET, null, new ParameterizedTypeReference<>() {});

        return exchangeRate.getBody().getData();
    }

}
