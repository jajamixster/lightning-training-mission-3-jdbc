package com.mission3.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.Set;

@ControllerAdvice
public class CustomGlobalExceptionHandler extends ResponseEntityExceptionHandler {

    //MethodArgumentNotValidException
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "From MethodArgumentNotValid Exception in Global Exception Handler",
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.BAD_REQUEST);
    }

    //HHTPRequestMethodNotSupportedException
    @Override
    protected ResponseEntity<Object> handleHttpRequestMethodNotSupported(
            HttpRequestMethodNotSupportedException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                "From HttpRequestMethodNotSupportedException in Global Exception Handler - Method Not Allowed",
                ex.getMessage());

        return new ResponseEntity<>(customErrorDetails, HttpStatus.METHOD_NOT_ALLOWED);
    }

    //IdNotFoundException cast
    @ExceptionHandler(IdNotFoundException.class)
    public final ResponseEntity<Object> handleIdNotFoundException(IdNotFoundException ex, WebRequest request) {

        CustomErrorDetails customErrorDetails = new CustomErrorDetails(
                new Date(),
                ex. getMessage(),
                //show client id? etc. -> change get method
                request.getDescription(false));

        return new ResponseEntity<>(customErrorDetails, HttpStatus.NOT_FOUND);
    }
}
