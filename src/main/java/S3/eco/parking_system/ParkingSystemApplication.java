package S3.eco.parking_system;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ParkingSystemApplication {
	public static void main(String[] args) {
		SpringApplication.run(ParkingSystemApplication.class, args);
	}
}

