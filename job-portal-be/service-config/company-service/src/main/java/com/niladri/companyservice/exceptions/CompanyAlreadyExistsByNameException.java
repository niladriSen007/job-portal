package com.niladri.companyservice.exceptions;

public class CompanyAlreadyExistsByNameException extends RuntimeException {
    public CompanyAlreadyExistsByNameException(String message) {
        super(message);
    }
}
