package com.omaru.employee.domain.repository;

import com.omaru.employee.domain.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends PagingAndSortingRepository<Employee, Long> {
    Iterable<Employee> findByIdIn(Collection<Long> ids);
    Collection<Employee> findByFirstNameContaining(String name);
    Optional<Employee> findByIdAndActiveTrue(Long id);
    Collection<Employee> findByActive(boolean active);
}