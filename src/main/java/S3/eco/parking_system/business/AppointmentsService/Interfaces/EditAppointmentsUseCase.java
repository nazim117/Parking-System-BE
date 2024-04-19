package S3.eco.parking_system.business.AppointmentsService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;

public interface EditAppointmentsUseCase {
    void editAppointment(Long appointmentId, EditAppointmentRequest request);
}
