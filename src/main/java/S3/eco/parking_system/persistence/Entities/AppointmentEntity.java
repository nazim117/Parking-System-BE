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

    @Column(name = "datetime")
    private LocalDateTime datetime;

    @Column
    private String guest;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "employee_id", referencedColumnName = "id")
    private EmployeeEntity employee;

    @Column(name = "guest_email")
    private String guestEmail;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "car_plate_number")
    private String carPlateNumber;
}