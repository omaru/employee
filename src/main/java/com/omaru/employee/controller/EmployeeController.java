package com.omaru.employee.controller;

import com.omaru.employee.domain.service.EmployeeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;

@RestController
@RequestMapping(value="/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Inject
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }
}
