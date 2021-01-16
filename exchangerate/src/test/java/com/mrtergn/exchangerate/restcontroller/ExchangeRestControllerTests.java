package com.mrtergn.exchangerate.restcontroller;

import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;
import com.mrtergn.exchangerate.service.RatesApiService;
import lombok.SneakyThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import java.util.HashMap;
import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.doReturn;
import static org.hamcrest.Matchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class ExchangeRestControllerTests {

    @MockBean
    private RatesApiService ratesApiService;

    @Autowired
    private MockMvc mockMvc;

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

    @SneakyThrows
    @Test
    @DisplayName("GET /exchangeRate success")
    public void getExchangeRateSuccess() {
        doReturn(defaultRatesApiResponse).when(ratesApiService).getLatestExchange(base, symbol);

        mockMvc.perform(get("/exchangeRate")
                .param("base", base)
                .param("symbol", symbol))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.base", is(base)))
                .andExpect(jsonPath("$.data.symbol", is(symbol)))
                .andExpect(jsonPath("$.data.rate", is(rates.get(symbol))));
    }

}
