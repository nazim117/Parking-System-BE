package S3.eco.parking_system.business.AppointmentsService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.EditAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.EmployeeService.Exceptions.EmployeeNotFoundException;
import S3.eco.parking_system.domain.Appointmets.EditAppointmentRequest;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
public class EditAppointmentsUseCaseImpl implements EditAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public void editAppointment(Long appointmentId, EditAppointmentRequest request) {
        Optional<AppointmentEntity> optionalAppointment = appointmentRepository.findById(appointmentId);
        AppointmentEntity appointment = optionalAppointment
                .orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for id: " + appointmentId));

        Optional<EmployeeEntity> employee = employeeRepository.findByEmployeeEmailAndEmployeeName(request.getEmployeeEmail(), request.getEmployee());
        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Can not find employee with email: " + request.getEmployeeEmail());
        }

        appointment.setDatetime(request.getDatetime());
        appointment.setEmployee(employee.get());
        appointment.setGuest(request.getGuest());
        appointment.setGuestEmail(request.getGuestEmail());
        appointment.setDescription(request.getDescription());
        appointment.setCarPlateNumber(request.getCarPlateNumber());

        appointmentRepository.save(appointment);
    }
}
