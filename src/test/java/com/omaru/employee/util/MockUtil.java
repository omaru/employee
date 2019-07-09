package com.omaru.employee.util;

import com.omaru.employee.domain.model.Department;
import com.omaru.employee.domain.model.Employee;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static java.util.stream.Collectors.toSet;

public final class MockUtil {

    private MockUtil() {
        throw new IllegalAccessError("utility class");
    }

    public static Collection<Employee> getEmployees(){
        return Stream.of(getEmployee("a"),
                getEmployee("b"),
                getEmployee("c"),
                getEmployee("d"),
                getEmployee("e"),
                getEmployee("f",false),
                getEmployee("g",false),
                getEmployee("h",false)).collect(toSet());
    }
    public static Collection<Department> getDepartments(){
        return Stream.of(getDepartment("first_department"),
                getDepartment("second_department"),
                getDepartment("third_department")).collect(toSet());
    }
    public static Collection<Employee> getActiveEmployees(){
        return getEmployees().stream().filter(e->e.isActive()).collect(toSet());
    }

    private static Employee getEmployee(String name, boolean active){
        Employee employee = getEmployee(name);
        employee.setActive(active);
        return employee;
    }

    private static Employee getEmployee(String name){
        Employee employee = new Employee(name);
        employee.setId(1L);
        employee.setActive(true);
        employee.setLastName("lastname");
        employee.setDepartment(getDepartment("department_1"));
        return employee;
    }

    private static Department getDepartment(String name){
        return new Department(name);
    }
}
