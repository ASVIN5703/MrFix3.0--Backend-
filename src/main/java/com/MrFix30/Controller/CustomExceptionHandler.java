package com.MrFix30.Controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import CustomExceptions.DataAlreadyExists;
import CustomExceptions.InvalidCredentialsException;

@RestControllerAdvice
public class CustomExceptionHandler {
	 @ExceptionHandler(InvalidCredentialsException.class)
	    public ResponseEntity<String> handleInvalidCredentialsException(InvalidCredentialsException ex) {
	        return new ResponseEntity<>(ex.getMessage(), HttpStatus.UNAUTHORIZED);
	    }
	 @ExceptionHandler(DataAlreadyExists.class)
	    public ResponseEntity<String> handleDataAlreadyExists(DataAlreadyExists ex){
		  return new ResponseEntity<>(ex.getMessage(),HttpStatus.FOUND);
	 }
}
