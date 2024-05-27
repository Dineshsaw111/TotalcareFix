package com.totalcarefix.exception;

import org.springframework.http.HttpStatus;

public class GlobalExceptionResponse {

    private String message;
    private HttpStatus httpStatus;

    public GlobalExceptionResponse(String message, HttpStatus httpStatus) {
        this.message = message;
        this.httpStatus = httpStatus;
    }

    public String getMessage() {
        return message;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

}
