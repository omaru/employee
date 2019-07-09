package com.omaru.employee.domain.repository;

import com.omaru.employee.domain.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Collection;

public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Iterable<Employee> findByIdIn(Collection<Long> ids);
    Iterable<Employee> findByNameContaining(String name);
}