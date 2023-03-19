package com.mkyong.controllers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ShopsExceptionHandler {

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleContentNotAllowedException(Exception ex) {
        System.err.println(ex.getMessage());
        return ResponseEntity.badRequest().build();
    }


}
