package com.omaru.employee.resource;

import com.omaru.employee.controller.DepartmentController;
import com.omaru.employee.controller.EmployeeController;
import com.omaru.employee.domain.model.Employee;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class EmployeeResourceAssembler extends ResourceAssemblerSupport<Employee, EmployeeResource> {
    public EmployeeResourceAssembler() {
        super(EmployeeController.class, EmployeeResource.class);
    }

    @Override
    public EmployeeResource toResource(Employee employee) {
        EmployeeResource resource = new EmployeeResource(employee);
        resource.add(linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withSelfRel());
        resource.add(linkTo(methodOn(DepartmentController.class)
                .getDepartment(employee.getDepartment().getId())).withRel("department"));
        return resource;
    }
}
