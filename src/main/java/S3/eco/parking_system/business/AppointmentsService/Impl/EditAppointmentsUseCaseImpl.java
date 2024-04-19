package S3.eco.parking_system.business.AppointmentsService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.EditAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class EditAppointmentsUseCaseImpl implements EditAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;

    @Override
    public void editAppointment(Long appointmentId, EditAppointmentRequest request) {
        AppointmentEntity appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found with id: " + appointmentId));

        appointment.setDatetime(request.getDatetime());
        appointment.setEmployee(request.getEmployee());
        appointment.setEmployeeEmail(request.getEmployeeEmail());
        appointment.setGuest(request.getGuest());
        appointment.setGuestEmail(request.getGuestEmail());
        appointment.setDescription(request.getDescription());
        appointment.setCarPlateNumber(request.getCarPlateNumber());

        appointmentRepository.save(appointment);
    }
}
