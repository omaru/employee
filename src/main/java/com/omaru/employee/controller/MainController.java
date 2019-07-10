package com.omaru.employee.controller;

import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
public class MainController {

    @GetMapping( path = {"","/"})
    public ResponseEntity<ResourceSupport> index(){
        ResourceSupport mainNavigation = new ResourceSupport();
        mainNavigation.add(linkTo(methodOn(EmployeeController.class).getEmployees()).withRel("employee"));
        return new ResponseEntity<>(mainNavigation, HttpStatus.OK);
    }
}
