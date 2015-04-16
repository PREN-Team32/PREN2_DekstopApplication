/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.hslu.pren.t32.desktopapplication.model.ConfigurationItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import javax.microedition.io.StreamConnection;

/**
 *
 * @author Niklaus
 */
public class ConfigSender {
    private final ConfigurationItem config;
    private StreamConnection mConnection;
    
    public ConfigSender(StreamConnection mConnection) {
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
    
    public void sendConfig() {
        ObjectOutputStream os;
        try {
            os = new ObjectOutputStream(mConnection.openOutputStream());
            os.writeObject(config);
            os.flush();
            os.close();
        } catch (IOException ex) {
            System.err.println("#ConfigSender: Could not open ByteArrayOutputstream in sendConfig().");
        }
    }
}
