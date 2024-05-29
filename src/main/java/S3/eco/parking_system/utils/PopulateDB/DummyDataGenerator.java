package S3.eco.parking_system.utils.PopulateDB;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import S3.eco.parking_system.persistence.Repositories.EmployeeRepository;
import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Random;

@SuppressWarnings("FieldCanBeLocal")
@Component
public class DummyDataGenerator implements CommandLineRunner {
    private static final Logger LOGGER = LoggerFactory.getLogger(DummyDataGenerator.class);

    private final AppointmentRepository appointmentRepository;
    private final EmployeeRepository employeeRepository;
    private final Random random;

    // Config options

    // Fixed seed or dynamic one
    private final long seed = 0;
    //long seed = System.currentTimeMillis();

    private final int employeeCount = 20;
    private final int appointmentCount = 1000;
    private final int startHour = 9;
    private final int endHour = 18;
    private final int dayRange = 15;

    private List<LocalDateTime> generatedDates;
    private List<String> generatedPlates;
    private List<String> generatedNames;
    private List<String> generatedEmails;

    private List<EmployeeEntity> generatedEmployees;
    private Collection<AppointmentEntity> generatedAppointments;

    public DummyDataGenerator(AppointmentRepository appointmentRepository,
                              EmployeeRepository employeeRepository) {
        this.appointmentRepository = appointmentRepository;
        this.employeeRepository = employeeRepository;

        this.random = new Random(seed);

        generatedDates = new ArrayList<>();
        generatedPlates = new ArrayList<>();
        generatedNames = new ArrayList<>();
        generatedEmails = new ArrayList<>();
    }

    @Override
    public void run(String...args) {
        LOGGER.info("Populating DB with dummy data");

        // Generators
        AppointmentDateGenerator dateGen = new AppointmentDateGenerator(random, startHour, endHour);
        DutchLicensePlateGenerator plateGen = new DutchLicensePlateGenerator(random);
        UniqueNameGenerator nameGen = new UniqueNameGenerator(random);
        UniqueEmailGenerator emailGen = new UniqueEmailGenerator(random);

        // Generate employee and appointment data together
        LOGGER.info("Generating dummy data");
        generatedDates = new ArrayList<>(dateGen.generateDateTimesAround(LocalDateTime.now(), dayRange, appointmentCount));
        generatedPlates = new ArrayList<>(plateGen.generateUniqueLicensePlates(appointmentCount));
        generatedNames = new ArrayList<>(nameGen.generateUniqueNames(employeeCount + appointmentCount));
        generatedEmails = new ArrayList<>(emailGen.generateUniqueEmails(generatedNames,employeeCount + appointmentCount));

        // Generate objects from dummy data and save in DB
        LOGGER.info("Generating " + employeeCount + " employee" + (employeeCount == 1 ? "" : "s") + "...");
        populateEmployees();
        LOGGER.info("Generating " + appointmentCount + " appointment" + (appointmentCount == 1 ? "" : "s") + "...");
        populateAppointments();

        LOGGER.info("Data population complete");
    }

    private void populateEmployees() {
        generatedEmployees = new ArrayList<>();

        for (int i = 0; i < employeeCount; i++) {
            EmployeeEntity employee = new EmployeeEntity();
            employee.setEmployeeName(generatedNames.get(i));
            employee.setEmployeeEmail(generatedEmails.get(i));
            generatedEmployees.add(employee);
        }
        generatedEmployees = employeeRepository.saveAll(generatedEmployees);
    }

    private void populateAppointments(){
        generatedAppointments = new ArrayList<>();

        for (int i = 0; i < appointmentCount; i++) {
            AppointmentEntity appointment = new AppointmentEntity();
            appointment.setDatetime(generatedDates.get(i));
            appointment.setDescription("dummy description");
            appointment.setCarPlateNumber(generatedPlates.get(i));
            appointment.setEmployee(generatedEmployees.get(random.nextInt(generatedEmployees.size())));

            // These options take names and emails
            appointment.setGuest(generatedNames.get(employeeCount + i));
            appointment.setGuestEmail(generatedEmails.get(employeeCount + i));

            generatedAppointments.add(appointment);
        }
        appointmentRepository.saveAll(generatedAppointments);
    }

    @PreDestroy
    public void cleanup() {
        // I would prefer to only delete the appointments
        // and employees that were autogenerated, but
        // there are cascading side effects, because if manually
        // created appointments depend on dummy employees,
        // they would still be deleted. I tweaked the debug
        // messages to better show this. - András
        LOGGER.info("Cleanup of dummy data triggered.");
        LOGGER.info("Deleting all appointments...");
        appointmentRepository.deleteAll();
        LOGGER.info("Deleting all employees...");
        employeeRepository.deleteAll();
        LOGGER.info("Dummy data deleted.");
    }
}
