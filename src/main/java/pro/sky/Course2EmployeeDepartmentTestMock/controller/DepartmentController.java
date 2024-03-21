package pro.sky.Course2EmployeeDepartmentTestMock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeAlreadyAddedException;
import pro.sky.Course2EmployeeDepartmentTestMock.model.Employee;
import pro.sky.Course2EmployeeDepartmentTestMock.service.DepartmentService;

import java.util.List;
import java.util.Map;

@RequestMapping(path = "/department")
@RestController
public class DepartmentController {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormat() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверно введены аргументы департамента. " +
                "Повторите ввод департамента в формате int");
    }

    @GetMapping(path = "/{id}/employees")
    public List<Employee> allEmployeesInDepartments(@PathVariable("id") String id) {
        return departmentService.getEmployeesInDepartment(id);
    }

    @GetMapping(path = "/{id}/salary/sum")
    public double sumSalaryInDept(@PathVariable("id") String id) {
        return departmentService.sumSalaryInDept(id);
    }

    @GetMapping(path = "/{id}/salary/max")
    public double maxSalaryEmployeeInDept(@PathVariable("id") String id) {
        return departmentService.maxSalaryEmployeeInDept(id);
    }

    @GetMapping(path = "/{id}/salary/min")
    public double minSalaryEmployeeInDept(@PathVariable("id") String id) {
        return departmentService.minSalaryEmployeeInDept(id);
    }

    @GetMapping(path = "/employees")
    public Map<Integer,List<Employee>> getAllEmployeesFromDepartmentsToMap() {
        return departmentService.getAllEmployeesFromDepartmentsToMap();
    }
}
