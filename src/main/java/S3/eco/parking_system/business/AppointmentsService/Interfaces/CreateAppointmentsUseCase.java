package S3.eco.parking_system.business.AppointmentsService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;

public interface CreateAppointmentsUseCase {
    Long createAppointment(CreateAppointmentRequest request);
}
