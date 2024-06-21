package S3.eco.parking_system.microservices.Arduino;

public interface SensorDataProcessorUseCase {
    void processReceivedData(String data);
    int getCounter();
    void setCounter(int counter);
}
