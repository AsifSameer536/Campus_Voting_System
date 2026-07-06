package com.asif.campusvoting.common.exception;

public class VoteAlreadyCastException extends RuntimeException {

    public VoteAlreadyCastException(String message) {
        super(message);
    }
}