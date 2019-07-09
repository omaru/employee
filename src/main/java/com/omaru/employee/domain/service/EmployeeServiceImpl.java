package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
@Service("employeeService")
public class EmployeeServiceImpl implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Inject
    public EmployeeServiceImpl(EmployeeRepository employeeRepository,DepartmentRepository departmentRepository){
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    @Override
    public Collection<Employee> get() {
        return employeeRepository.findByActive(true);
    }

    @Override
    public Employee get(Long employeeId) {
        return employeeRepository.findByIdAndActiveTrue(employeeId).orElseThrow(()->
                new NotFoundException("employee "+employeeId+ "not found"));
    }
    @Override
    public Collection<Employee> get(Boolean active) {
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
