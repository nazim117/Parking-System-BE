package S3.eco.parking_system.persistence.Repositories;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    @Override
    List<AppointmentEntity> findAll();

    @Override
    Optional<AppointmentEntity> findById(Long aLong);

    Optional<AppointmentEntity> findByEmployee(String employee);

    Optional<AppointmentEntity> findByDatetime(LocalDateTime dateTime);

    Optional<AppointmentEntity> findByCarPlateNumber(String carPlateNumber);
}
