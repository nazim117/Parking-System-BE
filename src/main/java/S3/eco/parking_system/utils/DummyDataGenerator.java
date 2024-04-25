package S3.eco.parking_system.utils;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class DummyDataGenerator implements CommandLineRunner {
    private final AppointmentRepository appointmentRepository;
    private  ArrayList<String> generatedPlates = new ArrayList<>();
    private ArrayList<String> generatedNames=new ArrayList<>();
    private ArrayList<String> generatedEmails=new ArrayList<>();

    public DummyDataGenerator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String...args) throws Exception{
        System.out.println("Populating dummy data....");
        System.out.println("Sorry for the wait...your pc is a bit old");
        System.out.println("Just like your mom hehexDD ");

        generatedPlates = DutchLicensePlateGenerator.INSTANCE.generateLicensePlates(2300);
        generatedNames = UniqueNameGenerator.INSTANCE.generateUniqueName(2300);
        generatedEmails = UniqueEmailGenerator.INSTANCE.generateUniqueEmails(generatedNames,2300);
        populateDummyData(1000);


        System.out.println("Data population complete!");

    }

    private void populateDummyData(int amountOfRows){
        List<AppointmentEntity> appointments = new ArrayList<>();
        for (int i = 0; i < amountOfRows; i++) {
            AppointmentEntity appointment = new AppointmentEntity();
            appointment.setId((long)1+i);
            appointment.setDatetime(LocalDateTime.now());
            appointment.setGuest(generatedNames.get(i));
            appointment.setEmployee(generatedNames.get(i+1000));
            appointment.setDescription("Тебя недостаточно трахали в детстве\n");
            appointment.setEmployeeEmail(generatedEmails.get(i));
            appointment.setGuestEmail(generatedEmails.get(i+1000));
            appointment.setCarPlateNumber(generatedPlates.get(i+1000));
            appointments.add(appointment);
        }
        appointmentRepository.saveAll(appointments);
        System.out.println("Dummy data populated.");

    }
    @PreDestroy
    public void cleanup() {
        appointmentRepository.deleteAll(); // Clean up on shutdown
        System.out.println("Dummy data deleted.");
    }

}
