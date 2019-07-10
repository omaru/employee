package com.omaru.employee.domain.service;

import com.omaru.employee.domain.model.Employee;

import java.util.Collection;

public interface EmployeeService {
    Collection<Employee> get();
    Employee get(Long employeeId);
    Collection<Employee> get(String firstName);
    Collection<Employee> get(Boolean active);
    Collection<Employee> getByDepartment(Long id);
    Employee save(Employee employee);
    void delete(Long id);
    void update(Employee employee);
}
