package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.springframework.stereotype.Service;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.AddedEmloyeeInvalidDataException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.StringUtils.*;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void indexSalaryesForDepartment(int indexValue, int dept) {
        employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == dept)
                .forEach(e -> e.setSalary(Math.round(e.getSalary() + e.getSalary() * indexValue / 1000)));
    }

    @Override
    public double avgSalaryForDepartment(String dept) {
        int intDept = Integer.parseInt(dept);
        return employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .mapToDouble(e -> e.getSalary())
                .average().orElse(00);
    }

    @Override
    public double sumSalaryInDept(String dept) {
        int intDept = Integer.parseInt(dept);
        return employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .mapToDouble(e -> e.getSalary())
                .sum();
    }

    @Override
    public double maxSalaryEmployeeInDept(String dept) {
        int intDept = Integer.parseInt(dept);
        Employee employee = employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
        return employee.getSalary();
    }

    @Override
    public double minSalaryEmployeeInDept(String dept) {
        int intDept = Integer.parseInt(dept);
        Employee employee = employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
        return employee.getSalary();
    }

    @Override
    public List<Employee> getEmployeesInDepartment(String dept) {
        int intDept = Integer.parseInt(dept);
        return employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .collect(Collectors.toList());
    }

    @Override
    public Map<Integer, List<Employee>> getAllEmployeesFromDepartmentsToMap() {
        return employeeService.printAllEmployees().values().stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }

    @Override
    public List<Employee> getAllEmployees() {
        return employeeService.printAllEmployees().values().stream()
                .sorted(Comparator.comparingInt(e -> e.getDepartment()))
                .collect(Collectors.toList());
    }

    private void checkInputString(String firstName, String surName, String lastName) {
        if (!(isAlpha(firstName) && isAlpha(surName) && isAlpha(lastName))) {
            throw new AddedEmloyeeInvalidDataException();
        }
    }
}