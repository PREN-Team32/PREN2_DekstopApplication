/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ValueItem;
import java.io.BufferedInputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
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
public class ConnectionCheckerRunnable implements Runnable {
    private static ConnectionCheckerRunnable theInstance = null;
    private ValueItem newValues;
    
    private StreamConnection mConnection;

    
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
    
    public void setConnection(StreamConnection mConnection) {
        this.mConnection = mConnection;
    }
    
    @Override
    public void run() {
        if(mConnection != null) {
            DataInputStream dis = null;
            try {
               dis = mConnection.openDataInputStream();
            } catch (IOException ex) {
                Logger.getLogger(ConnectionCheckerRunnable.class.getName()).log(Level.SEVERE, null, ex);
            }
            while(true) {
                boolean objectReceived = false;
                //TODO: check for incoming objects on stream
                
                if(mConnection != null) {
                     try {
                        byte[] bites = new byte[1000000];

                        BufferedInputStream inputStream = new BufferedInputStream(dis);
                        

                         int bufferSize = bites.length;

                        //WEiss nicht wieso aber liest beim ersten mal nur immer 990 bytes ein
                        int byteNo = inputStream.read(bites);                       
                        if (byteNo != -1) {             

                        //Hier liest er die restlichen ein wenn das byte[] array nicht mehr als 1 Million datensätze enthält
                        byteNo = inputStream.read(bites,byteNo,bufferSize-byteNo);
                            
                        }             
                        recieveByte(bites);
                        dis.reset();
                        
                    } catch (IOException ex) {
                        Logger.getLogger(ConnectionCheckerRunnable.class.getName()).log(Level.SEVERE, null, ex);
                    }
                     finally{
                         try{                         
                         }catch(Exception ex){ex.printStackTrace();}
                     }
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
    
    public void recieveByte(byte[] myBytes) throws IOException {
        ByteArrayInputStream bis = new ByteArrayInputStream(myBytes);
        ObjectInput in = null;

        try {
            in = new ObjectInputStream(bis);
            bis.close();
            
            ValueItem val = ValueItem.getInstance();
            val = (ValueItem) in.readObject();
            
            newValues.overrideValues(val);
            System.out.println("#ConnectionCheckerRunnable: Successfully received new Values.");
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
