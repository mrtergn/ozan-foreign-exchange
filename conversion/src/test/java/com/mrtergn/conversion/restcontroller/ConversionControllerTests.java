package com.mrtergn.conversion.restcontroller;

import com.mrtergn.conversion.model.entity.ExchangeRate;
import com.mrtergn.conversion.service.ExchangeRateService;
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

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.doReturn;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringBootTest
@AutoConfigureMockMvc
public class ConversionControllerTests {

    @MockBean
    private ExchangeRateService exchangeRateService;

    @Autowired
    private MockMvc mockMvc;

    static String base = "TRY";
    static String symbol = "USD";
    static double amount = 1;
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

    @SneakyThrows
    @Test
    @DisplayName("GET /conversion success")
    public void getConversionSuccess() {
        doReturn(defaultExchangeRateResponse).when(exchangeRateService).getExchangeRate(base, symbol);

        mockMvc.perform(get("/conversion")
                .param("amount", Double.toString(amount))
                .param("source", base)
                .param("target", symbol))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.data.source", is(base)))
                .andExpect(jsonPath("$.data.target", is(symbol)))
                .andExpect(jsonPath("$.data.amount", is(amount)))
                .andExpect(jsonPath("$.data.conversion", is(amount * rate)));
    }

}
