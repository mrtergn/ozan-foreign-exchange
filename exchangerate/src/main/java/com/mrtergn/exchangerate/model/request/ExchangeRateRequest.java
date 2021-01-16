package com.mrtergn.exchangerate.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "ExchangeRateRequest model", description = "ExchangeRateRequest model")
public class ExchangeRateRequest {

    @NotNull
    @ApiModelProperty(value = "Base symbol to get exchange rate", example = "TRY")
    private String base;
    @NotNull
    @ApiModelProperty(value = "Target symbol to get exchange rate", example = "USD")
    private String symbol;

}
