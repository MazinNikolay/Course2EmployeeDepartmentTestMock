package pro.sky.Course2EmployeeDepartmentTestMock.service;

import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.List;
import java.util.Map;

public interface DepartmentService {

    void indexSalaryesForDepartment(String indexValue, String department);

    double sumSalaryInDept(String department);

    double maxSalaryEmployeeInDept(String dept);

    double minSalaryEmployeeInDept(String dept);

    List<Employee> getEmployeesInDepartment(String dept);

    List<Employee> getAllEmployees();

    double avgSalaryForDepartment(String department);

    Map<Integer, List<Employee>> getAllEmployeesFromDepartmentsToMap();
}
