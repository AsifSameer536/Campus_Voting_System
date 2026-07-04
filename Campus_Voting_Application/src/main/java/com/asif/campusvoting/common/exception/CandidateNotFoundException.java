package com.asif.campusvoting.common.exception;

public class CandidateNotFoundException extends RuntimeException {

    public CandidateNotFoundException(String message) {
        super(message);
    }
}