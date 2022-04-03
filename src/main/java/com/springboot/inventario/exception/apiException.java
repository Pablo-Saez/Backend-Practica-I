package com.springboot.inventario.exception;

import org.springframework.http.HttpStatus;

public class apiException extends RuntimeException{
    private HttpStatus status;
    private String message;

    public apiException(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }

    public apiException(String message, HttpStatus status, String message1) {
        super(message);
        this.status = status;
        this.message = message1;
    }

    public HttpStatus getStatus() {
        return status;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
