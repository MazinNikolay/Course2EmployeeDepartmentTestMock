package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.InvalidArgException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {
    private DepartmentService out;
    @Mock
    private EmployeeService employeeServiceMock;

    @BeforeEach
    public void initVariable() {
        out = new DepartmentServiceImpl(employeeServiceMock);
    }

    private Map<String, Employee> employeeMap = new HashMap<>(Map.of(
            "IvanovIvanIvanovich", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 20_000),
            "IvanovIvanIvanovic", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 30_000),
            "IvanovIvanIvanovi", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvanov", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvano", new Employee("Ivanov", "Ivan", "Ivanovich",
                    2, 10_000),
            "IvanovIvanIvan", new Employee("Ivanov", "Ivan", "Ivanovich",
                    2, 10_000),
            "IvanovIvanIva", new Employee("Ivanov", "Ivan", "Ivanovich",
                    2, 10_000),
            "IvanovIvanIv", new Employee("Ivanov", "Ivan", "Ivanovich",
                    3, 10_000),
            "IvanovIvanI", new Employee("Ivanov", "Ivan", "Ivanovich",
                    3, 10_000),
            "IvanovIvan", new Employee("Ivanov", "Ivan", "Ivanovich",
                    4, 10_000))
    );

    @Test
    void indexSalaryCorrect() {
        double salary = 20_000;
        int indexValue = 5;
        String employeeKey = "IvanovIvanIvanovich";
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        double expectedSalary = Math.round(salary + salary * indexValue / 1000);
        out.indexSalaryesForDepartment("5", "1");
        double actualSalary = employeeServiceMock.printAllEmployees().get(employeeKey).getSalary();
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void indexSalaryNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.indexSalaryesForDepartment("ada", "1"));
    }

    @Test
    void indexSalaryWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.indexSalaryesForDepartment("1", ""));
    }

    @Test
    void avgSalaryCorrect() {
        double salarySum = 70_000;
        int employeeCount = 4;
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        double expectedSalary = salarySum / employeeCount;
        double actualSalary = out.avgSalaryForDepartment("1");
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void avgSalaryEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        double actualSalary = out.avgSalaryForDepartment("1");
        assertEquals(0.0, actualSalary);
    }

    @Test
    void avgSalaryNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.avgSalaryForDepartment("ada"));
    }

    @Test
    void avgSalaryWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.avgSalaryForDepartment(""));
    }

    @Test
    void sumSalaryCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        double expectedSalary = 70_000;
        double actualSalary = out.sumSalaryInDept("1");
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void sumSalaryEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        double actualSalary = out.sumSalaryInDept("1");
        assertEquals(0.0, actualSalary);
    }

    @Test
    void sumSalaryNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.sumSalaryInDept("ada"));
    }

    @Test
    void sumSalaryWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.sumSalaryInDept(""));
    }

    @Test
    void maxSalaryCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        double expectedSalary = 30_000;
        double actualSalary = out.maxSalaryEmployeeInDept("1");
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void maxSalaryEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        assertThrows(EmployeeNotFoundException.class, () -> out.maxSalaryEmployeeInDept("1"));
    }

    @Test
    void maxSalaryNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.maxSalaryEmployeeInDept("ada"));
    }

    @Test
    void maxSalaryWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.maxSalaryEmployeeInDept(""));
    }

    @Test
    void minSalaryCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        double expectedSalary = 10_000;
        double actualSalary = out.minSalaryEmployeeInDept("1");
        assertEquals(expectedSalary, actualSalary);
    }

    @Test
    void minSalaryEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        assertThrows(EmployeeNotFoundException.class, () -> out.minSalaryEmployeeInDept("1"));
    }

    @Test
    void minSalaryNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.minSalaryEmployeeInDept("ada"));
    }

    @Test
    void minSalaryWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.minSalaryEmployeeInDept(""));
    }

    @Test
    void getEmployeesInDepartmentCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        List<Employee> expectedList = employeeMap.values().stream()
                .filter(e -> e.getDepartment() == 1)
                .collect(Collectors.toList());
        List<Employee> actualList = out.getEmployeesInDepartment("1");
        assertEquals(expectedList, actualList);
    }

    @Test
    void getEmployeesInDepartmentEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        List<Employee> expectedList = new ArrayList<>();
        List<Employee> actualList = out.getEmployeesInDepartment("1");
        assertEquals(expectedList, actualList);
    }

    @Test
    void getEmployeesInDepartmentNoNumberFormatInput() {
        assertThrows(NumberFormatException.class, () -> out.getEmployeesInDepartment("ada"));
    }

    @Test
    void getEmployeesInDepartmentWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.getEmployeesInDepartment(""));
    }

    @Test
    void getAllEmployeesFromDepartmentsToMapCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        Map<Integer, List<Employee>> expectedMap = employeeMap.values().stream()
                .collect(Collectors.groupingBy(e -> e.getDepartment()));
        Map<Integer, List<Employee>> actualMap = out.getAllEmployeesFromDepartmentsToMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void getAllEmployeesFromDepartmentsToMapEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        Map<Integer, List<Employee>> expectedMap = new HashMap<>();
        Map<Integer, List<Employee>> actualMap = out.getAllEmployeesFromDepartmentsToMap();
        assertEquals(expectedMap, actualMap);
    }

    @Test
    void getAllEmployeesCorrect() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(employeeMap);
        List<Employee> expectedList = employeeMap.values().stream()
                .sorted(Comparator.comparingInt(e -> e.getDepartment()))
                .collect(Collectors.toList());
        List<Employee> actualList = out.getAllEmployees();
        assertEquals(expectedList, actualList);
    }

    @Test
    void getAllEmployeesEmptyMap() {
        when(employeeServiceMock.printAllEmployees()).thenReturn(Collections.emptyMap());
        List<Employee> expectedList = new ArrayList<>();
        List<Employee> actualList = out.getAllEmployees();
        assertEquals(expectedList, actualList);
    }
}