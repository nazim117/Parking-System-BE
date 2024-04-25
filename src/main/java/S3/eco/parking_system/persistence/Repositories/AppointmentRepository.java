package S3.eco.parking_system.persistence.Repositories;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findAll();

    Page<AppointmentEntity> findAllByOrderByDatetimeDesc(Pageable pageable);

    Optional<AppointmentEntity> findById(Long aLong);

    Optional<AppointmentEntity> findByEmployee(String employee);

    Optional<AppointmentEntity> findByEmployeeEmail(String employeeEmail);

    Optional<AppointmentEntity> findByGuest(String guest);

    Optional<AppointmentEntity> findByGuestEmail(String guestEmail);

    Optional<AppointmentEntity> findByDatetime(LocalDateTime dateTime);

    @Query("SELECT a FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a WHERE YEAR(a.datetime) = :year AND MONTH(a.datetime) = :month")
    List<AppointmentEntity> findByDatetimeYearAndDatetimeMonth(
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("SELECT a FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a WHERE " +
            "LOWER(a.employeeEmail) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.guestEmail) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.carPlateNumber) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.description) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    List<AppointmentEntity> searchAppointmentEntityBy(
            @Param("searchString") String searchString
    );

    Optional<AppointmentEntity> findByCarPlateNumber(String carPlateNumber);

    AppointmentEntity findAllByDatetimeAndEmployeeEmailAndGuest(LocalDateTime dateTime, String employeeEmail, String Guest);

    boolean existsByDatetimeAndEmployeeEmailAndGuest(LocalDateTime dateTime, String employeeEmail, String Guest);
}
