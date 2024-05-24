package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.microservices.CarPlateProcessorUseCase;
import S3.eco.parking_system.microservices.PlateDetectionReceiverUseCase;
import S3.eco.parking_system.microservices.PlateDetectionReceiverUseCaseImpl;
import jakarta.annotation.PostConstruct;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class CarObserver {
    private final CarPlateProcessorUseCase carPlateProcessorUseCase;
    private final PlateDetectionReceiverUseCase plateDetectionReceiverUseCase;

    @PostConstruct
    public void init() {
        startPlateDetectionReceiver();
    }

    @Async
    public void startPlateDetectionReceiver() {
        plateDetectionReceiverUseCase.getCarPlateNumber(carPlateProcessorUseCase);
    }
}
