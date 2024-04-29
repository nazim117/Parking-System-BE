package S3.eco.parking_system.business.AppointmentsService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.CreateAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentAlreadyExistsException;
import S3.eco.parking_system.business.EmployeeService.Exceptions.EmployeeNotFoundException;
import S3.eco.parking_system.domain.Appointmets.CreateAppointmentRequest;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@AllArgsConstructor
@Service
public class CreateAppointmentsUseCaseImpl implements CreateAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;

    @Override
    public Long createAppointment(CreateAppointmentRequest request) {
        if (appointmentRepository.existsByDatetimeAndEmployeeEmailAndGuest(request.getDatetime(), request.getEmployeeEmail(), request.getGuest())) {
            throw new AppointmentAlreadyExistsException("Appointment already exists for this datetime and employee");
        }

        saveNewAppointment(request);
        return appointmentRepository
                .findAllByDatetimeAndEmployeeEmailAndGuest(request.getDatetime(), request.getEmployeeEmail(), request.getGuest()).getId();
    }

    private void saveNewAppointment(CreateAppointmentRequest request) {
        Optional<EmployeeEntity> employee = employeeRepository.findByEmployeeEmailAndEmployeeName(request.getEmployeeEmail(), request.getEmployee());

        if (employee.isEmpty()) {
            throw new EmployeeNotFoundException("Can not find employee with email and name: " + request.getEmployeeEmail() + ", " + request.getEmployee());
        }

        AppointmentEntity newAppointment = AppointmentEntity.builder()
                .datetime(request.getDatetime())
                .employee(employee.get())
                .guest(request.getGuest())
                .guestEmail(request.getGuestEmail())
                .description(request.getDescription())
                .carPlateNumber(request.getCarPlateNumber())
                .build();
        appointmentRepository.save(newAppointment);
    }
}
