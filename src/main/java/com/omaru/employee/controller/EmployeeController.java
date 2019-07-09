package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.EmployeeService;
import org.springframework.http.HttpEntity;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(value="/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;

    @Inject
    public EmployeeController(EmployeeService employeeService){
        this.employeeService = employeeService;
    }

    @RequestMapping(value={""},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Collection<Employee> getEmployees(){
        Collection<Employee> employees = employeeService.get();
        return employees;
    }
    @RequestMapping(value={"{id}"},method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmployees(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("ok");
    }
}
