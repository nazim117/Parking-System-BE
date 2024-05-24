package S3.eco.parking_system.microservices;

public interface PlateDetectionReceiverUseCase {
    void getCarPlateNumber(CarPlateProcessorUseCase carPlateProcessor);
}
