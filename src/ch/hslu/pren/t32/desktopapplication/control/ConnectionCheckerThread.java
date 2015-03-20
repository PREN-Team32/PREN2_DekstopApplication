/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.view.DesktopViewer;
import ch.hslu.pren.t32.model.ValueItem;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niklaus * 
 */
public class ConnectionCheckerThread implements Runnable {
    private DesktopViewer observer;
    private BluetoothConnection connection;
    private ValueItem newValues;
    
    public ConnectionCheckerThread(DesktopViewer observer, BluetoothConnection connection) {
        this.observer = observer;
        this.connection = connection;
    }

    @Override
    public void run() {
        while(true) {
            boolean objectReceived = false;
            //TODO: check for incoming objects on stream
            
            
            if(objectReceived) {
                //TODO: Read object from stream and assign it to newValues
                
                
                observer.updateValues(newValues);
            }
            else {
                try {
                    Thread.sleep(50);
                } catch (InterruptedException ex) {
                    System.err.println(ex.getMessage());
                }
            }
        }
    }
    
}
