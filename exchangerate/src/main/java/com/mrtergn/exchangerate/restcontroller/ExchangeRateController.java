package com.mrtergn.exchangerate.restcontroller;

import com.mrtergn.exchangerate.model.ModelCreator;
import com.mrtergn.exchangerate.model.entity.ExchangeRate;
import com.mrtergn.exchangerate.model.repsonse.BaseResponse;
import com.mrtergn.exchangerate.model.repsonse.RatesApiResponse;
import com.mrtergn.exchangerate.model.request.ExchangeRateRequest;
import com.mrtergn.exchangerate.service.IRatesApiService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.net.MalformedURLException;
import java.net.URISyntaxException;

@RestController
@Validated
@Api(value = "Exchange Rate API")
public class ExchangeRateController {

    @Autowired
    private IRatesApiService ratesApiService;

    @GetMapping(
            value = "/exchangeRate",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Get exchange rate of given symbol pair")
    public ResponseEntity<BaseResponse<ExchangeRate>> handleExchangeRate(@Valid ExchangeRateRequest exchangeRateRequest) throws MalformedURLException, URISyntaxException {
        RatesApiResponse ratesApiResponse = ratesApiService.getLatestExchange(exchangeRateRequest.getBase(), exchangeRateRequest.getSymbol());
        ExchangeRate exchangeRate = ModelCreator.createExchangeRate(exchangeRateRequest, ratesApiResponse);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(exchangeRate));
    }

}
