package S3.eco.parking_system.business.AppointmentsService.Interfaces;

import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import org.springframework.data.domain.Page;

public interface GetPaginatedAppointmentsUseCase {
    Page<AppointmentData> getAppointmentsByPage(int pageNumber, int pageSize);
}
