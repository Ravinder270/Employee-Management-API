package com.global_groupware.employeemanagement.dao;

import com.global_groupware.employeemanagement.entity.Employee;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepository extends MongoRepository<Employee, String> {
}
