package com.omaru.employee.domain.repository;

import com.omaru.employee.domain.model.Department;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface DepartmentRepository extends PagingAndSortingRepository<Department, Long> {

}