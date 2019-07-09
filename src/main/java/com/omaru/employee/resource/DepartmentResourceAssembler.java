package com.omaru.employee.resource;

import com.omaru.employee.controller.DepartmentController;
import com.omaru.employee.domain.model.Department;
import org.springframework.hateoas.mvc.ResourceAssemblerSupport;

public class DepartmentResourceAssembler extends ResourceAssemblerSupport<Department, DepartmentResource> {

    public DepartmentResourceAssembler() {
        super(DepartmentController.class, DepartmentResource.class);
    }

    @Override
    public DepartmentResource toResource(Department department) {
        return new DepartmentResource(department);
    }
}
