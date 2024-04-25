package S3.eco.parking_system.business.AppointmentsService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import org.springframework.data.domain.Page;

import java.util.List;

public interface GetPaginatedAppointmentsUseCase {
    Page<AppointmentData> getAppointmentsByPage(int pageNumber, int pageSize);
    Page<AppointmentData> getAppointmentsByUniversalSearch(int pageNumber, int pageSize, String searchString);
}
