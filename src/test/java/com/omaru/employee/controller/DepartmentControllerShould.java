package com.omaru.employee.controller;

import com.omaru.employee.domain.service.DepartmentService;
import com.omaru.employee.resource.DepartmentResource;
import com.omaru.employee.resource.DepartmentResourceAssembler;
import com.omaru.employee.util.CommandLineDataIngester;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import javax.inject.Inject;

import static com.omaru.employee.util.MockUtil.getDepartmentResources;
import static org.hamcrest.Matchers.is;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerShould {
    @Inject
    private MockMvc mockMvc;
    @MockBean
    private DepartmentService departmentService;
    @MockBean
    private DepartmentResourceAssembler departmentResourceAssembler;
    @MockBean
    private CommandLineDataIngester ingester;

    @Test
    public void beAbleToRetrieveDepartments() throws Exception {
        DepartmentResource departmentResource = getDepartmentResources().get(0);
        given(departmentResourceAssembler.toResource(any())).willReturn(departmentResource);
        mockMvc.perform(get("/department/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk()).andExpect(jsonPath("name", is(departmentResource.getName())));
    }
}
