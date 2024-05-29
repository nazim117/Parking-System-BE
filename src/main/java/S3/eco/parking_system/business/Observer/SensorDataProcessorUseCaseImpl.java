package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.microservices.Arduino.ArduinoSerialReaderUseCaseImpl;
import S3.eco.parking_system.microservices.Arduino.SensorDataProcessorUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class SensorDataProcessorUseCaseImpl implements SensorDataProcessorUseCase {
    private static int counter = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoSerialReaderUseCaseImpl.class);

    private static final long DEBOUNCE_TIME = 1000; // Debounce time in milliseconds
    private long lastEntryTime = 0;
    private long lastExitTime = 0;

    @Override
    public void processReceivedData(String data) {
        LOGGER.info("Received data: " + data);

        long currentTime = System.currentTimeMillis();

        switch (data) {
            case "ENTRY":
                if (currentTime - lastEntryTime > DEBOUNCE_TIME) {
                    counter++;
                    lastEntryTime = currentTime;
                    LOGGER.info("ENTRY detected, counter incremented");
                }
                break;

            case "EXIT":
                if (currentTime - lastExitTime > DEBOUNCE_TIME) {
                    if(counter > 0){
                        counter--;
                    }
                    lastExitTime = currentTime;
                    LOGGER.info("EXIT detected, counter decremented");
                }
                break;

            default:
                LOGGER.warn("Unknown data received: " + data);
                break;
        }

        LOGGER.info("Current counter value: {}", counter);
    }

    @Override
    public int getCounter() {
        return counter;
    }
}
