package S3.eco.parking_system.persistence.Entities;

import jakarta.persistence.*;
import lombok.*;

@Entity(name = "parked_cars")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class ParkingSpaceCounterEntity {
    @Id
    @Column
    private Long id;

    @Column(name = "cars_in_parking")
    private int carsInParking;
}
