package S3.eco.parking_system.persistence.Repositories;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {
    List<AppointmentEntity> findAll();

    Page<AppointmentEntity> findAllByOrderByDatetimeDesc(Pageable pageable);

    Optional<AppointmentEntity> findById(Long aLong);

    Optional<AppointmentEntity> findByGuest(String guest);

    Optional<AppointmentEntity> findByGuestEmail(String guestEmail);

    Optional<AppointmentEntity> findByDatetime(LocalDateTime dateTime);

    @Query("SELECT a FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a WHERE YEAR(a.datetime) = :year AND MONTH(a.datetime) = :month ORDER BY a.datetime")
    List<AppointmentEntity> findByDatetimeYearAndDatetimeMonth(
            @Param("year") int year,
            @Param("month") int month
    );

    @Query("SELECT a FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a " +
            "INNER JOIN S3.eco.parking_system.persistence.Entities.EmployeeEntity e " +
            "ON a.employee.id = e.id " +
            "WHERE LOWER(e.employeeName) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(e.employeeEmail) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.guest) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.guestEmail) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.carPlateNumber) LIKE LOWER(CONCAT('%', :searchString, '%')) OR " +
            "LOWER(a.description) LIKE LOWER(CONCAT('%', :searchString, '%'))")
    Page<AppointmentEntity> searchAppointmentEntityBy(
            @Param("searchString") String searchString,
            Pageable pageable
    );

    Optional<AppointmentEntity> findByCarPlateNumber(String carPlateNumber);

    @Query("SELECT a FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a " +
            "INNER JOIN a.employee e " +
            "WHERE a.datetime = :dateTime " +
            "AND e.employeeEmail = :employeeEmail " +
            "AND a.guest = :guest")
    AppointmentEntity findAllByDatetimeAndEmployeeEmailAndGuest(
            @Param("dateTime") LocalDateTime dateTime,
            @Param("employeeEmail") String employeeEmail,
            @Param("guest") String guest
    );

    @Query("SELECT CASE WHEN COUNT(a) > 0 THEN true ELSE false END " +
            "FROM S3.eco.parking_system.persistence.Entities.AppointmentEntity a " +
            "INNER JOIN a.employee e " +
            "WHERE a.datetime = :dateTime " +
            "AND e.employeeEmail = :employeeEmail " +
            "AND a.guest = :guest")
    boolean existsByDatetimeAndEmployeeEmailAndGuest(
            @Param("dateTime") LocalDateTime dateTime,
            @Param("employeeEmail") String employeeEmail,
            @Param("guest") String guest
    );
}
