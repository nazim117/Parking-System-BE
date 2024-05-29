package S3.eco.parking_system.microservices.PlateDetection;

public interface PlateDetectionReceiverUseCase {
    void getCarPlateNumber(CarPlateProcessorUseCase carPlateProcessor);
}
