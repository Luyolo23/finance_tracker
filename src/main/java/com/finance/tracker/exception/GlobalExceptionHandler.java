package com.finance.tracker.exception;

import com.finance.tracker.dto.ErrorResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ErrorResponse> handleRuntimeException(RuntimeException ex){

        ErrorResponse error = new ErrorResponse( ex.getMessage(), 400);

        return ResponseEntity.badRequest().body(error);
    }
}
