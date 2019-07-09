package com.omaru.employee.domain.service;

import com.omaru.employee.domain.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Collection<Employee> get();
    Collection<Employee> get(boolean active);
    void save(Employee employee);
    void delete(Employee employee);
    void update(Employee employee);
}
