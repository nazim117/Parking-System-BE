package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.microservices.ArduinoSerialReaderUseCaseImpl;
import S3.eco.parking_system.microservices.CarPlateProcessorUseCase;
import S3.eco.parking_system.microservices.SensorDataProcessorUseCase;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Component
@AllArgsConstructor
public class CarPlateProcessorUseCaseImpl implements CarPlateProcessorUseCase {
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final SensorDataProcessorUseCase sensorDataProcessorUseCase;
    @Override
    public void process(String carPlateNumber) {
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository.findByCarPlateNumber(carPlateNumber);

        if (appointmentEntity.isPresent() && sensorDataProcessorUseCase.getCounter() < 3) {
            String employeeEmail = appointmentEntity.get().getEmployee().getEmployeeEmail();
            // notification logic and check if email has been already sent
        }
    }
}
