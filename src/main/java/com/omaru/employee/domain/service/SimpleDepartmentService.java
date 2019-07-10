package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service("departmentService")
public class SimpleDepartmentService implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final EmployeeRepository employeeRepository;
    @Inject
    public SimpleDepartmentService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository){
        this.departmentRepository = departmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void save(Department department) {
        departmentRepository.save(department);
    }

    @Override
    public Collection<Department> get() {
        return departmentRepository.findAll();
    }

    @Override
    public Department get(Long id) {
        return departmentRepository.findById(id)
                .orElseThrow(()->new NotFoundException("unable to get department "));
    }

    @Override
    public Collection<Employee> getEmployeeByDepartment(Long id) {
        return employeeRepository.findByDepartmentId(id);
    }
}
