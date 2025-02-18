package S3.eco.parking_system.persistence.Repositories;

import S3.eco.parking_system.persistence.Entities.AppointmentEntity;
import S3.eco.parking_system.persistence.Entities.EmployeeEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEntity, Long> {
    List<EmployeeEntity> findAll();

    Page<EmployeeEntity> findAllByOrderByEmployeeName(Pageable pageable);

    Optional<EmployeeEntity> findByEmployeeEmailAndEmployeeName(String employeeEmail, String employeeName);

    Optional<EmployeeEntity> findById(Long id);

    Optional<EmployeeEntity> findByEmployeeEmail(String employeeEmail);

    @Query("SELECT e FROM S3.eco.parking_system.persistence.Entities.EmployeeEntity e WHERE e.employeeName Like %:name%")
    Page<EmployeeEntity> findAllByEmployeeName(@Param("name")String name, Pageable pageable);
}
