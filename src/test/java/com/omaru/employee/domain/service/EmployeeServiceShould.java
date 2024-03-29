package com.omaru.employee.domain.service;

import com.omaru.employee.domain.exception.NotFoundException;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import com.omaru.employee.util.CommandLineDataIngester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Consumer;

import static com.omaru.employee.util.MockUtil.getEmployee;
import static com.omaru.employee.util.MockUtil.getEmployees;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class EmployeeServiceShould {
    @Inject
    private EmployeeRepository employeeRepository;
    @Inject
    private DepartmentRepository departmentRepository;
    private EmployeeService employeeService;
    @MockBean
    private CommandLineDataIngester ingester;
    @Before
    public void setUp(){
        employeeService = new SimpleEmployeeService(employeeRepository,departmentRepository);
        Collection<Employee> employees = getEmployees();
        Consumer<Employee> saveEmployees = e->{e.setId(null);e.setDepartment(null);employeeService.save(e);};
        employees.stream().peek(e->System.out.println("employee e"+e.getId()+" "+e.getFirstName())).forEach(saveEmployees);
    }

    @Test
    public void beAbleToRetrieveEmployees() {
        Collection<Employee> employees = employeeService.get();
        assertThat(employees).isNotEmpty();
    }
    @Test
    public void geAbleToSetActiveTrueByDefault() {
        Employee employee = getEmployee("test");
        employee = employeeService.save(employee);
        assertThat(employee.getActive()).isTrue();
    }
    @Test
    public void beAbleToRetrieveEmployeesByActiveStatus(){
        String employeeName = "a";
        Collection<Employee> employees = employeeService.get(employeeName);
        assertThat(employees).hasSize(1);
    }

    @Test
    public void beAbleToRetrieveEmployeesByInactiveStatus(){
        Collection<Employee> employees = employeeService.get(false);
        assertThat(employees).hasSize(3);
    }
    @Test
    public void beAbleToRetrieveEmployeesByName(){
        Collection<Employee> employees = employeeService.get("a");
        assertThat(employees).hasSize(1);
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
        employeeService.delete(employee.getId());
        employeeService.get(employee.getId());
    }
}
