package com.mrtergn.conversion.restcontroller;

import com.mrtergn.conversion.model.entity.Conversion;
import com.mrtergn.conversion.model.request.ConversionListRequest;
import com.mrtergn.conversion.model.response.BaseResponse;
import com.mrtergn.conversion.model.response.ErrorResponse;
import com.mrtergn.conversion.service.ConversionDbService;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.List;

@RestController
@Validated
public class ConversionListController {

    @Autowired
    private ConversionDbService conversionDbService;

    @GetMapping(
            value = "/conversion/list",
            produces = {MediaType.APPLICATION_JSON_VALUE}
    )
    @ApiOperation(value = "Get conversion log list with given transactionDate or transactionId")
    public ResponseEntity<?> handleConversionList(@Valid ConversionListRequest conversionListRequest) {
        List<Conversion> conversionList = null;
        if (StringUtils.isNotBlank(conversionListRequest.getTransactionId())) {
            conversionList = conversionDbService.findByTransactionId(conversionListRequest.getTransactionId());
        } else if (conversionListRequest.getTransactionDate() != null) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
            conversionList = conversionDbService.findByTransactionDate(
                    simpleDateFormat.format(conversionListRequest.getTransactionDate()),
                    conversionListRequest.getPageNo(),
                    conversionListRequest.getPageSize()
            );
        } else {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                    .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), "Missing parameter. transactionId or transactionDate must be provided"));
        }

        return ResponseEntity.status(HttpStatus.OK).body(new BaseResponse<>(conversionList));
    }

}
