package S3.eco.parking_system.persistence.Repositories;

import S3.eco.parking_system.persistence.Entities.ParkingSpaceCounterEntity;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface ParkingSpaceRepository extends JpaRepository<ParkingSpaceCounterEntity, Long> {
    @Modifying
    @Transactional
    @Query("UPDATE S3.eco.parking_system.persistence.Entities.ParkingSpaceCounterEntity c SET c.carsInParking = c.carsInParking + 1 WHERE c.id = 1")
    void increment();

    @Modifying
    @Transactional
    @Query("UPDATE S3.eco.parking_system.persistence.Entities.ParkingSpaceCounterEntity c SET c.carsInParking = c.carsInParking - 1 WHERE c.id = 1")
    void decrement();
}
