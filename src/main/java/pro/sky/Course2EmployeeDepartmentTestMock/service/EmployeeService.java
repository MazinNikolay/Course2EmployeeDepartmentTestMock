package pro.sky.Course2EmployeeDepartmentTestMock.service;

import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.Map;

public interface EmployeeService {

    Employee addEmployee(String firstName, String surName, String lastName, String department, String salary);

    String removeEmployee(String firstName, String surName, String lastName);

    Employee findEmployee(String firstName, String surName, String lastName);

    Map<String, Employee> printAllEmployees();
}
