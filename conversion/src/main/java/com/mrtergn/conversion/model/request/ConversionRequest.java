package com.mrtergn.conversion.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "ConversionRequest model", description = "ConversionRequest model")
public class ConversionRequest {

    @NotNull
    @ApiModelProperty(value = "Amount of source symbol to convert", example = "1")
    private double amount;
    @NotNull
    @ApiModelProperty(value = "Source symbol", example = "TRY")
    private String source;
    @NotNull
    @ApiModelProperty(value = "Target symbol", example = "USD")
    private String target;

}
