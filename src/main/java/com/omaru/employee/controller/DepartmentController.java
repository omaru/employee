package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.DepartmentService;
import com.omaru.employee.resource.DepartmentResource;
import com.omaru.employee.resource.DepartmentResourceAssembler;
import com.omaru.employee.resource.EmployeeResource;
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
@RequestMapping("/department/")
public class DepartmentController  {
    private final DepartmentService departmentService;
    private final DepartmentResourceAssembler departmentResourceAssembler;
    @Inject
    public DepartmentController(DepartmentService departmentService,DepartmentResourceAssembler departmentResourceAssembler){
        this.departmentService = departmentService;
        this.departmentResourceAssembler=departmentResourceAssembler;
    }

    @RequestMapping(value={"{id}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<DepartmentResource> getDepartment(@PathVariable Long id){
        Department department = departmentService.get(id);
        return new ResponseEntity<>(departmentResourceAssembler.toResource(department), HttpStatus.OK);
    }
    @RequestMapping(value={"{id}/employee/"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Collection<EmployeeResource>> getEmployeesByDepartment(@PathVariable Long id){
        Collection<Employee> employees = departmentService.getEmployeeByDepartment(id);
        return new ResponseEntity<>(departmentResourceAssembler.toResources(employees), HttpStatus.OK);
    }
}
