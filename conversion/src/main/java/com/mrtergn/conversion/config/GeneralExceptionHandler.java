package com.mrtergn.conversion.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrtergn.conversion.model.response.ErrorResponse;
import com.mrtergn.conversion.service.LogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private LogService logService;

    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobal(Exception e) {
        logService.error(serviceName, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClient(RestClientException e) throws JsonProcessingException {
        HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) e;
        ErrorResponse errorResponse = objectMapper.readValue(httpStatusCodeException.getResponseBodyAsString(), ErrorResponse.class);

        logService.error(serviceName, errorResponse.getError().getMessage());

        return ResponseEntity.status(httpStatusCodeException.getStatusCode())
                .body(new ErrorResponse(errorResponse.getError().getCode(), errorResponse.getError().getMessage()));
    }

    @ExceptionHandler(BindException.class)
    public ResponseEntity<ErrorResponse> handleBind(BindException e) {
        String s = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).reduce("", String::concat);
        logService.error(serviceName, s);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                .body(new ErrorResponse(HttpStatus.BAD_REQUEST.value(), s));
    }
}