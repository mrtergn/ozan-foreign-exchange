package com.mrtergn.exchangerate.model.repsonse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
public class ErrorResponse {

    private final Error error;

    public ErrorResponse(int code, String message) {
        this.error = new Error(code, message);
    }

    @AllArgsConstructor
    @Getter
    public static class Error {

        private final int code;
        private final String message;

    }

}
