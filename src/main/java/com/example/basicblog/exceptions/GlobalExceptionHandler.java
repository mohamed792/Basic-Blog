package com.example.basicblog.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<ApiError> handleMethodArgumentTypeMismatchException(MethodArgumentTypeMismatchException exception) {
        String message = String.format("required value for field ( %s ) is ( %s ) but you provide ( %s )",
                exception.getName(),
                exception.getRequiredType().getCanonicalName(),
                exception.getValue().getClass().getSimpleName() + " value \" " + exception.getValue().toString() + "\"");
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        ResponseEntity<ApiError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiError> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        String message = exception.getBindingResult().getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage).toList().toString();
        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, message);
        ResponseEntity<ApiError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }


    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiError> handleResourceNotFoundException(ResourceNotFoundException exception) {

        ApiError apiError = new ApiError(HttpStatus.BAD_REQUEST, exception.getMessage());
        ResponseEntity<ApiError> responseEntity = ResponseEntity.badRequest().body(apiError);
        return responseEntity;
    }


}
