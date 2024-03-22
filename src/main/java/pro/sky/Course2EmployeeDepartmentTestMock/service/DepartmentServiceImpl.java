package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.springframework.stereotype.Service;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.InvalidArgException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {
    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public void indexSalaryesForDepartment(String indexValue, String dept) {
        isValidArgument(indexValue, dept);
        int intDept = Integer.parseInt(dept);
        int intIndexValue = Integer.parseInt(indexValue);
        checkNull(employeeService.printAllEmployees().values());
        employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .forEach(e -> e.setSalary(Math.round(e.getSalary() + e.getSalary() * intIndexValue / 1000)));
    }

    @Override
    public double avgSalaryForDepartment(String dept) {
        isValidArgument(dept);
        int intDept = Integer.parseInt(dept);
        return employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .mapToDouble(e -> e.getSalary())
                .average().orElse(00);
    }

    @Override
    public double sumSalaryInDept(String dept) {
        isValidArgument(dept);
        int intDept = Integer.parseInt(dept);
        return employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .mapToDouble(e -> e.getSalary())
                .sum();
    }

    @Override
    public double maxSalaryEmployeeInDept(String dept) {
        isValidArgument(dept);
        int intDept = Integer.parseInt(dept);
        Employee employee = employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .max(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
        return employee.getSalary();
    }

    @Override
    public double minSalaryEmployeeInDept(String dept) {
        isValidArgument(dept);
        int intDept = Integer.parseInt(dept);
        Employee employee = employeeService.printAllEmployees().values().stream()
                .filter(e -> e.getDepartment() == intDept)
                .min(Comparator.comparingDouble(e -> e.getSalary()))
                .orElseThrow(() -> new EmployeeNotFoundException());
        return employee.getSalary();
    }

    @Override
    public List<Employee> getEmployeesInDepartment(String dept) {
        isValidArgument(dept);
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

    private void isValidArgument(String arg) {
        boolean isNull = arg == null;
        boolean isEmpty = false;
        if (!isNull) {
            isEmpty = arg.isEmpty();
        }
        if (isNull || isEmpty || Integer.parseInt(arg) < 1) {
            throw new InvalidArgException();
        }
    }

    private void isValidArgument(String arg1, String arg2) {
        boolean isNull = arg1 == null || arg2 == null;
        boolean isEmpty = false;
        if (!isNull) {
            isEmpty = arg1.isEmpty() || arg2.isEmpty();
        }
        if (isNull || isEmpty || Integer.parseInt(arg2) < 1) {
            throw new InvalidArgException();
        }
    }

    private void checkNull(Collection<Employee> values) {
        if (values == null) {
            throw new EmployeeNotFoundException();
        }
    }
}