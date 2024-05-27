package com.global_groupware.employeemanagement.service;

import com.global_groupware.employeemanagement.entity.Employee;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface EmployeeService {

    List<Employee> getAllEmployees(Pageable pageable);

    Employee getEmployeeById(String employeeId);

    String addEmployee(Employee employee);

    void deleteEmployee(String employeeId);

    void updateEmployee(String employeeId, Employee employee);

    Employee getNthLevelManager(String employeeId, Integer level);

}
