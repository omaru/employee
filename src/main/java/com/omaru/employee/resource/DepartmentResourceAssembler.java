package com.omaru.employee.resource;

import com.omaru.employee.controller.DepartmentController;
import com.omaru.employee.domain.model.Department;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;
@Component
public class DepartmentResourceAssembler extends ResourceAssemblerSupport<Department, DepartmentResource> {

    public DepartmentResourceAssembler() {
        super(DepartmentController.class, DepartmentResource.class);
    }

    @Override
    public DepartmentResource toResource(Department department) {
        DepartmentResource resource = new DepartmentResource(department);
        resource.add(linkTo(methodOn(DepartmentController.class).getDepartment(department.getId())).withSelfRel());
        return resource;
    }
}
