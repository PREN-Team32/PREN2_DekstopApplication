/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.model.ConfigurationItem;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Niklaus
 */
public class ConfigSender {
    private ConfigurationItem config;
    
    public ConfigSender() {
        this.config = ConfigurationItem.getInstance();
    }
    
    public void setLuminanceThreshold(float threshold) {
        config.luminanceThreshold = threshold;
    }
    
    public void setWidthToObserve(int width) {
        config.widthToObserve = width;
    }
    
    public void setHeightToObserve(int height) {
        config.heightToObserve = height;
    }
    
    public void setVisitedPixels(int pixelCount) {
        config.visitedPixels = pixelCount;
    }
    
    public void setStartSignal(boolean signal) {
        config.startSignal = signal;
    }
    
    public void sendConfig() throws IOException {
            
        BluetoothConnection blue = new BluetoothConnection();        
        StreamConnection mConnection = blue.getConnection();
        OutputStream os = mConnection.openOutputStream();
        
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
               
        try {            
            byte[] yourBytes = bos.toByteArray();

            
            os.write(yourBytes);
            os.flush();
            os.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        bos.flush();
    }
}
