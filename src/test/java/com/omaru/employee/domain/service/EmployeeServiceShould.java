package com.omaru.employee.domain.service;

import com.omaru.employee.domain.model.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

public class EmployeeServiceShould {
    private EmployeeService employeeService;

    @Before
    public void setUp() {
        employeeService = new EmployeServiceImpl();
    }

    @Test
    public void beAbleToRetrieveEmployees() {
        Collection<Employee> employees = employeeService.get();
        assertThat(employees).isNotEmpty();
    }

    @Test
    public void beAbleToRetrieveEmployeesByActiveStatus(){
        Boolean activeEmployees = true;
        Collection<Employee> employees = employeeService.get(activeEmployees);
        long totalActiveEmployees = employees.stream().filter(e->e.isActive()).count();
        assertThat(totalActiveEmployees).isEqualTo(5L);
    }

    @Test
    public void beAbleToRetrieveEmployeesByInactiveStatus(){
        Boolean activeEmployees = false;
        Collection<Employee> employees = employeeService.get(activeEmployees);
        assertThat(employees).isNotEmpty();
        long totalActiveEmployees = employees.stream().filter(e->e.isActive()==activeEmployees).count();
        assertThat(totalActiveEmployees).isEqualTo(3L);
    }
}
