package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Employee;
import lombok.Getter;
import org.springframework.hateoas.ResourceSupport;
@Getter
public class EmployeeResource extends ResourceSupport {
    private final String name;
    private final String lastName;
    private final DepartmentResource departmentResource;
    public EmployeeResource(Employee employee){
        this.name = employee.getName();
        this.lastName = employee.getLastName();
        this.departmentResource = new DepartmentResource(employee.getDepartment());
    }
}
