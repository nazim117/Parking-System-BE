package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.business.NotificationsService.Interfaces.EmailSenderUseCase;
import S3.eco.parking_system.microservices.PlateDetection.CarPlateProcessorUseCase;
import S3.eco.parking_system.microservices.Arduino.SensorDataProcessorUseCase;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.utils.EmailMessages;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
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
    private int currentCarAmount = 0;
    @Value("${appointments.timeZone}")
    private ZoneId timeZone;
    @Value("${appointments.checkHours}")
    private int checkHours;
    @Value("${parking.spaces}")
    private int parkingSpaces;

    public CarPlateProcessorUseCaseImpl(AppointmentRepository appointmentRepository,
                                        EmailSenderUseCase emailSenderUseCase,
                                        SensorDataProcessorUseCase sensorDataProcessorUseCase) {
        this.appointmentRepository = appointmentRepository;
        this.emailSenderUseCase = emailSenderUseCase;
        this.sensorDataProcessorUseCase = sensorDataProcessorUseCase;
        currentCarAmount = sensorDataProcessorUseCase.getCounter();
    }

    @Override
    public void process(String carPlateNumber) {
        int newCarAmount = sensorDataProcessorUseCase.getCounter();

        long currentTime = System.currentTimeMillis();
        LocalDateTime now = LocalDateTime.now(timeZone);
        LocalDateTime fourHoursLater = now.plusHours(checkHours);

        Optional<AppointmentEntity> appointmentEntity = appointmentRepository
                .findByCarPlateNumberAndTimeRange(carPlateNumber, now, fourHoursLater);

        if(appointmentEntity.isEmpty()) {
            return;
        }
        String employeeEmail = appointmentEntity.get().getEmployee().getEmployeeEmail();
        String employeeName = appointmentEntity.get().getEmployee().getEmployeeName();
        String guestName = appointmentEntity.get().getGuest();

        //When parking is full notify guest that they have to go to another parking
        if(newCarAmount == parkingSpaces){
            sensorDataProcessorUseCase.setCounter(currentCarAmount);
            String emailBody = EmailMessages.APPOINTMENT_NO_PARKING_SPACE_BODY
                    .replace("${guestName}", guestName);

            emailSenderUseCase.sendEmail(employeeEmail, EmailMessages.APPOINTMENT_NO_PARKING_SPACE_SUBJECT, emailBody);
        }else if(currentCarAmount < newCarAmount){ //Check if car enters parking
            currentCarAmount = newCarAmount;

            if((currentTime - lastEntryTime) > DEBOUNCE_TIME){ //Check if email has already been sent
                lastEntryTime = currentTime;

                LocalDateTime meetingTime = appointmentEntity.get().getDatetime();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss");
                meetingTime.format(formatter);

                String emailBody = EmailMessages.APPOINTMENT_REMINDER_EMPLOYEE_BODY
                                .replace("${employeeName}", employeeName)
                                .replace("${guestName}", guestName)
                                .replace("${meetingTime}", meetingTime.toLocalTime().toString())
                                .replace("${meetingDate}", meetingTime.toLocalDate().toString());

                emailSenderUseCase.sendEmail(employeeEmail, EmailMessages.APPOINTMENT_REMINDER_EMPLOYEE_SUBJECT, emailBody);
            }
        }

    }
}
