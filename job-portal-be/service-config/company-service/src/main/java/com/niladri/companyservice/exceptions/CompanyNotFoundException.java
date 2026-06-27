package com.niladri.companyservice.exceptions;

public class CompanyNotFoundException extends RuntimeException {
    public CompanyNotFoundException(String s) {
        super(s);
    }
}
