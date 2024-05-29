package S3.eco.parking_system.microservices.Arduino;

public interface ArduinoSerialReaderUseCase {
    void getSensorInfo(SensorDataProcessorUseCase sensorDataProcessorUseCase);
}
