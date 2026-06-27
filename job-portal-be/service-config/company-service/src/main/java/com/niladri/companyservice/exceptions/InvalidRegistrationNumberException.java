package com.niladri.companyservice.exceptions;

public class InvalidRegistrationNumberException extends RuntimeException {
    public InvalidRegistrationNumberException(String message) {
        super(message);
    }
}
