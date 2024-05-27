package com.global_groupware.employeemanagement.exception.managerException;

public class ManagerNotFoundException extends RuntimeException{
    public ManagerNotFoundException(String message) {
        super(message);
    }

    public ManagerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public ManagerNotFoundException(Throwable cause) {
        super(cause);
    }
}
