package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Consumer;

import static com.omaru.employee.util.EmployeeUtil.getEmployees;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceShould {
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    @Before
    public void setUp(){
        employeeService = new EmployeeServiceImpl(employeeRepository,departmentRepository);
        Collection<Employee> employees = getEmployees();
        Consumer<Employee> saveEmployees = e->employeeService.save(e);
        employees.forEach(saveEmployees);
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

    @Test(expected = NotFoundException.class)
    public void whenNoEmployeeFoundThrowNotFoundException(){
        long nonExistentEmployeeId = -789789L;
        employeeService.get(nonExistentEmployeeId);
    }

    @Test(expected = NotFoundException.class)
    public void beAbleToLogicalDeleteAnEmployee(){
        Collection<Employee> employees = employeeService.get();
        Employee employee = employees.iterator().next();
        employeeService.delete(employee);
        employeeService.get(employee.getId());
    }
}
