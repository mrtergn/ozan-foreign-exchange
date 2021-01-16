package com.mrtergn.exchangerate.service;

import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;
import org.apache.http.client.utils.URIBuilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@SpringBootTest
public class RatesApiServiceTests {

    @Autowired
    private RatesApiService ratesApiService;

    @MockBean
    @Qualifier("restTemplate")
    private RestTemplate restTemplate;

    static String base = "TRY";
    static String symbol = "USD";
    static Map<String, Double> rates = new HashMap<>() {{
        put("USD", 7d);
    }};

    static ResponseEntity<RatesApiResponse> defaultRatesApiResponse;

    @BeforeAll
    public static void init() {
        RatesApiResponse ratesApiResponse = new RatesApiResponse();
        ratesApiResponse.setBase(base);
        ratesApiResponse.setRates(rates);

        defaultRatesApiResponse = ResponseEntity.status(HttpStatus.OK).body(ratesApiResponse);
    }

    @Test
    @DisplayName("GET https://api.ratesapi.io/api/latest success")
    public void getLatestExchangeSuccess() throws MalformedURLException, URISyntaxException {
        URI uri = new URIBuilder("https://api.ratesapi.io/api/latest")
                .addParameter("base", base)
                .addParameter("symbols", symbol)
                .build()
                .toURL()
                .toURI();

        Mockito.when(restTemplate.exchange(uri, HttpMethod.GET, null, RatesApiResponse.class))
                .thenReturn(defaultRatesApiResponse);

        RatesApiResponse ratesApiResponse = ratesApiService.getLatestExchange(base, symbol);

        Assertions.assertSame(Objects.requireNonNull(ratesApiResponse).getBase(), base);
    }

}
