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
    private BluetoothConnection bluetoothConnection;
    private ValueReceiver receiver;
    private ConfigSender sender;
    
    public DesktopViewerEventHandler() {
        this.bluetoothConnection = BluetoothConnection.getInstance();
    }
    
    public void drawMainAreaMouseClicked(JLabel imageLabel) {
        ImageHandler.drawVerticalLine(receiver.getEditedImage(), receiver.getMainArea(), com.sun.prism.paint.Color.BLUE);
        ImageIcon image = new ImageIcon(receiver.getEditedImage());
        imageLabel.setIcon(image);
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setText("");
        imageLabel.repaint();
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
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        imageLabel.setText("");
        imageLabel.repaint();
    }
    
    public void testrunMouseClicked(JSlider luminanceSlider) {                                     
        sender.setLuminanceThreshold(luminanceSlider.getValue());
        //sender.sendConfig();
    }
}
