/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ConfigurationItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niklaus
 */
public class ConfigSender {
    private final ConfigurationItem config;
    ServerSocket serverSocket;
    
    public ConfigSender() {
        this.config = ConfigurationItem.getInstance();
        try {
            serverSocket = new ServerSocket(11111);
        } catch (IOException ex) {
            Logger.getLogger(ConfigSender.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    
    public void setPixelToCm(double pixelToCm) {
        config.pixelToCm = pixelToCm;
    }
    
    public void sendConfig() throws IOException{
        if(serverSocket.isClosed()) {
            this.serverSocket = new ServerSocket(11111);
        }
        Socket pipe = serverSocket.accept();
        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(pipe.getOutputStream());
        
        objectOutputStream.writeObject(config);
        
        pipe.close();
        serverSocket.close();
        System.out.println("#ConfigSender: Config was successfully sent.");
    }
}
