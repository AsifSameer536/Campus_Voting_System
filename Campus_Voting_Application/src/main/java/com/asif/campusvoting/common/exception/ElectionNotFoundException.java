package com.asif.campusvoting.common.exception;

public class ElectionNotFoundException extends RuntimeException {

    public ElectionNotFoundException(String message) {
        super(message);
    }
}