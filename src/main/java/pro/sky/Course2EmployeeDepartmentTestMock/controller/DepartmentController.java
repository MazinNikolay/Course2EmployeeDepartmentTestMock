package pro.sky.Course2EmployeeDepartmentTestMock.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.EmployeeNotFoundException;
import pro.sky.Course2EmployeeDepartmentTestMock.exception.InvalidArgException;
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

    @ExceptionHandler(InvalidArgException.class)
    public ResponseEntity<String> handleInvalidArg() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Не введен номер департамента, " +
                "либо номер департамента меньше 1");
    }

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<String> handleNumberFormat() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Неверно введен номер департамента. " +
                "Повторите ввод номера департамента в формате d");
    }

    @ExceptionHandler(EmployeeNotFoundException.class)
    public ResponseEntity<String> handleNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Сотрудники не найдены");
    }

    @GetMapping(path = "/{id}/{indexValue}/salary/index")
    public void indexSalaryesForDepartment(@PathVariable("indexValue") String indexValue, @PathVariable("id") String id) {
        departmentService.indexSalaryesForDepartment(indexValue, id);
    }

    @GetMapping(path = "/{id}/salary/avg")
    public double avgSalaryForDepartment(@PathVariable("id") String id) {
        return departmentService.avgSalaryForDepartment(id);
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
    public Map<Integer, List<Employee>> getAllEmployeesFromDepartmentsToMap() {
        return departmentService.getAllEmployeesFromDepartmentsToMap();
    }
}
