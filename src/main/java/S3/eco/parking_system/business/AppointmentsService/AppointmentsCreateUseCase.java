package S3.eco.parking_system.business.AppointmentsService;

import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;

public interface AppointmentsCreateUseCase {
    Long createAppointment(CreateAppointmentRequest request);
}
