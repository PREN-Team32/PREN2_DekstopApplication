/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ValueItem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;

/**
 *
 * @author Niklaus *
 * Checks the BluetoothConnection for wheter new Values are incoming or not.
 */
public class ConnectionCheckerRunnable implements Runnable {
    private static ConnectionCheckerRunnable theInstance = null;
    private ValueItem newValues;
    private String hostIP;

    
    private ConnectionCheckerRunnable() {
        super();   
        newValues = ValueItem.getInstance();
    }
    
    public static ConnectionCheckerRunnable getInstance() {
        if(theInstance == null) {
            theInstance = new ConnectionCheckerRunnable();
        }
        return theInstance;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }    
    
    @Override
    public void run() {
        while(true) {
            System.out.println("#ConnectionChecker: Starting to check for incoming ValueItems.");
            try (Socket clientSocket = new Socket(hostIP, 11111)) {
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ValueItem tmp = (ValueItem) ois.readObject();
                System.out.println("#ConnectionChecker: New Values received.");
                newValues.overrideValues(tmp);
            }
            catch(IOException | ClassNotFoundException e) {
                try {
                    Thread.sleep(100);
                    System.err.println("#ConnectionChecker: " + e.getMessage());
                } catch (InterruptedException ex) {}
            }
        }
        
    }
}
