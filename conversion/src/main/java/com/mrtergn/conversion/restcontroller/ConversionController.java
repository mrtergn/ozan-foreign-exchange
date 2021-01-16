package com.mrtergn.conversion.restcontroller;

import com.mrtergn.conversion.model.ModelCreator;
import com.mrtergn.conversion.model.entity.Conversion;
import com.mrtergn.conversion.model.entity.ExchangeRate;
import com.mrtergn.conversion.model.request.ConversionRequest;
import com.mrtergn.conversion.model.response.BaseResponse;
import com.mrtergn.conversion.service.ConversionDbService;
import com.mrtergn.conversion.service.ExchangeRateService;
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
public class ConversionController {

    @Autowired
    private ExchangeRateService exchangeRateService;

    @Autowired
    private ConversionDbService conversionDbService;

    @GetMapping(
            value = "/conversion",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Get conversion of given symbol pair and amount")
    public ResponseEntity<BaseResponse<Conversion>> handleConversion(@Valid ConversionRequest conversionRequest) throws URISyntaxException, MalformedURLException {
        ExchangeRate exchangeRate = exchangeRateService.getExchangeRate(conversionRequest.getSource(), conversionRequest.getTarget());
        Conversion conversion = ModelCreator.createConversion(conversionRequest, exchangeRate);
        conversionDbService.save(conversion);
        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(conversion));
    }

}
