package com.omaru.employee.util;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;
import com.omaru.employee.resource.DepartmentResource;
import com.omaru.employee.resource.DepartmentResourceAssembler;
import com.omaru.employee.resource.EmployeeResource;
import com.omaru.employee.resource.EmployeeResourceAssembler;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public final class MockUtil {
    private static final EmployeeResourceAssembler employeeResourceAssembler = new EmployeeResourceAssembler();
    private static final DepartmentResourceAssembler departmentResourceAssembler = new DepartmentResourceAssembler(employeeResourceAssembler);

    private MockUtil() {
        throw new IllegalAccessError("utility class");
    }

    public static Collection<Employee> getEmployees(){
        return Stream.of(getEmployee("a",true),
                getEmployee("b",true),
                getEmployee("c",true),
                getEmployee("d",true),
                getEmployee("e",true),
                getEmployee("f",false),
                getEmployee("g",false),
                getEmployee("h",false)).collect(toSet());
    }
    public static Collection<Department> getDepartments(){
        return Stream.of(getDepartment("first_department",1L),
                getDepartment("second_department",2L),
                getDepartment("third_department",3L)).collect(toSet());
    }
    public static Collection<Employee> getActiveEmployees(){
        return getEmployees().stream().filter(e->e.getActive()).collect(toSet());
    }

    private static Employee getEmployee(String name, boolean active){
        Employee employee = getEmployee(name);
        employee.setActive(active);
        return employee;
    }

    public static List<DepartmentResource> getDepartmentResources(){
        return departmentResourceAssembler.toResources(getDepartments());
    }

    public static List<EmployeeResource> getEmployeesResources(){
        return employeeResourceAssembler.toResources(getActiveEmployees());
    }
    public static EmployeeResource getAsResource(Employee employee){
        return employeeResourceAssembler.toResource(employee);
    }
    public static Employee getEmployee(String name){
        Employee employee = new Employee(name);
        employee.setId(1L);
        employee.setLastName("lastname");
        employee.setDateOfBirth(new Timestamp(Calendar.getInstance().getTimeInMillis()));
        employee.setDepartment(getDepartment("department_1",1L));
        return employee;
    }

    private static Department getDepartment(String name,Long id){
        Department department = new Department(name);
        department.setId(id);
        return department;
    }
}
