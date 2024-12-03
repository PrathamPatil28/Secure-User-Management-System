package com.example.pratham.Secure.User.Management.System.Project.exception;

import org.apache.velocity.exception.ResourceNotFoundException;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


import java.lang.module.ResolutionException;

@ControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<String> handelResourceNotFoundException(ResourceNotFoundException ex){
             return new ResponseEntity<>(ex.getMessage(), HttpStatus.NOT_FOUND);
    }
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String>handelGeneralException(Exception ex){
         return new ResponseEntity<>("error aaya he bhai" +ex.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
