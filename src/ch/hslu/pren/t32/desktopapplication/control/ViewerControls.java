/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.control.network.BluetoothConnection;
import ch.hslu.pren.t32.desktopapplication.control.network.ConfigSender;
import ch.hslu.pren.t32.desktopapplication.control.network.ConnectionCheckerRunnable;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Niklaus
 */
public class ViewerControls {
    private ResultLogger logger;
    private final BluetoothConnection bluetoothConnection;
    private final ConnectionCheckerRunnable connectionChecker;
    private Thread connectionCheckerThread;
    private ResultLogger receiver;
    private ConfigSender sender = null;
    
    public ViewerControls(ConnectionCheckerRunnable connectionChecker) {
        this.connectionChecker = connectionChecker;
        this.bluetoothConnection = BluetoothConnection.getInstance();
        this.receiver = new ResultLogger();
    }
    
    public void luminanceSliderStateChanged(JSlider luminanceSlider, JTextField luminanceThreshold) {                                             
        float value = luminanceSlider.getValue();
        value = value/100.00f;
        System.out.println(value + " f");
        luminanceThreshold.setText(value + " f");
    }
    
    public void testrunMouseClicked(int luminanceThreshold, String pixelToCm, String visitedPixels, String width, String height) {
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
    
    public void startMouseClicked(int luminanceThreshold, String pixelToCm, String visitedPixels, String width, String height) {
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
        connectionChecker.setConnection(bluetoothConnection.getConnection());
        if(connectionCheckerThread == null)
            connectionCheckerThread = new Thread(connectionChecker);
        if(!connectionCheckerThread.isAlive())
            connectionCheckerThread.start();
    }
    
    public void connectBluetoothMouseClicked() {
        if(sender == null) {
            this.sender = new ConfigSender(bluetoothConnection.getConnection());
            System.out.println("#ViewerControls: BluetoothConnection established.");
        }
    }
    
    public void closeOperations() {
        System.out.println("#ViewerControls: Shutting down Bluetooth..");
        bluetoothConnection.closeConnection();
    }
}
