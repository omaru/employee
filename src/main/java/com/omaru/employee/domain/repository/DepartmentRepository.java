package com.omaru.employee.domain.repository;

import com.omaru.employee.domain.model.Department;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;

@Repository
public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {
        Collection<Department> findAll();
}