package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import java.util.Collection;

@Service("departmentService")
public class SimpleDepartmentService implements DepartmentService {
    private final DepartmentRepository departmentRepository;

    @Inject
    public SimpleDepartmentService(DepartmentRepository departmentRepository){
        this.departmentRepository = departmentRepository;
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
}
