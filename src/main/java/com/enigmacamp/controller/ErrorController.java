package com.enigmacamp.controller;

import com.enigmacamp.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ErrorController {
    @ExceptionHandler(UnauthorizedException.class)
    ResponseEntity<String> handleUnauthorizedException(UnauthorizedException exception){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(exception.getMessage());
    }
    @ExceptionHandler(Exception.class)
    ResponseEntity<String> handleAllException(Exception exception){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Gk tau euy");
    }

    @ExceptionHandler(NullPointerException.class)
    ResponseEntity<String> handleNullException(NullPointerException exception){
        return ResponseEntity.status(HttpStatus.NOT_ACCEPTABLE).body("null");
    }

}
