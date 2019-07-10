package com.omaru.employee.domain.service;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.domain.repository.DepartmentRepository;
import com.omaru.employee.domain.repository.EmployeeRepository;
import com.omaru.employee.util.CommandLineDataIngester;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import javax.inject.Inject;
import java.util.Collection;
import java.util.function.Consumer;

import static com.omaru.employee.util.MockUtil.getDepartments;
import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
public class DepartmentServiceShould {
    @Inject
    private DepartmentRepository departmentRepository;
    @Inject
    private EmployeeRepository employeeRepository;
    private DepartmentService departmentService;
    @MockBean
    private CommandLineDataIngester ingester;

    @Before
    public void setUp() {
        departmentService = new SimpleDepartmentService(departmentRepository,employeeRepository);
        Collection<Department> departments= getDepartments();
        Consumer<Department> saveDepartments= d -> departmentService.save(d);
        departments.forEach(saveDepartments);
    }

    @Test
    public void beAbleToRetrieveDepartments() {
        Collection<Department> departments= departmentService.get();
        assertThat(departments).isNotEmpty();
    }

}
