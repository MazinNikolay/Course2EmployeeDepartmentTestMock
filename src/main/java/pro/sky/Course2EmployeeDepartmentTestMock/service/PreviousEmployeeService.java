package pro.sky.Course2EmployeeDepartmentTestMock.service;

import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.Map;

public interface PreviousEmployeeService {

    Employee addEmployee(String firstName, String surName, String lastName, int department, double salary);

    Employee removeEmployee(String firstName, String surName, String lastName);

    Employee findEmployee(String firstName, String surName, String lastName);

    Map<String, Employee> printAllEmployees();
}
