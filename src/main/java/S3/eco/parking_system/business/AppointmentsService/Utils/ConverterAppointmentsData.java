package S3.eco.parking_system.business.AppointmentsService.Utils;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@AllArgsConstructor
public class ConverterAppointmentsData {
    public AppointmentData convertToAppointmentData(AppointmentEntity entity) {
        return AppointmentData.builder()
                .id(entity.getId())
                .datetime(entity.getDatetime())
                .employeeId(entity.getEmployee().getId())
                .employee(entity.getEmployee().getEmployeeName())
                .employeeEmail(entity.getEmployee().getEmployeeEmail())
                .guest(entity.getGuest())
                .guestEmail(entity.getGuestEmail())
                .carPlateNumber(entity.getCarPlateNumber())
                .description(entity.getDescription())
                .build();
    }

    public List<AppointmentData> convertToAppointmentData(List<AppointmentEntity> entities) {
        List<AppointmentData> appointmentDataLst = new ArrayList<>();

        for (AppointmentEntity entity : entities) {
            appointmentDataLst.add(AppointmentData.builder()
                    .id(entity.getId())
                    .datetime(entity.getDatetime())
                    .employeeId(entity.getEmployee().getId())
                    .employee(entity.getEmployee().getEmployeeName())
                    .employeeEmail(entity.getEmployee().getEmployeeEmail())
                    .guest(entity.getGuest())
                    .guestEmail(entity.getGuestEmail())
                    .carPlateNumber(entity.getCarPlateNumber())
                    .description(entity.getDescription())
                    .build());
        }

        return appointmentDataLst;
    }
}
