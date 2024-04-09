package S3.eco.parking_system.domain.Appointmets;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class AppointmentData {
    private Long id;

    private LocalDateTime datetime;

    private String guest;

    private String employee;

    private String guestEmail;

    private String employeeEmail;

    private String description;

    private String carPlateNumber;
}
