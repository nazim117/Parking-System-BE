package S3.eco.parking_system.microservices;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

@Async
@Component
public class PlateDetectionReceiverUseCaseImpl implements PlateDetectionReceiverUseCase{
    private final String HOST = "localhost";
    private final int PORT = 65432;
    private static final Logger LOGGER = LoggerFactory.getLogger(PlateDetectionReceiverUseCaseImpl.class);

    @Override
    public void getCarPlateNumber(CarPlateProcessorUseCase carPlateProcessor) {
        while (true) {
            try (Socket socket = new Socket(HOST, PORT);
                 BufferedReader reader = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

                LOGGER.info("Connected to server");

                String data;
                while ((data = reader.readLine()) != null) {
                    LOGGER.info("Received data: " + data);
                    carPlateProcessor.process(data);
                }
            } catch (IOException e) {
                LOGGER.error("Error occurred while reading data: " + e.getMessage());
                try {
                    Thread.sleep(10_000);
                } catch (InterruptedException ex) {
                    LOGGER.error("Thread interrupted while waiting for reconnection: " + ex.getMessage());
                    Thread.currentThread().interrupt(); // Restore interrupted status
                }
            }
        }
    }
}
