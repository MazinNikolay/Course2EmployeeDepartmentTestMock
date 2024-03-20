package pro.sky.Course2EmployeeDepartmentTestMock.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Invalid data entered")
public class AddedEmloyeeInvalidDataException extends RuntimeException {
    public AddedEmloyeeInvalidDataException(String message) {
        super(message);
    }
}
