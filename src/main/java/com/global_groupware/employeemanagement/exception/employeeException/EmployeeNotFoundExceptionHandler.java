package com.global_groupware.employeemanagement.exception.employeeException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@ControllerAdvice
public class EmployeeNotFoundExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<EmployeeErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        EmployeeErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", errorMessages);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<EmployeeErrorResponse> handleEmployeeNotFoundException(EmployeeNotFoundException ex) {
        List<String> errorMessages = List.of(ex.getMessage());
        EmployeeErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", errorMessages);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private EmployeeErrorResponse buildErrorResponse(HttpStatus status, String error, List<String> messages) {
        return new EmployeeErrorResponse(
                status.value(),
                error,
                messages,
                LocalDateTime.now()
        );
    }
}
