package com.omaru.employee.domain.repository;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
        Collection<Department> findAll();
}