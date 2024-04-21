package S3.eco.parking_system.business.AppointmentsService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;

import java.time.LocalDateTime;
import java.util.List;

public interface GetAppointmentsUseCase {
    List<AppointmentData> getAll();

    AppointmentData getById(Long aLong);

    AppointmentData getByEmployee(String employee);

    AppointmentData getByEmployeeEmail(String employeeEmail);

    AppointmentData getByGuest(String guest);

    AppointmentData getByGuestEmail(String guestEmail);

    AppointmentData getByDatetime(LocalDateTime dateTime);

    List<AppointmentData> getAppointmentsByYearAndMonth(int year, int month);

    AppointmentData getByCarPlateNumber(String carPlateNumber);
}
