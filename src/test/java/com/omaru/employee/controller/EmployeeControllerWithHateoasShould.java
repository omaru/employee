package com.omaru.employee.controller;

import com.omaru.employee.domain.service.EmployeeService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import javax.inject.Inject;

import static com.omaru.employee.util.MockUtil.getActiveEmployees;
import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerWithHateoasShould {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private EmployeeService employeeService;

    @Test
    public void retrieveNavigationResourceLinksWhenGettingEmployees() throws Exception{
        given(employeeService.get()).willReturn(getActiveEmployees());
       MvcResult result = mockMvc.perform(get("/employee/").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andReturn();
        assertThat(result.getResponse().getContentAsString()).isNotNull();
    }
}
