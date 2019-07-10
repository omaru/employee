package com.omaru.employee.resource;

import com.omaru.employee.controller.EmployeeController;
import org.springframework.hateoas.ResourceSupport;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

public class MainNavigationResource extends ResourceSupport {
    public MainNavigationResource(){
        this.add(linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employee"));
    }
}
