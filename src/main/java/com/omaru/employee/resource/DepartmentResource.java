package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Department;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;

@Getter
public class DepartmentResource extends ResourceSupport {
    @Column(nullable = false)
    private final String name;
    public DepartmentResource(Department department){
        this.name = department.getName();
    }

}
