package com.global_groupware.employeemanagement.exception.managerException;

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
public class ManagerNotFoundExceptionHandler {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<ManagerErrorResponse> handleValidationException(MethodArgumentNotValidException ex) {
        List<String> errorMessages = ex.getBindingResult().getFieldErrors()
                .stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .collect(Collectors.toList());

        ManagerErrorResponse errorResponse = buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", errorMessages);
        return ResponseEntity.badRequest().body(errorResponse);
    }

    @ExceptionHandler(ManagerNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ManagerErrorResponse> handleManagerNotFoundException(ManagerNotFoundException ex) {
        List<String> errorMessages = List.of(ex.getMessage());
        ManagerErrorResponse errorResponse = buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", errorMessages);
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorResponse);
    }

    private ManagerErrorResponse buildErrorResponse(HttpStatus status, String error, List<String> messages) {
        return new ManagerErrorResponse(
                status.value(),
                error,
                messages,
                LocalDateTime.now()
        );
    }
}
