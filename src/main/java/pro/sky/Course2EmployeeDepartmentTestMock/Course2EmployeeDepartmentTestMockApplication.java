package pro.sky.Course2EmployeeDepartmentTestMock;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Course2EmployeeDepartmentTestMockApplication {

	public static void main(String[] args) {
		SpringApplication.run(Course2EmployeeDepartmentTestMockApplication.class, args);
		System.out.println("http://localhost:8080/employee/add?firstName=aaa&surName=aab&lastName=aac&department=1&salary=1000");
		System.out.println("http://localhost:8080/employee/add?firstName=bba&surName=bbb&lastName=bbc&department=1&salary=2000");
		System.out.println("http://localhost:8080/employee/add?firstName=cca&surName=ccb&lastName=ccc&department=1&salary=3000");
		System.out.println("http://localhost:8080/department/1/salary/sum");
		System.out.println("http://localhost:8080/department/1/salary/max");
		System.out.println("http://localhost:8080/department/1/salary/min");
		System.out.println("http://localhost:8080/department/1/employees");
		System.out.println("http://localhost:8080/department/employees");
	}

}
