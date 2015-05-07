/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ConfigurationItem;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Niklaus
 */
public class ConfigSender {
    private final ConfigurationItem config;
    private String hostIP;
    Socket clientSocket;
    
    public ConfigSender(String host) {
        this.config = ConfigurationItem.getInstance();
        this.hostIP = host;
        try {
            clientSocket = new Socket(hostIP, 11111);
            System.out.println("#ConfigSender: Connection succeeded.");
        } catch (IOException ex) {
            Logger.getLogger(ConfigSender.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getHostIP() {
        return hostIP;
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
        if(clientSocket.isClosed()) {
            this.clientSocket = new Socket(hostIP, 11111);
        }
        
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(clientSocket.getOutputStream());
        
        objectOutputStream.writeObject(config);
        
        clientSocket.close();
        System.out.println("#ConfigSender: Config was successfully sent.");
    }
}
