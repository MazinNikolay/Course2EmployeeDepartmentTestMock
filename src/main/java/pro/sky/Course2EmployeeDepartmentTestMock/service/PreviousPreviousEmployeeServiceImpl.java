package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.springframework.stereotype.Service;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeAlreadyAddedException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeStorageIsFullException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.model.EmployeeBook;

import java.util.Collections;
import java.util.Map;

@Service
public class PreviousPreviousEmployeeServiceImpl implements PreviousEmployeeService {
    final private static int MAX_RANGE_EMPLOYEE = 10;

    private final EmployeeBook employeeBook;

    public PreviousPreviousEmployeeServiceImpl() {
        this.employeeBook = new EmployeeBook();
    }

    @Override
    public Employee addEmployee(String firstName, String surName, String lastName, int department, double salary) {
        String key = firstName.concat(surName).concat(lastName);
        if (employeeBook.getEmployeesMap().size() >= MAX_RANGE_EMPLOYEE) {
            throw new EmployeeStorageIsFullException("Превышен лимит количества сотрудников в фирме");
        } else if (employeeBook.getEmployeesMap().containsKey(key)) {
            throw new EmployeeAlreadyAddedException("Сотрудник с такими данными уже добавлен");
        }
        Employee employee = new Employee(firstName, surName, lastName, department, salary);
        employeeBook.getEmployeesMap().put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String surName, String lastName) {
        String key = firstName.concat(surName).concat(lastName);
        if (!employeeBook.getEmployeesMap().containsKey(key)) {
            throw new EmployeeNotFoundException("Сотрудник с заданными данными не найден");
        }
        employeeBook.getEmployeesMap().remove(key);
        return employeeBook.getEmployeesMap().get(key);
    }

    @Override
    public Employee findEmployee(String firstName, String surName, String lastName) {
        String key = firstName.concat(surName).concat(lastName);
        boolean employeeFound = employeeBook.getEmployeesMap().containsKey(key);
        if (!employeeFound) {
            throw new EmployeeNotFoundException("Сотрудник с заданными данными не найден");
        }
        return employeeBook.getEmployeesMap().get(key);
    }

    @Override
    public Map<String, Employee> printAllEmployees() {
        return Collections.unmodifiableMap(employeeBook.getEmployeesMap());
    }
}
