package S3.eco.parking_system.business.AppointmentsService.Exceptions;

public class AppointmentAlreadyExistsException extends RuntimeException {
    public AppointmentAlreadyExistsException(String message) {
        super(message);
    }
}
