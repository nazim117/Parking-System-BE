package S3.eco.parking_system.domain.Appointmets;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Builder
public class CreateAppointmentRequest {
    private LocalDateTime datetime;

    private String guest;

    private String employee;

    private String guestEmail;

    private String employeeEmail;

    private String description;

    private String carPlateNumber;
}
