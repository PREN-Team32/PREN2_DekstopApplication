/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;



import java.io.IOException;
import javax.bluetooth.DiscoveryAgent;
import javax.bluetooth.LocalDevice;
import javax.bluetooth.UUID;
import javax.microedition.io.Connector;
import javax.microedition.io.StreamConnection;
import javax.microedition.io.StreamConnectionNotifier;
/**
 *
 * @author Niklaus
 */
public class BluetoothConnection implements Runnable {
    private StreamConnection connection = null;
    private static BluetoothConnection bluetoothInstance = null;
    
    private BluetoothConnection() {
    }
    
    public static BluetoothConnection getInstance() {
        if(bluetoothInstance == null) {
            bluetoothInstance = new BluetoothConnection();
        }
        return bluetoothInstance;
    }
    
    @Override
    public void run() {
        waitForConnection();
    }
    
    public StreamConnection getConnection(){
        if(this.connection == null) {
            Thread t = new Thread(this);
            t.start();
        }
        System.out.println("#BluetoothConnection: Freezing until connection established..");
        while(this.connection == null) {
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                System.err.println("#BluetoothConnection: Waiting for connection was interrupted.");
            }
        }
        return connection;
    }
    
     /** Waiting for connection from devices */
    private void waitForConnection() {
        LocalDevice local = null;
        StreamConnectionNotifier notifier;        

        // Setzt Einstellungen für eine Erfolgreiche Bluetooth Connection
        //ToDo: PC Name optionen einstellbar machen.
        try {
            local = LocalDevice.getLocalDevice();
            local.setDiscoverable(DiscoveryAgent.GIAC);
            
            
            UUID uuid = new UUID("2ebee4ce0f154e619344e8cea59f59ec",false);
            
            
            String url = "btspp://localhost:" + uuid.toString() + ";name=LIVIO-LAPTOP";
            notifier = (StreamConnectionNotifier)Connector.open(url);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        // Hier wird auf eine Verbindung gewartet
        while(true) {
            try {                
                connection = notifier.acceptAndOpen();
                        
            //Ursprünglech wurde von hier aus der Thread der liest gestartet
            //Thread processThread = new Thread(new ConnectionCheckerThread(connection));
            //processThread.start();
            } catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }
    
    private void closeConnection() {
        if(connection != null) {
            try {
                connection.close();
            } catch (IOException ex) {
                System.err.println("#BluetoothConnection: Could not close StreamConnection.");
            }
        }
    }
    
}
