package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;
import java.util.Optional;

@Service("employeeService")
public class SimpleEmployeeService implements EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;

    @Inject
    public SimpleEmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository){
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
    public Collection<Employee> get(String firstName) {
        return employeeRepository.findByFirstNameContaining(firstName);
    }

    @Override
    public Collection<Employee> get(Boolean active) {
        return employeeRepository.findByActive(active);
    }

    @Override
    public Collection<Employee> getByDepartment(Long id) {
        return departmentRepository.findById(id).orElseThrow(()->
                new NotFoundException("Department not found")).getEmployees();
    }

    @Override
    public Employee save(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public void delete(Long id) {
        Optional<Employee> employee = employeeRepository.findById(id);
        if(!employee.isPresent()){
            return;
        }
        employee.get().setActive(false);
        update(employee.get());
    }

    @Override
    public void update(Employee employee) {
        if(employee.getDepartment() != null){
            employee.setDepartment(departmentRepository.findById(employee.getDepartment().getId()).get());
        }
        employeeRepository.save(employee);
    }
}
