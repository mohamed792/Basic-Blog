package com.example.basicblog.exceptions;

import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpStatus;

import java.security.PrivateKey;
import java.security.Timestamp;
import java.time.LocalDateTime;

public class ApiError {

    private HttpStatus status;
    private String message;
    private LocalDateTime localDateTime;

    public ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
        localDateTime = LocalDateTime.now();
    }

    public HttpStatus getStatus() {
        return status;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public void setStatus(HttpStatus status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
