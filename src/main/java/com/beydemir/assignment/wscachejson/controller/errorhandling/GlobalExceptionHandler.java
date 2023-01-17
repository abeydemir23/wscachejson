package com.beydemir.assignment.wscachejson.controller.errorhandling;

import com.beydemir.assignment.wscachejson.service.exception.SubscriberNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
@RestControllerAdvice

public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(SubscriberNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<Object> handleSubscriberNotFoundException(SubscriberNotFoundException subscriberNotFoundException) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(subscriberNotFoundException.getMessage());
    }
}
