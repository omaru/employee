package com.omaru.employee.domain.service;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;

import java.util.Collection;

public interface DepartmentService {
    void save(Department d);
    Collection<Department> get();
    Department get(Long id);
    Collection<Employee> getEmployeeByDepartment(Long id);
}
