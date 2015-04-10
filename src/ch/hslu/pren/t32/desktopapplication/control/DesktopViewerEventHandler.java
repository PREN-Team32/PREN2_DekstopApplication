/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.desktopapplication.control.network.BluetoothConnection;
import ch.hslu.pren.t32.desktopapplication.control.network.ConfigSender;
import ch.hslu.pren.t32.desktopapplication.control.network.ValueReceiver;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JSlider;
import javax.swing.JTextField;

/**
 *
 * @author Niklaus
 */
public class DesktopViewerEventHandler {
    private final BluetoothConnection bluetoothConnection;
    private ValueReceiver receiver;
    private ConfigSender sender = null;
    
    public DesktopViewerEventHandler() {
        this.bluetoothConnection = BluetoothConnection.getInstance();
        this.receiver = new ValueReceiver();
    }
    
    public void drawMainAreaMouseClicked(JLabel imageLabel) {
        ImageHandler.drawVerticalLine(receiver.getEditedImage(), receiver.getMainArea(), com.sun.prism.paint.Color.BLUE);
        ImageIcon image = new ImageIcon(receiver.getEditedImage());
        imageLabel.setIcon(image);        
    }
    
    public void luminanceSliderStateChanged(JSlider luminanceSlider, JTextField luminanceThreshold) {                                             
        float value = luminanceSlider.getValue();
        value = value/100.00f;
        luminanceThreshold.setText(value + " f");
    }
    
    public void drawShapeBorderMouseClicked(JLabel imageLabel) {                                             
        ImageHandler.drawVerticalLine(receiver.getEditedImage(), receiver.getObjectBorder(), com.sun.prism.paint.Color.GREEN);
        ImageIcon image = new ImageIcon(receiver.getEditedImage());
        imageLabel.setIcon(image);        
    }
    
    public void testrunMouseClicked(int luminanceThreshold) {                                     
        sender.setLuminanceThreshold(luminanceThreshold);
        sender.sendConfig();
    }
    
    public void startActionPerformed() {                                      
        sender.setStartSignal(true);
        sender.sendConfig();
    }
    
    public void connectBluetoothActionPerformed() {
        if(sender == null) {
            this.sender = new ConfigSender(bluetoothConnection.getConnection());
            System.out.println("#DekstopViewer: BluetoothConnection established.");
        }
    }
}
