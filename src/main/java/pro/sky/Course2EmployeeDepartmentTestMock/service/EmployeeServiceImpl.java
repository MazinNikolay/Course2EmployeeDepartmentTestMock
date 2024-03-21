package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.springframework.stereotype.Service;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.AddedEmloyeeInvalidDataException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeAlreadyAddedException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeStorageIsFullException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.model.EmployeeBook;

import java.util.Collections;
import java.util.Map;

import static org.apache.commons.lang3.StringUtils.isAlpha;

@Service
public class EmployeeServiceImpl implements EmployeeService {
    final private static int MAX_RANGE_EMPLOYEE = 10;

    private final EmployeeBook employeeBook;

    public EmployeeServiceImpl() {
        this.employeeBook = new EmployeeBook();
    }

    @Override
    public Employee addEmployee(String firstName, String surName, String lastName, String department, String salary) {
        checkInputString(firstName, surName, lastName);

        String key = firstName.concat(surName).concat(lastName);
        if (employeeBook.getEmployeesMap().size() >= MAX_RANGE_EMPLOYEE) {
            throw new EmployeeStorageIsFullException();
        } else if (employeeBook.getEmployeesMap().containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(firstName, surName, lastName, Integer.parseInt(department), Double.parseDouble(salary));
        employeeBook.getEmployeesMap().put(key, employee);
        return employee;
    }

    @Override
    public String removeEmployee(String firstName, String surName, String lastName) {
        checkInputString(firstName, surName, lastName);
        String key = firstName.concat(surName).concat(lastName);
        if (!employeeBook.getEmployeesMap().containsKey(key)) {
            throw new EmployeeNotFoundException();
        }
        employeeBook.getEmployeesMap().remove(key);
        return key + " удален";
    }

    @Override
    public Employee findEmployee(String firstName, String surName, String lastName) {
        checkInputString(firstName, surName, lastName);
        String key = firstName.concat(surName).concat(lastName);
        boolean employeeFound = employeeBook.getEmployeesMap().containsKey(key);
        if (!employeeFound) {
            throw new EmployeeNotFoundException();
        }
        return employeeBook.getEmployeesMap().get(key);
    }

    @Override
    public Map<String, Employee> printAllEmployees() {
        return Collections.unmodifiableMap(employeeBook.getEmployeesMap());
    }

    private void checkInputString(String firstName, String surName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(surName) && isAlpha(lastName))) {
            throw new AddedEmloyeeInvalidDataException();
        }
    }
}
