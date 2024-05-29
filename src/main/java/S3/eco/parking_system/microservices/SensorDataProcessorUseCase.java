package S3.eco.parking_system.microservices;

public interface SensorDataProcessorUseCase {
    void processReceivedData(String data);
    int getCounter();
}
