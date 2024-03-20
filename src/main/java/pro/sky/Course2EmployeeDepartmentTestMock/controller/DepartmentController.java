package pro.sky.Course2EmployeeDepartmentTestMock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.service.EmployeeService;

import java.util.List;

@RestController
@RequestMapping(path = "/departments")
public class DepartmentController {
    private final EmployeeService employeeService;

    public DepartmentController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудник не найден");
    }

    @GetMapping(path = "/max-salary")
    public Employee maxSalaryEmployeeInDept(@RequestParam("departmentId") int departmentId) {
        return employeeService.maxSalaryEmployeeInDept(departmentId);
    }

    @GetMapping(path = "/min-salary")
    public Employee minSalaryEmployeeInDept(@RequestParam("departmentId") int departmentId) {
        return employeeService.minSalaryEmployeeInDept(departmentId);
    }

    @GetMapping(path = "/all")
    public List<Employee> allEmployeesInDepartments(@RequestParam(value = "departmentId") int departmentId) {
        return employeeService.employeesInDepartment(departmentId);
    }

    @GetMapping
    public List<Employee> allEmployeesInDepartments() {
        return employeeService.allEmployeesInDepartments();
    }
}
