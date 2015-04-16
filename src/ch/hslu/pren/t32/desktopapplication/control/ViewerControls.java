/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.control.network.BluetoothConnection;
import ch.hslu.pren.t32.desktopapplication.control.network.ConfigSender;
import ch.hslu.pren.t32.desktopapplication.control.network.ConnectionCheckerRunnable;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Niklaus
 */
public class ViewerControls {
    private ResultLogger logger;
    private final BluetoothConnection bluetoothConnection;
    private ConnectionCheckerRunnable connectionChecker;
    private Thread connectionCheckerThread;
    private ResultLogger receiver;
    private ConfigSender sender = null;
    
    public ViewerControls(ConnectionCheckerRunnable connectionChecker) {
        this.connectionChecker = connectionChecker;
        this.bluetoothConnection = BluetoothConnection.getInstance();
        this.receiver = new ResultLogger();
    }
    
    public void drawMainAreaMouseClicked(JLabel imageLabel) {
//        ImageHandler.drawVerticalLine(receiver.getEditedImage(), receiver.getMainArea(), com.sun.prism.paint.Color.BLUE);
//        ImageIcon image = new ImageIcon(receiver.getEditedImage());
//        imageLabel.setIcon(image);        
    }
    
    public void luminanceSliderStateChanged(JSlider luminanceSlider, JTextField luminanceThreshold) {                                             
        float value = luminanceSlider.getValue();
        value = value/100.00f;
        System.out.println(value + " f");
        luminanceThreshold.setText(value + " f");
    }
    
    public void drawShapeBorderMouseClicked(JLabel imageLabel) {                                             
//        ImageHandler.drawVerticalLine(receiver.getEditedImage(), receiver.getObjectBorder(), com.sun.prism.paint.Color.GREEN);
//        ImageIcon image = new ImageIcon(receiver.getEditedImage());
//        imageLabel.setIcon(image);        
    }
    
    public void testrunMouseClicked(int luminanceThreshold) {                                     
        sender.setLuminanceThreshold(luminanceThreshold);
        sender.sendConfig();
        startConnectionChecking();
    }
    
    public void startMouseClicked() {                                      
        sender.setStartSignal(true);
        sender.sendConfig();
        startConnectionChecking();
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
            System.out.println("#DekstopViewer: BluetoothConnection established.");
        }
    }
}
