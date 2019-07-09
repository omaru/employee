package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Employee;
import org.springframework.hateoas.ResourceSupport;

public class EmployeeResource extends ResourceSupport {
    private Long id;
    private String name;
    private String lastName;
    private DepartmentResource departmentResource;
    public(Employee employee){

    }
}
