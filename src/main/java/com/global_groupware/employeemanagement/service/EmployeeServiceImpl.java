package com.global_groupware.employeemanagement.service;

import com.global_groupware.employeemanagement.dao.EmployeeRepository;
import com.global_groupware.employeemanagement.entity.Employee;
import com.global_groupware.employeemanagement.exception.employeeException.EmployeeNotFoundException;
import com.global_groupware.employeemanagement.exception.managerException.ManagerNotFoundException;
import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private EmployeeRepository employeeRepository;

    // Entry Level
    @Autowired
    public EmployeeServiceImpl(EmployeeRepository theEmployeeRepository) {
        employeeRepository = theEmployeeRepository;
    }

    @Override
    public List<Employee> getAllEmployees(Pageable pageable) {
        return employeeRepository.findAll(pageable).getContent();
    }

    @Override
    public String addEmployee(@NotNull Employee employee) {
        String employeeId = UUID.randomUUID().toString();
        employee.setId(employeeId);
        employeeRepository.save(employee);
        return employeeId;
    }

    @Override
    public Employee getEmployeeById(String employeeId) {
        Optional<Employee> optionalEmployee = employeeRepository.findById(employeeId);
        return optionalEmployee
                .orElseThrow(() -> new EmployeeNotFoundException("Employee with Id :: " + employeeId + " not found"));
    }

    @Override
    public void deleteEmployee(String employeeId) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with Id :: " + employeeId + " not found");
        }
        employeeRepository.deleteById(employeeId);
    }

    @Override
    public void updateEmployee(String employeeId, Employee employee) {
        if (!employeeRepository.existsById(employeeId)) {
            throw new EmployeeNotFoundException("Employee with Id :: " + employeeId + " not found");
        }
        employeeRepository.save(employee);
    }



    //Intermediate Level

    @Override
    public Employee getNthLevelManager(String employeeId, Integer level)
    {
        if(!employeeRepository.existsById(employeeId))
        {
            throw new EmployeeNotFoundException("Employee with Id :: " + employeeId + " not found");

        }
        Employee employee = employeeRepository.findById(employeeId)
                .orElseThrow(()-> new EmployeeNotFoundException("Employee with Id :: " + employeeId + " not found"));
        if(employee.getReportsTo() == null)
        {
            throw new ManagerNotFoundException("Employee with Id: " + employeeId + " has no manager");
        }
        if (level == 0) {
            return employee;
        }
        return getNthLevelManager(employee.getReportsTo(), level - 1);
    }



}
