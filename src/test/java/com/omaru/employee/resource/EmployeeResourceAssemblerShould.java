package com.omaru.employee.resource;

import com.omaru.employee.domain.model.Employee;
import org.junit.Test;

import static com.omaru.employee.util.MockUtil.getEmployees;
import static org.assertj.core.api.Assertions.assertThat;

public class EmployeeResourceAssemblerShould {

    private EmployeeResourceAssembler employeeResourceAssembler = new EmployeeResourceAssembler();

    @Test
    public void retrieveEmployeeResourceForGivenEmployee(){
        Employee employee = getEmployees().iterator().next();
        EmployeeResource employeeResource = employeeResourceAssembler.toResource(employee);
        assertThat(employeeResource).isNotNull();
    }
    @Test
    public void containNavigationLinkToSelf(){
        Employee employee = getEmployees().iterator().next();
        EmployeeResource employeeResource = employeeResourceAssembler.toResource(employee);
        assertThat(employeeResource.getLink("self").getHref()).contains("/employee/"+employee.getId());
    }

    @Test
    public void retrieveResourceThatContainsNameAndLastName(){
        Employee employee = getEmployees().iterator().next();
        EmployeeResource employeeResource = employeeResourceAssembler.toResource(employee);
        assertThat(employeeResource.getFirstName()).isEqualTo(employee.getFirstName());
        assertThat(employeeResource.getLastName()).isEqualTo(employee.getLastName());
    }

    @Test
    public void containNavigationLinkToDepartment(){
        Employee employee = getEmployees().iterator().next();
        EmployeeResource employeeResource = employeeResourceAssembler.toResource(employee);
        assertThat(employeeResource.getLink("department").getHref()).contains("/department/"+
                employee.getDepartment().getId());
    }
}
