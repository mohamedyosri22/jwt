package com.spring.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.Date;

@ControllerAdvice
public class ControllerException {
    @ExceptionHandler
    public ResponseEntity<Error> getExceptionToken(TokenException tk) {
        Error r = new Error();
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setMessage(tk.getMessage());
        r.setTimeStamp(System.currentTimeMillis());

        return new ResponseEntity<Error>(r, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    public ResponseEntity<Error> getException(Exception e){
        Error r = new Error();
        r.setMessage(e.getMessage());
        r.setStatusCode(HttpStatus.NOT_FOUND.value());
        r.setTimeStamp(new Date().getTime());

        return new ResponseEntity<Error>(r,HttpStatus.I_AM_A_TEAPOT);
    }
}
