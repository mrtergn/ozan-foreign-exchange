package com.mrtergn.exchangerate.config;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mrtergn.exchangerate.model.repsonse.ErrorResponse;
import com.mrtergn.exchangerate.service.ILogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpStatusCodeException;
import org.springframework.web.client.RestClientException;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GeneralExceptionHandler {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ILogService logService;

    @Value("${spring.application.name}")
    private String serviceName;

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobal(Exception e) {
        logService.error(serviceName, e.getMessage());
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage()));
    }

    @ExceptionHandler(RestClientException.class)
    public ResponseEntity<ErrorResponse> handleRestClient(RestClientException e) throws JsonProcessingException {
        HttpStatusCodeException httpStatusCodeException = (HttpStatusCodeException) e;
        Map<String, String> result = objectMapper.readValue(httpStatusCodeException.getResponseBodyAsString(), HashMap.class);

        logService.error(serviceName, result.get("error"));

        return ResponseEntity.status(
                httpStatusCodeException.getStatusCode())
                .body(new ErrorResponse(httpStatusCodeException.getRawStatusCode(), result.get("error")));
    }
}