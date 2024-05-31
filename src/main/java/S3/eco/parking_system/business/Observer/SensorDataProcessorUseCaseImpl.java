package S3.eco.parking_system.business.Observer;

import S3.eco.parking_system.microservices.Arduino.ArduinoSerialReaderUseCaseImpl;
import S3.eco.parking_system.microservices.Arduino.SensorDataProcessorUseCase;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class SensorDataProcessorUseCaseImpl implements SensorDataProcessorUseCase {
    private static int counter = 0;
    private static final Logger LOGGER = LoggerFactory.getLogger(ArduinoSerialReaderUseCaseImpl.class);

    private static final long DEBOUNCE_TIME = 1000; // Debounce time in milliseconds
    private long lastEntryTime = 0;
    private long lastExitTime = 0;
    private boolean lastStateWasEntry = false;
    private boolean lastStateWasExit = false;

    @Value("${parking.spaces}")
    private int parkingSpaces;

    @Override
    public void processReceivedData(String data) {
        LOGGER.info("Received data: " + data);

        long currentTime = System.currentTimeMillis();

        switch (data) {
            case "ENTRY":
                if (currentTime - lastEntryTime > DEBOUNCE_TIME) {
                    if (lastStateWasExit) { //Check if car is entering
                        if(counter > 0){
                            counter--;
                            LOGGER.info("ENTRY detected after EXIT, counter decrement");
                        }else{
                            LOGGER.info("Parking is already empty");
                        }
                        lastStateWasEntry = false;
                    }else{
                        lastEntryTime = currentTime;
                        lastStateWasEntry = true;
                    }
                        lastStateWasExit = false;
                }
                break;

            case "EXIT":
                if (currentTime - lastExitTime > DEBOUNCE_TIME) {
                    if (lastStateWasEntry) { //Check if car is leaving parking
                        if(counter < parkingSpaces){
                            counter++;
                            LOGGER.info("EXIT detected after ENTRY, counter increment");
                        }else{
                            LOGGER.info("Parking is full");
                        }
                        lastStateWasExit = false;
                    }else {
                        lastExitTime = currentTime;
                        lastStateWasExit = true;
                    }
                        lastStateWasEntry = false;
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
