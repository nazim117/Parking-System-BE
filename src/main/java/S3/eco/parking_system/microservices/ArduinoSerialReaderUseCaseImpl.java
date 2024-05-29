package S3.eco.parking_system.microservices;

import com.fazecast.jSerialComm.SerialPort;
import com.fazecast.jSerialComm.SerialPortDataListener;
import com.fazecast.jSerialComm.SerialPortEvent;
import org.springframework.stereotype.Component;

@Component
public class ArduinoSerialReaderUseCaseImpl implements ArduinoSerialReaderUseCase {
    private StringBuilder dataBuffer = new StringBuilder(); // Buffer for storing incoming data

    @Override
    public void getSensorInfo(SensorDataProcessorUseCase sensorDataProcessorUseCase) {
        SerialPort comPort = SerialPort.getCommPort("COM5"); // Change to your port
        comPort.setBaudRate(9600);
        if (!comPort.openPort()) {
            System.out.println("Unable to open the port.");
            return;
        }

        comPort.addDataListener(new SerialPortDataListener() {
            @Override
            public int getListeningEvents() {
                return SerialPort.LISTENING_EVENT_DATA_AVAILABLE;
            }

            @Override
            public void serialEvent(SerialPortEvent event) {
                if (event.getEventType() != SerialPort.LISTENING_EVENT_DATA_AVAILABLE) {
                    return;
                }
                byte[] newData = new byte[comPort.bytesAvailable()];
                comPort.readBytes(newData, newData.length);
                dataBuffer.append(new String(newData)); // Append new data to the buffer

                String receivedData = dataBuffer.toString();
                int newlineIndex;

                // Process each complete line
                while ((newlineIndex = receivedData.indexOf('\n')) != -1) {
                    String line = receivedData.substring(0, newlineIndex).trim();
                    sensorDataProcessorUseCase.processReceivedData(line);
                    receivedData = receivedData.substring(newlineIndex + 1);
                }
                // Update the buffer with any remaining data
                dataBuffer.setLength(0);
                dataBuffer.append(receivedData);
            }
        });
    }
}
