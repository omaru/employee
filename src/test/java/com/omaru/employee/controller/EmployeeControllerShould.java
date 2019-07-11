package com.omaru.employee.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.service.EmployeeService;
import com.omaru.employee.resource.EmployeeResource;
import com.omaru.employee.resource.EmployeeResourceAssembler;
import com.omaru.employee.util.CommandLineDataIngester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;
import java.util.List;

import static com.omaru.employee.util.MockUtil.*;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.hamcrest.Matchers.containsString;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerShould {
    @Inject
    private MockMvc mockMvc;
    @Inject
    private ObjectMapper objectMapper;
    @MockBean
    private EmployeeService employeeService;
    @MockBean
    private EmployeeResourceAssembler employeeResourceAssembler;
    @MockBean
    private CommandLineDataIngester ingester;

    @Test
    public void beAbleToRetrieveEmployees() throws Exception {
        List<EmployeeResource> employeeResources = getEmployeesResources();
        given(employeeResourceAssembler.toResources(any())).willReturn(employeeResources);
        EmployeeResource employeeResource = employeeResources.get(0);
        mockMvc.perform(get("/employee/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].firstName",
                is(employeeResource.getFirstName())));
    }

    @Test
    public void beAbleToRetrieveEmployeeById() throws Exception {
        EmployeeResource employeeResource = getEmployeesResources().get(0);
        given(employeeResourceAssembler.toResource(any())).willReturn(employeeResource);
        mockMvc.perform(get("/employee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("firstName",
                is(employeeResource.getFirstName())));
    }

    @Test
    public void beAbleToCreateAnEmployee() throws Exception {
        Employee employee = getEmployee("created employee");
        given(employeeService.save(any())).willReturn(employee);
        EmployeeResource resource = getAsResource(employee);
        given(employeeResourceAssembler.toResource(any())).willReturn(resource);
        mockMvc.perform(post("/employee/").content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("_links.employee.href",containsString("/employee/1")));
    }

    @Test
    public void whenInvalidEmployeeCreationRetrieveConflictStatus() throws Exception {
        Employee employee = getEmployee("created employee");
        employee.setDateOfBirth(null);
        given(employeeResourceAssembler.toResource(any())).willReturn(getAsResource(employee));
        mockMvc.perform(post("/employee/").content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isConflict());
    }

    @Test
    public void beAbleToUpdateAnExistentEmployee() throws Exception {
        Employee employee = getEmployee("created employee");
        mockMvc.perform(put("/employee/1").content(objectMapper.writeValueAsString(employee))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());
    }

    @Test
    public void onDeleteDenyAuthorization() throws Exception {
        mockMvc.perform(delete("/employee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(delete("/employee/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    public void onUnexistentEmployeeReturnNotFoundResponse() throws Exception {
        given(employeeService.get(anyLong())).willThrow(new NotFoundException("user not found"));
        mockMvc.perform(get("/employee/-4").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());
    }

    @Test
    @WithMockUser
    public void onDeleteAllowAuthorizedRequest() throws Exception {
        mockMvc.perform(delete("/employee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
