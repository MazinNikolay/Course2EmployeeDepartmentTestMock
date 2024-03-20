package pro.sky.Course2EmployeeDepartmentTestMock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.service.EmployeeService;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping(path = "/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник не найден");
    }

    @GetMapping(path = "/add")
    public Employee add(@RequestParam("firstName") String firstName,
                        @RequestParam("surName") String surName,
                        @RequestParam("lastName") String lastName,
                        @RequestParam("department") int department,
                        @RequestParam("salary") double salary) {
        return employeeService.addEmployee(firstName, surName, lastName, department, salary);
    }

    @GetMapping(path = "/find")
    public Employee find(@RequestParam("firstName") String firstName,
                         @RequestParam("surName") String surName,
                         @RequestParam("lastName") String lastName) {
        return employeeService.findEmployee(firstName, surName, lastName);
    }

    @GetMapping(path = "/remove")
    public Employee remove(@RequestParam("firstName") String firstName,
                           @RequestParam("surName") String surName,
                           @RequestParam("lastName") String lastName) {
        return employeeService.removeEmployee(firstName, surName, lastName);
    }

    @GetMapping
    public List<String> print() {
        List<String> employeeList = new ArrayList<>();
        for (Employee employee : employeeService.printAllEmployees().values()) {
            if (employee != null) {
                employeeList.add(employee.toString());
            }
        }
        return employeeList;
    }
}
