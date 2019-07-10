package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.EmployeeService;
import com.omaru.employee.resource.EmployeeResource;
import com.omaru.employee.resource.EmployeeResourceAssembler;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.inject.Inject;
import java.util.Collection;

@RestController
@RequestMapping(value="/employee/")
public class EmployeeController {
    private final EmployeeService employeeService;
    private final EmployeeResourceAssembler employeeResourceAssembler;
    @Inject
    public EmployeeController(EmployeeService employeeService, EmployeeResourceAssembler employeeResourceAssembler){
        this.employeeService = employeeService;
        this.employeeResourceAssembler = employeeResourceAssembler;
    }

    @RequestMapping(value={""},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EmployeeResource>> getEmployees(){
        Collection<Employee> employees = employeeService.get();
        return new ResponseEntity<>(employeeResourceAssembler.toResources(employees), HttpStatus.OK);
    }

    @RequestMapping(value={"{id}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Employee getEmployee(@PathVariable Long id){
        return employeeService.get(id);
    }

    @RequestMapping(value={"{id}"},method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> deleteEmployees(@PathVariable Long id){
        employeeService.delete(id);
        return ResponseEntity.ok("ok");
    }
}
