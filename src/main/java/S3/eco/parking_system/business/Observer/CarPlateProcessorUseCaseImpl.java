package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.business.NotificationsService.Interfaces.EmailSenderUseCase;
import S3.eco.parking_system.microservices.PlateDetection.CarPlateProcessorUseCase;
import S3.eco.parking_system.microservices.Arduino.SensorDataProcessorUseCase;
import S3.eco.parking_system.microservices.PlateDetection.PlateDetectionReceiverUseCaseImpl;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.util.Optional;

@Component
public class CarPlateProcessorUseCaseImpl implements CarPlateProcessorUseCase {
    private static final Logger LOGGER = LoggerFactory.getLogger(CarPlateProcessorUseCaseImpl.class);
    private final AppointmentRepository appointmentRepository;
    private final EmailSenderUseCase emailSenderUseCase;
    private final SensorDataProcessorUseCase sensorDataProcessorUseCase;

    private static final long DEBOUNCE_TIME = 15_000; // Debounce time in milliseconds
    private long lastEntryTime = 0;
    private long lastExitTime = 0;

    public CarPlateProcessorUseCaseImpl(AppointmentRepository appointmentRepository,
                                        EmailSenderUseCase emailSenderUseCase,
                                        SensorDataProcessorUseCase sensorDataProcessorUseCase) {
        this.appointmentRepository = appointmentRepository;
        this.emailSenderUseCase = emailSenderUseCase;
        this.sensorDataProcessorUseCase = sensorDataProcessorUseCase;
    }

    @Value("${appointments.timeZone}")
    private String timeZone;

    @Value("${appointments.checkHours}")
    private int checkHours;

    @Value("${parking.spaces}")
    private int parkingSpaces;

    @Override
    public void process(String carPlateNumber) {
        long currentTime = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now(ZoneId.of(timeZone));
        LocalDateTime fourHoursLater = now.plusHours(checkHours);
        Optional<AppointmentEntity> appointmentEntity = appointmentRepository
                .findByCarPlateNumberAndTimeRange(carPlateNumber, now, fourHoursLater);

        // SensorDataProcessorUseCase.getCounter() < parkingSpaces
        // Think about this line
        LOGGER.info(String.valueOf(currentTime - lastEntryTime));
        if (appointmentEntity.isPresent() && currentTime - lastEntryTime > DEBOUNCE_TIME) {
            lastEntryTime = currentTime;
            String employeeEmail = appointmentEntity.get().getEmployee().getEmployeeEmail();
            String employeeName = appointmentEntity.get().getEmployee().getEmployeeName();

            LocalDateTime meetingTime = appointmentEntity.get().getDatetime();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
            meetingTime.format(formatter);

            emailSenderUseCase.sendEmail(employeeEmail, "Appointment",
                    "Client" + employeeName + "for appointment at "
                            + meetingTime + " is parking");
        }
    }
}
