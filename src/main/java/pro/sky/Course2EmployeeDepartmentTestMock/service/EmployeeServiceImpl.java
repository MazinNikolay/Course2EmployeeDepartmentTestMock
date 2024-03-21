package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.springframework.stereotype.Service;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.*;
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
        isValidArgument(firstName, surName, lastName, department, salary);
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
        isValidArgument(firstName, surName, lastName);
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
        isValidArgument(firstName, surName, lastName);
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

    private void isValidArgument(String arg1, String arg2, String arg3, String arg4, String arg5) {
        boolean isNull = arg1 == null || arg2 == null || arg3 == null || arg4 == null || arg5 == null;
        boolean isEmpty = false;
        if (!isNull) {
            isEmpty = arg1.isEmpty() || arg2.isEmpty() || arg3.isEmpty() || arg4.isEmpty() || arg5.isEmpty();
        }
        if (isNull || isEmpty || Integer.parseInt(arg4) < 1) {
            throw new InvalidArgException();
        }
    }

    private void isValidArgument(String arg1, String arg2, String arg3) {
        boolean isNull = arg1 == null || arg2 == null || arg3 == null;
        boolean isEmpty = false;
        if (!isNull) {
            isEmpty = arg1.isEmpty() || arg2.isEmpty() || arg3.isEmpty();
        }
        if (isNull || isEmpty) {
            throw new InvalidArgException();
        }
    }
}
