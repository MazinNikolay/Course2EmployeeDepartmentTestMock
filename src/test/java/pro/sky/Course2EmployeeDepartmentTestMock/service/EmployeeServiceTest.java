package pro.sky.Course2EmployeeDepartmentTestMock.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.*;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.model.EmployeeBook;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class EmployeeServiceTest {

    private EmployeeService out;
    private Map<String, Employee> employeeMapEmpty = new HashMap<>();
    private Map<String, Employee> employeeMap = new HashMap<>(Map.of(
            "IvanovIvanIvanovich", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvanovic", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvanovi", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvanov", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvano", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIvan", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIva", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanIv", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvanI", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000),
            "IvanovIvan", new Employee("Ivanov", "Ivan", "Ivanovich",
                    1, 10_000))
    );
    private final List<String> CORRECT_EMPLOYEE = Arrays.asList("ivanov", "ivan", "Ivanovich", "1", "10000");
    private final Employee CORRECT_EMPLOYEE_CLASS = new Employee("ivanov", "ivan", "Ivanovich",
            1, 10_000);

    @Mock
    private EmployeeBook employeeBookMock;

    @BeforeEach
    public void initVariables() {
        out = new EmployeeServiceImpl(employeeBookMock);
    }

    @Test
    void addedEmployeeCorrect() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMapEmpty);
        Employee expectedEmployee = CORRECT_EMPLOYEE_CLASS;
        Employee actualEmployee = out.addEmployee("Ivanov", "Ivan", "Ivanovich",
                "1", "10000");
        assertEquals(expectedEmployee, actualEmployee);
    }

    @Test
    void addedEmployeeWithIllegalArgument() {
        assertThrows(EmloyeeInvalidDataException.class, () -> out.addEmployee("Ivanov", "11",
                "Ivanovich", "1", "10000"));
    }

    @Test
    void addedEmployeeWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.addEmployee("Ivanov", "Ivan",
                "Ivanovich", "1", ""));
    }

    @Test
    void addedEmployeeStorageIsFull() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMap);
        assertThrows(EmployeeStorageIsFullException.class, () -> out.addEmployee("Ivanov", "Ivan",
                "Ivanovich", "1", "10000"));
    }

    @Test
    void addedEmployeeAlreadyExist() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMapEmpty);
        out.addEmployee("Ivanov", "Ivan", "Ivanovich", "1", "10000");
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.addEmployee("Ivanov", "Ivan",
                "Ivanovich", "1", "10000"));
    }

    @Test
    void removedEmployeeCorrect() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMap);
        String expectedResult = "IvanovIvanIvanovich удален";
        String actualResult = out.removeEmployee("Ivanov", "Ivan", "Ivanovich");
        assertEquals(expectedResult, actualResult);
    }

    @Test
    void removedEmployeeWithIllegalArgument() {
        assertThrows(EmloyeeInvalidDataException.class, () -> out.removeEmployee("Ivanov", "11",
                "Ivanovich"));
    }

    @Test
    void removedEmployeeWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.removeEmployee("Ivanov", "",
                "Ivanovich"));
    }

    @Test
    void removedEmployeeNotFound() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMap);
        assertThrows(EmployeeNotFoundException.class, () -> out.removeEmployee("Ivanov", "Petr",
                "Ivanovich"));
    }

    @Test
    void foundEmployeeCorrect() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMap);
        Employee expectedEmployee = CORRECT_EMPLOYEE_CLASS;
        Employee actualResult = out.findEmployee("Ivanov", "Ivan", "Ivanovich");
        assertEquals(expectedEmployee, actualResult);
    }

    @Test
    void foundEmployeeWithIllegalArgument() {
        assertThrows(EmloyeeInvalidDataException.class, () -> out.findEmployee("Ivanov", "11",
                "Ivanovich"));
    }

    @Test
    void foundEmployeeWithoutArgument() {
        assertThrows(InvalidArgException.class, () -> out.findEmployee("Ivanov", "",
                "Ivanovich"));
    }

    @Test
    void foundEmployeeNotFound() {
        when(employeeBookMock.getEmployeesMap()).thenReturn(employeeMap);
        assertThrows(EmployeeNotFoundException.class, () -> out.findEmployee("Ivanov", "Petr",
                "Ivanovich"));
    }

    @Test
    void printAllEmployees() {
    }
}