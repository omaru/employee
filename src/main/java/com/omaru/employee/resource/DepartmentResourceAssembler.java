package com.omaru.employee.resource;

import com.omaru.employee.controller.DepartmentController;
import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
@Component
public class DepartmentResourceAssembler extends ResourceAssemblerSupport<Department, DepartmentResource> {
    private final EmployeeResourceAssembler employeeResourceAssembler;
    public DepartmentResourceAssembler(EmployeeResourceAssembler employeeResourceAssembler) {
        super(DepartmentController.class, DepartmentResource.class);
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    @Override
    public DepartmentResource toResource(Department department) {
        DepartmentResource resource = new DepartmentResource(department);
        resource.add(linkTo(methodOn(DepartmentController.class).getDepartment(department.getId())).withSelfRel());
        resource.add(linkTo(methodOn(DepartmentController.class).
                getEmployeesByDepartment(department.getId())).withRel("employee-by-department"));
        return resource;
    }
    public Collection<EmployeeResource>toResources(Collection<Employee> employees) {
        return employeeResourceAssembler.toResources(employees);
    }
}
