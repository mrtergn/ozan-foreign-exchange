package com.mrtergn.conversion.model.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@ApiModel(value = "ConversionListRequest model", description = "ConversionListRequest model")
public class ConversionListRequest {

    @ApiModelProperty(value = "Conversion transaction id")
    private String transactionId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "Conversion transaction date", example = "2021-01-15")
    private Date transactionDate;
    @NotNull
    @ApiModelProperty(value = "Pagination page number", example = "1")
    private int pageNo;
    @NotNull
    @ApiModelProperty(value = "Pagination page size", example = "5")
    private int pageSize;

}
