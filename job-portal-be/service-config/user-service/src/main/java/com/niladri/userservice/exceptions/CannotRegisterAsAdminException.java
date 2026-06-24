package com.niladri.userservice.exceptions;

public class CannotRegisterAsAdminException extends RuntimeException {
    public CannotRegisterAsAdminException(String message) {
        super(message);
    }
}
