package S3.eco.parking_system.persistence.Entities;

import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "appointments")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private LocalDateTime datetime;

    @Column
    private String guest;

    @Column
    private String employee;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(name = "employee_email")
    private String employeeEmail;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "car_plate_number")
    private String carPlateNumber;
}