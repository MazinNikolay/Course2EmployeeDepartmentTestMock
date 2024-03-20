package pro.sky.Course2EmployeeDepartmentTestMock.model;

import java.util.HashMap;
import java.util.Map;

public class EmployeeBook {
    private final Map<String, Employee> employeesMap;

    public EmployeeBook() {
        this.employeesMap = new HashMap<>();
    }

    public Map<String, Employee> getEmployeesMap() {
        return employeesMap;
    }
}
