package S3.eco.parking_system.business.AppointmentsService;

import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;

public interface AppointmentsEditUseCase {
    void editAppointment(Long appointmentId, EditAppointmentRequest request);
}
