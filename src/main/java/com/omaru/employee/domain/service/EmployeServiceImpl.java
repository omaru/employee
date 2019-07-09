package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.EmployeeRepository;

import javax.inject.Inject;
import java.util.Collection;

public class EmployeServiceImpl implements EmployeeService {
    private EmployeeRepository employeeRepository;

    @Inject
    public EmployeServiceImpl(EmployeeRepository employeeRepository){
        this.employeeRepository = employeeRepository;
    }

    @Override
    public Collection<Employee> get() {
        return null;
    }

    @Override
    public Collection<Employee> get(boolean active) {
        return employeeRepository.findByActive(active);
    }

    @Override
    public void save(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void delete(Employee employee) {
        employee.setActive(false);
        update(employee);
    }

    @Override
    public void update(Employee employee) {
        employeeRepository.save(employee);
    }
}
