package com.ems.exception;

public class EmployeeNotFoundException extends RuntimeException {

    public EmployeeNotFoundException(int id) {
        super("Employee with ID " + id + " was not found.");
    }
}