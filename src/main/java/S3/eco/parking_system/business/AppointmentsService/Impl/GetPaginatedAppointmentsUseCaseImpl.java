package S3.eco.parking_system.business.AppointmentsService.Impl;

import S3.eco.parking_system.business.AppointmentsService.Exceptions.AppointmentNotFoundException;
import S3.eco.parking_system.business.AppointmentsService.Interfaces.GetPaginatedAppointmentsUseCase;
import S3.eco.parking_system.business.AppointmentsService.Utils.ConverterAppointmentsData;
import S3.eco.parking_system.domain.Appointmets.AppointmentData;
import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Repositories.AppointmentRepository;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class GetPaginatedAppointmentsUseCaseImpl implements GetPaginatedAppointmentsUseCase {
    private final AppointmentRepository appointmentRepository;
    private final ConverterAppointmentsData converter;

    public Page<AppointmentData> getAppointmentsByPage(int pageNumber, int pageSize) {
        Pageable pageable = PageRequest.of(pageNumber, pageSize);
        Page<AppointmentEntity> appointmentPage = appointmentRepository.findAllByOrderByDatetimeDesc(pageable);

        if (appointmentPage.isEmpty()) {
            throw new AppointmentNotFoundException("No appointments found on page: " + pageNumber);
        }

        return appointmentPage.map(converter::convertToAppointmentData);
    }
}
