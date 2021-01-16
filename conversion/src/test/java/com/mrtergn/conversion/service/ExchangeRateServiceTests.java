package com.mrtergn.conversion.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.mrtergn.conversion.model.entity.ExchangeRate;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Objects;

@SpringBootTest
public class ExchangeRateServiceTests {

    @MockBean
    private RestTemplate restTemplate;

    @Autowired
    private ExchangeRateService exchangeRateService;

    static String base = "TRY";
    static String symbol = "USD";
    static double rate = 7;

    static ResponseEntity<ExchangeRate> defaultExchangeRateResponse;

    @BeforeAll
    public static void init() {
        ExchangeRate exchangeRate = new ExchangeRate();
        exchangeRate.setBase(base);
        exchangeRate.setSymbol(symbol);
        exchangeRate.setRate(rate);

        defaultExchangeRateResponse = ResponseEntity.status(HttpStatus.OK).body(exchangeRate);
    }

    @Test
    @DisplayName("GET exchangeRate success")
    public void getExchangeRateSuccess() throws URISyntaxException, MalformedURLException, JsonProcessingException {
        URI uri = new URIBuilder("http://exchange-rate/exchangeRate")
                .addParameter("base", base)
                .addParameter("symbol", symbol)
                .build()
                .toURL()
                .toURI();

        Mockito.when(restTemplate.exchange(uri, HttpMethod.GET, null, ExchangeRate.class))
                .thenReturn(defaultExchangeRateResponse);

        ExchangeRate exchangeRateResponse = exchangeRateService.getExchangeRate(base, symbol);

        Assertions.assertEquals(Objects.requireNonNull(exchangeRateResponse).getBase(), base);
        Assertions.assertEquals(Objects.requireNonNull(exchangeRateResponse).getSymbol(), symbol);
        Assertions.assertEquals(Objects.requireNonNull(exchangeRateResponse).getRate(), rate);
    }

}
