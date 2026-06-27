package com.niladri.companyservice.exceptions;

public class CompanyAlreadyExistsByRegistrationNumber extends RuntimeException {
    public CompanyAlreadyExistsByRegistrationNumber(String message) {
        super(message);
    }
}
