package S3.eco.parking_system.utils.PopulateDB;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataGenerator implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyDataGenerator.class);
    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private ArrayList<LocalDateTime> generatedDates = new ArrayList<>();
    private  ArrayList<String> generatedPlates = new ArrayList<>();
    private ArrayList<String> generatedNames=new ArrayList<>();
    private ArrayList<String> generatedEmails=new ArrayList<>();

    public DummyDataGenerator(AppointmentRepository appointmentRepository,
                              EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;
    }

    @Override
    public void run(String...args) throws Exception{
        LOGGER.info("Populating DB with dummy data");

        generatedDates = DateGenerator.INSTANCE.generateDateTime(1000);
        generatedPlates = DutchLicensePlateGenerator.INSTANCE.generateLicensePlates(2300);
        generatedNames = UniqueNameGenerator.INSTANCE.generateUniqueName(2300);
        generatedEmails = UniqueEmailGenerator.INSTANCE.generateUniqueEmails(generatedNames,2300);
        populateDummyData(1000);

        LOGGER.info("Data population complete");
    }

    private void populateDummyData(int amountOfRows){
        List<EmployeeEntity> employees = new ArrayList<>();
        List<AppointmentEntity> appointments = new ArrayList<>();

        for (int i = 0; i < amountOfRows; i++) {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setId((long)1+i);
            employee.setEmployeeName(generatedNames.get(i+1000));
            employee.setEmployeeEmail(generatedEmails.get(i));
            employees.add(employee);
        }
        employeeRepository.saveAll(employees);

        for (int i = 0; i < amountOfRows; i++) {
            AppointmentEntity appointment = new AppointmentEntity();
            appointment.setId((long)1+i);
            appointment.setDatetime(generatedDates.get(i));
            appointment.setGuest(generatedNames.get(i));
            appointment.setEmployee(employees.get(i));
            appointment.setDescription("dummy description");
            appointment.setGuestEmail(generatedEmails.get(i+1000));
            appointment.setCarPlateNumber(generatedPlates.get(i+1000));
            appointments.add(appointment);
        }
        appointmentRepository.saveAll(appointments);
    }

    @PreDestroy
    public void cleanup() {
        appointmentRepository.deleteAll(); // Clean up on shutdown
        System.out.println("Dummy data deleted.");
    }
}
