package com.omaru.employee.controller;

import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerShould {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;
    @Test
    public void beAbleToRetrieveAllActiveEmployees() throws Exception{
        Collection<Employee> employees = Stream.of(new Employee("a"),new Employee("b")).collect(Collectors.toSet());
        Mockito.when(employeeService.get(true)).thenReturn(employees);
        mockMvc.perform(get("/employee").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].name").isString());
    }
}
