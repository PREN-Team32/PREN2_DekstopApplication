/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.control.network.ConfigSender;
import ch.hslu.pren.t32.desktopapplication.control.network.ConnectionCheckerRunnable;
import java.io.IOException;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Niklaus
 */
public class ViewerControls {
    private ResultLogger logger;
    private final ConnectionCheckerRunnable connectionChecker;
    private Thread connectionCheckerThread;
    private ConfigSender sender = new ConfigSender();
    
    public ViewerControls(ConnectionCheckerRunnable connectionChecker) {
        this.connectionChecker = connectionChecker;
    }
    
    public void luminanceSliderStateChanged(JSlider luminanceSlider, JTextField luminanceThreshold) {                                             
        float value = luminanceSlider.getValue();
        value = value/100.00f;
        System.out.println(value + " f");
        luminanceThreshold.setText(value + " f");
    }
    
    public void testrunMouseClicked(int luminanceThreshold, String pixelToCm, String visitedPixels, String width, String height) throws IOException{
        if(sender != null) {
            sender.setLuminanceThreshold(luminanceThreshold);
            sender.setPixelToCm(Double.parseDouble(pixelToCm));
            sender.setVisitedPixels(Integer.parseInt(visitedPixels));
            sender.setWidthToObserve(Integer.parseInt(width));
            sender.setHeightToObserve(Integer.parseInt(height));
            sender.sendConfig();
            startConnectionChecking();
        }
    }
    
    public void startMouseClicked(int luminanceThreshold, String pixelToCm, String visitedPixels, String width, String height) throws IOException{
        if(sender != null) {
            sender.setLuminanceThreshold(luminanceThreshold);
            sender.setPixelToCm(Double.parseDouble(pixelToCm));
            sender.setVisitedPixels(Integer.parseInt(visitedPixels));
            sender.setWidthToObserve(Integer.parseInt(width));
            sender.setHeightToObserve(Integer.parseInt(height));
            sender.setStartSignal(true);
            sender.sendConfig();
            startConnectionChecking();
        }
    }

    private void startConnectionChecking() {
        if(connectionCheckerThread == null)
            connectionCheckerThread = new Thread(connectionChecker);
        if(!connectionCheckerThread.isAlive())
            connectionCheckerThread.start();
    }
}
