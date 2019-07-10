package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Employee;
import lombok.Getter;
import lombok.Setter;
import org.springframework.hateoas.ResourceSupport;

import java.sql.Timestamp;

@Getter@Setter
public class EmployeeResource extends ResourceSupport {
    private final String firstName;
    private final Character middleInitial;
    private final String lastName;
    private Timestamp dateOfBirth;
    private Timestamp dateOfEmployment;
    public EmployeeResource(Employee employee){
        this.firstName = employee.getFirstName();
        this.lastName = employee.getLastName();
        this.middleInitial = employee.getMiddleInitial();
        this.dateOfBirth = employee.getDateOfBirth();
        this.dateOfEmployment = employee.getDateOfEmployment();
    }
}
