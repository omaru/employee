package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.service.DepartmentService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/department/")
public class DepartmentController  {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService){
        this.departmentService = departmentService;
    }

    @RequestMapping(value={"{id}"},method = RequestMethod.GET,produces = MediaType.APPLICATION_JSON_VALUE)
    public Department getDepartment(@PathVariable Long id){
        return departmentService.get(id);
    }

}
