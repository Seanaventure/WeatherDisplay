import java.io.*;

import gnu.io.CommPortIdentifier;
import gnu.io.SerialPort;
import java.util.Enumeration;

import java.io.OutputStream;

/**
 * Created by Sean on 7/24/2017.
 */

public class ArduinoComm {
    SerialPort serialPort;
    private OutputStream output;
    public void openPort() {
        CommPortIdentifier portId = null;
        Enumeration portEnum = CommPortIdentifier.getPortIdentifiers();
        while (portEnum.hasMoreElements()) {
            CommPortIdentifier currPortId = (CommPortIdentifier)portEnum.nextElement();
            portId = currPortId;
            break;
        }
        if (portId == null) {
            System.out.println("Could not find COM port");
            return;
        }
        try {
            // open serial port, and use class name for the appName.
            serialPort = (SerialPort) portId.open(this.getClass().getName(), 2000);
            Thread.sleep(4000);
            // set port parameters
            serialPort.setSerialPortParams(9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);

            // open the streams

            output = serialPort.getOutputStream();

        } catch (Exception e) {
            System.err.println(e.toString());
        }
    }
    public void writeMsg(String msg) {
        try {
            output.write(msg.getBytes());
            output.flush();
        }catch (IOException e){
            System.out.println("error");
        }
    }
    public void closePort() {
        serialPort.close();
    }
}
