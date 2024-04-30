package S3.eco.parking_system.business.EmployeeService.Exceptions;

public class EmployeeAlreadyExistsException extends RuntimeException {
    public EmployeeAlreadyExistsException(String message) {
        super(message);
    }
}
