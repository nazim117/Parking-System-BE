package S3.eco.parking_system.microservices;

public interface ArduinoSerialReaderUseCase {
    void getSensorInfo(SensorDataProcessorUseCase sensorDataProcessorUseCase);
}
