package com.asif.campusvoting.common.exception;

public class StudentAlreadyRegisteredException extends RuntimeException {

    public StudentAlreadyRegisteredException(String message) {
        super(message);
    }

}