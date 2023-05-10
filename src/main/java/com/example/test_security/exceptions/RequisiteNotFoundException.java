package com.example.test_security.exceptions;

public class RequisiteNotFoundException extends IllegalArgumentException {

    public RequisiteNotFoundException(String s) {
        super(s);
    }
}
