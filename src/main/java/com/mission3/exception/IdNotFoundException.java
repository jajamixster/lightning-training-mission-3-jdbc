package com.mission3.exception;

public class IdNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    //Superclass Constructor
    public IdNotFoundException(String message) {
        super(message);
    }
}
