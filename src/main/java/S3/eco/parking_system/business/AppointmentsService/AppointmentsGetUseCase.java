package S3.eco.parking_system.business.AppointmentsService;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;

import java.time.LocalDateTime;
import java.util.List;

public interface AppointmentsGetUseCase {
    List<AppointmentData> getAll();

    AppointmentData getById(Long aLong);

    AppointmentData getByEmployee(String employee);

    AppointmentData getByEmployeeEmail(String employeeEmail);

    AppointmentData getByGuest(String guest);

    AppointmentData getByGuestEmail(String guestEmail);

    AppointmentData getByDatetime(LocalDateTime dateTime);

    AppointmentData getByCarPlateNumber(String carPlateNumber);
}
