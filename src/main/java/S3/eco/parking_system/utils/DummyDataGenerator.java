package S3.eco.parking_system.utils;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import jakarta.annotation.PreDestroy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Component
public class DummyDataGenerator implements CommandLineRunner {
    private final AppointmentRepository appointmentRepository;

    public DummyDataGenerator(AppointmentRepository appointmentRepository) {
        this.appointmentRepository = appointmentRepository;
    }

    @Override
    public void run(String...args) throws Exception{
        System.out.println("Populating dummy data....");
        System.out.println("Sorry for the wait...your pc is a bit old");
        System.out.println("Just like your mom hehexDD ");
        populateDummyData();
        System.out.println("Data population complete!");

    }

    private void populateDummyData(){
        List<AppointmentEntity> appointments = new ArrayList<>();
        for (int i = 0; i < 1000; i++) {
            AppointmentEntity appointment = new AppointmentEntity();
            appointment.setId((long)1+i);
            appointment.setDatetime(LocalDateTime.now());
            appointment.setGuest("Your mom :)" + " "+ i);
            appointment.setEmployee("ох ты, маленькая грязная сучка" + " " + i);
            appointment.setDescription("Тебя недостаточно трахали в детстве\n");
            appointment.setEmployeeEmail("pepetheassassin@pepe" + " " +i);
            appointment.setGuestEmail("bigmusclemanystronk@stronk " + i);
            appointment.setCarPlateNumber("N A S A " + " " + i);
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
