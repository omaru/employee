package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Department;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import javax.persistence.Column;
import java.util.Collection;

@Getter@Setter
public class DepartmentResource extends ResourceSupport {
    @Column(nullable = false)
    private String name;
    private Collection<EmployeeResource> employees;
    public DepartmentResource(Department department){
        this.name = department.getName();
    }

}
