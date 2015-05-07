/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ValueItem;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author Niklaus *
 * Checks the BluetoothConnection for wheter new Values are incoming or not.
 */
public class ConnectionCheckerRunnable implements Runnable {
    private static ConnectionCheckerRunnable theInstance = null;
    private ValueItem newValues;

    
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
    
    @Override
    public void run() {
        try {
            while(true) {
                ServerSocket serverSocket = new ServerSocket(11111);
                Socket pipe = serverSocket.accept();

                ObjectInputStream ois = new ObjectInputStream(pipe.getInputStream());
                ValueItem tmp = (ValueItem) ois.readObject();
                newValues.overrideValues(tmp);

                pipe.close();
                serverSocket.close();
            }
        }
        catch(ClassNotFoundException | IOException ex) {
            ex.printStackTrace();
        }

    }
}
