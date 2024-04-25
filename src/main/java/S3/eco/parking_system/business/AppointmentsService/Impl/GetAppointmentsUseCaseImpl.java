package S3.eco.parking_system.business.AppointmentsService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Interfaces.GetAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.AppointmentsService.Utils.ConverterAppointmentsData;
import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class GetAppointmentsUseCaseImpl implements GetAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;
    private final ConverterAppointmentsData converter;

    @Override
    public List<AppointmentData> getAll() {
        List<AppointmentEntity> appointments = appointmentRepository.findAll();

        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("No appointments found");
        }

        return converter.convertToAppointmentData(appointments);
    }

    @Override
    public AppointmentData getById(Long id) {
        Optional<AppointmentEntity> optionalAppointment = appointmentRepository.findById(id);
        AppointmentEntity appointmentEntity = optionalAppointment.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for id: " + id));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public AppointmentData getByEmployee(String employee) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByEmployee(employee);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for employee: " + employee));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public AppointmentData getByEmployeeEmail(String employeeEmail) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByEmployeeEmail(employeeEmail);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for employee email: " + employeeEmail));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public AppointmentData getByGuest(String guest) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByGuest(guest);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for guest: " + guest));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public AppointmentData getByGuestEmail(String guestEmail) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByGuestEmail(guestEmail);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for guest email: " + guestEmail));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public AppointmentData getByDatetime(LocalDateTime dateTime) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByDatetime(dateTime);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for datetime: " + dateTime));
        return converter.convertToAppointmentData(appointmentEntity);
    }

    @Override
    public List<AppointmentData> getAppointmentsByYearAndMonth(int year, int month) {
        List<AppointmentEntity> appointments = appointmentRepository.findByDatetimeYearAndDatetimeMonth(year, month);

        if (appointments.isEmpty()) {
            throw new AppointmentNotFoundException("No appointments found for year: " + year + " and month: " + month);
        }

        return appointments.stream()
                .map(converter::convertToAppointmentData)
                .collect(Collectors.toList());
    }

    @Override
    public AppointmentData getByCarPlateNumber(String carPlateNumber) {
        Optional<AppointmentEntity> appointmentOptional = appointmentRepository.findByCarPlateNumber(carPlateNumber);
        AppointmentEntity appointmentEntity = appointmentOptional.orElseThrow(() -> new AppointmentNotFoundException("Appointment not found for car plate number: " + carPlateNumber));
        return converter.convertToAppointmentData(appointmentEntity);
    }

}
