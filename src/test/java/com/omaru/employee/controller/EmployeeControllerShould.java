package com.omaru.employee.controller;

import com.omaru.employee.domain.service.EmployeeService;
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

import static com.omaru.employee.util.MockUtil.getEmployeesResources;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
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
    @MockBean
    private EmployeeResourceAssembler employeeResourceAssembler;
    @MockBean
    private CommandLineDataIngester ingester;
    @Test
    public void beAbleToRetrieveEmployees() throws Exception {
        given(employeeResourceAssembler.toResources(any())).willReturn(getEmployeesResources());
        mockMvc.perform(get("/employee/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("$[0].name", is("b")));
    }

    @Test
    public void onDeleteDenyAuthorization() throws Exception {
        mockMvc.perform(delete("/employee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
        mockMvc.perform(delete("/employee/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser
    public void onDeleteAllowAuthorizedRequest() throws Exception {
        mockMvc.perform(delete("/employee/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }
}
