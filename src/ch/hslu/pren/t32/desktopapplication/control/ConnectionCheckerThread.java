/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.view.DesktopViewer;
import ch.hslu.pren.t32.model.ValueItem;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Niklaus *
 * Checks the BluetoothConnection for wheter new Values are incoming or not.
 */
public class ConnectionCheckerThread implements Runnable {
    private DesktopViewer observer;
    private BluetoothConnection connection;
    private ValueItem newValues;
    
    private StreamConnection mConnection;

    
    public ConnectionCheckerThread(DesktopViewer observer, BluetoothConnection connection) {
        this.observer = observer;
        this.connection = connection;   
        newValues = ValueItem.getInstance();
    }
    
    @Override
    public void run() {
        while(true) {
            boolean objectReceived = false;
            //TODO: check for incoming objects on stream
            
            mConnection =  connection.getConnection();
            
            if(mConnection != null) {
                 try {
                    byte[] bites = new byte[1000000];
               
                    BufferedInputStream inputStream = new BufferedInputStream(mConnection.openDataInputStream());
                    
                     int bufferSize = bites.length;
             
                    //WEiss nicht wieso aber liest beim ersten mal nur immer 990 bytes ein
                    int byteNo = inputStream.read(bites);                       
                    if (byteNo != -1) {             

                    //Hier liest er die restlichen ein wenn das byte[] array nicht mehr als 1 Million datensätze enthält
                    byteNo = inputStream.read(bites,byteNo,bufferSize);

                    }             
                    recieveByte(bites);
                } catch (IOException ex) {
                    Logger.getLogger(ConnectionCheckerThread.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                
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
    
    public void recieveByte(byte[] myBytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(myBytes);
        ObjectInput in = null;

        try {
            in = new ObjectInputStream(bis);
            bis.close();
            
            newValues = (ValueItem) in.readObject();
            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                bis.close();
            } catch (IOException ex) {
                System.out.println("Exception lautet: " + ex.getMessage());
            }
            try {
                if (in != null) {
                    in.close();
                }
            } catch (IOException ex) {
                System.out.println("ExceptionIO lautet: " + ex.getMessage());
            }           
        }
    }
    
}
