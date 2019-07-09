package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;
@Getter@Setter
public class EmployeeResource extends ResourceSupport {
    private final String name;
    private final String lastName;
    public EmployeeResource(Employee employee){
        this.name = employee.getName();
        this.lastName = employee.getLastName();
    }
}
