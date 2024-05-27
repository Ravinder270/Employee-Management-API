package com.global_groupware.employeemanagement.rest;

import com.global_groupware.employeemanagement.entity.Employee;
import com.global_groupware.employeemanagement.exception.employeeException.EmployeeNotFoundException;
import com.global_groupware.employeemanagement.exception.managerException.ManagerNotFoundException;
import com.global_groupware.employeemanagement.service.EmployeeService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/employees")
public class EmployeeRestController
{
    private EmployeeService employeeService;

    @Autowired
    public EmployeeRestController(EmployeeService theEmployeeService) {
        employeeService = theEmployeeService;
    }

    private ResponseEntity<Object> buildErrorResponse(HttpStatus status, String error, List<String> messages) {
        Map<String, Object> response = new HashMap<>();
        response.put("status", status.value());
        response.put("error", error);
        response.put("messages", messages);
        response.put("timestamp", LocalDateTime.now());

        return ResponseEntity.status(status).body(response);
    }

    //Read By id
    @GetMapping("/{employeeId}")
    public ResponseEntity<Object> getEmployeeById(@PathVariable String employeeId) {
        try {
            Employee theEmployee = employeeService.getEmployeeById(employeeId);
            return ResponseEntity.ok(theEmployee);
        } catch (EmployeeNotFoundException exc) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", List.of(exc.getMessage()));
        }

    }

    // ADD
    @PostMapping
    public ResponseEntity<Object> addEmployee(@Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());
            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", errorMessages);
        }
        String employeeId = employeeService.addEmployee(employee);
        return new ResponseEntity<>(employeeId, HttpStatus.CREATED);
    }

    //Update
    @PutMapping("/{employeeId}")
    public ResponseEntity<Object> updateEmployee(@PathVariable String employeeId, @Valid @RequestBody Employee employee, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            // Extract validation error messages
            List<String> errorMessages = bindingResult.getFieldErrors()
                    .stream()
                    .map(error -> error.getField() + ": " + error.getDefaultMessage())
                    .collect(Collectors.toList());

            return buildErrorResponse(HttpStatus.BAD_REQUEST, "Bad Request", errorMessages);
        }

        try {
            employeeService.updateEmployee(employeeId, employee);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException ex) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", List.of(ex.getMessage()));
        }
    }

    //Delete
    @DeleteMapping("/{employeeId}")
    public ResponseEntity<Object> deleteEmployee(@PathVariable String employeeId) {
        try {
            employeeService.deleteEmployee(employeeId);
            return ResponseEntity.noContent().build();
        } catch (EmployeeNotFoundException ex) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", List.of(ex.getMessage()));
        }

    }

    //Intermediate
    @GetMapping("/manager")
    public ResponseEntity<Object> getNthLevelManager(
            @RequestParam String employeeId,
            @RequestParam int level
    ) {
        try {
            Employee manager = employeeService.getNthLevelManager(employeeId, level);
            return ResponseEntity.ok(manager);
        } catch (EmployeeNotFoundException | ManagerNotFoundException ex) {
            return buildErrorResponse(HttpStatus.NOT_FOUND, "Not Found", List.of(ex.getMessage()));
        }
    }


    // Read all
    @GetMapping
    public ResponseEntity<List<Employee>> getAllEmployees(
            @RequestParam(defaultValue = "1") int page,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "id") String sortBy
    ) {
        try {
            Pageable pageable = PageRequest.of(page - 1, pageSize, Sort.by(sortBy).ascending());
            List<Employee> employeePage = employeeService.getAllEmployees(pageable);
            return ResponseEntity.ok(employeePage);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

}
