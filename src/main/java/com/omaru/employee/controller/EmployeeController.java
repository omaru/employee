package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.EmployeeService;
import com.omaru.employee.resource.EmployeeResource;
import com.omaru.employee.resource.EmployeeResourceAssembler;
import com.omaru.employee.resource.MainNavigationResource;
import org.springframework.hateoas.ResourceSupport;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.inject.Inject;
import javax.validation.Valid;
import java.util.Collection;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

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
    public ResponseEntity<EmployeeResource> getEmployee(@PathVariable Long id){
        Employee employee = employeeService.get(id);
        return new ResponseEntity<>(employeeResourceAssembler.toResource(employee),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> saveEmployee(@RequestBody @Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(),HttpStatus.CONFLICT);
        }
        employee = employeeService.save(employee);
        ResourceSupport support = new ResourceSupport();
        support.add(linkTo(methodOn(EmployeeController.class).getEmployee(employee.getId())).withRel("employee"));
        return new ResponseEntity<>(support,HttpStatus.OK);
    }

    @RequestMapping(value="{id}",method = RequestMethod.PUT,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Object> updateEmployee(@PathVariable Long id,@RequestBody @Valid Employee employee, BindingResult result){
        if(result.hasErrors()){
            return new ResponseEntity<>(result.getAllErrors(),HttpStatus.CONFLICT);
        }
        employee.setId(id);
        employeeService.update(employee);
        return new ResponseEntity<>("",HttpStatus.NO_CONTENT);
    }

    @RequestMapping(value={"{id}"},method = RequestMethod.DELETE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<ResourceSupport> deleteEmployee(@PathVariable Long id){
        employeeService.delete(id);
        return new ResponseEntity<>(new MainNavigationResource(),HttpStatus.OK);
    }
}
