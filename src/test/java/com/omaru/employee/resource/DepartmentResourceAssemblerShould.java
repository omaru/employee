package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Department;
import org.junit.Test;

import static com.omaru.employee.util.MockUtil.getEmployees;
import static org.assertj.core.api.Assertions.assertThat;

public class DepartmentResourceAssemblerShould {
    private DepartmentResourceAssembler departmentResourceAssembler = new DepartmentResourceAssembler(new EmployeeResourceAssembler());

    @Test
    public void retrieveEmployeeResourceForGivenDepartment(){
        Department department = getEmployees().iterator().next().getDepartment();
        DepartmentResource departmentResource= departmentResourceAssembler.toResource(department);
        assertThat(departmentResource).isNotNull();
    }

    @Test
    public void containNavigationLinkToSelf(){
        Department department = getEmployees().iterator().next().getDepartment();
        DepartmentResource departmentResource= departmentResourceAssembler.toResource(department);
        assertThat(departmentResource.getLink("self").getHref()).contains("/department/"+department.getId());
    }
}
