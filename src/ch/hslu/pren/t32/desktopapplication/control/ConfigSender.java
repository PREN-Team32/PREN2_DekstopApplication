/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.model.ConfigurationItem;

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
    
    public void sendConfig() {
        //TODO
    }
}
