package pro.sky.Course2EmployeeDepartmentTestMock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.*;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.service.EmployeeService;

import java.util.Map;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ExceptionHandler(InvalidArgException.class)
    public ResponseEntity<String> handleInvalidArg() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не введены данные, " +
                "либо номер департамента меньше 1");
    }
    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormat() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверно введены аргументы департамента или зарплаты. " +
                "Повторите ввод департамента в формате d, зарплаты в формате d.d");
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник не найден");
    }

    @ExceptionHandler(EmloyeeInvalidDataException.class)
    public ResponseEntity<String> handleAddedEmployeeData() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Некорректно введены данные сотрудника");
    }

    @ExceptionHandler(EmployeeAlreadyAddedException.class)
    public ResponseEntity<String> handleAlreadyAdded() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Сотрудник с такими данными уже существует");
    }

    @ExceptionHandler(EmployeeStorageIsFullException.class)
    public ResponseEntity<String> handleStorageFull() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Превышен лиммит сотрудников");
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam(value = "firstName", required = false) String firstName,
                        @RequestParam(value = "surName", required = false) String surName,
                        @RequestParam(value = "lastName", required = false) String lastName,
                        @RequestParam(value = "department", required = false) String department,
                        @RequestParam(value = "salary", required = false) String salary) {
        return employeeService.addEmployee(firstName, surName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam(value = "firstName", required = false) String firstName,
                         @RequestParam(value = "surName", required = false) String surName,
                         @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.findEmployee(firstName, surName, lastName);
    }

    @GetMapping(path = "/remove")
    public String remove(@RequestParam(value = "firstName", required = false) String firstName,
                         @RequestParam(value = "surName", required = false) String surName,
                         @RequestParam(value = "lastName", required = false) String lastName) {
        return employeeService.removeEmployee(firstName, surName, lastName);
    }

    @GetMapping()
    public Map<String, Employee> printAll() {
        return employeeService.printAllEmployees();
    }
}
