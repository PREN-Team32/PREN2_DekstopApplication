/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication;

import ch.hslu.pren.t32.desktopapplication.control.DesktopViewerEventHandler;
import ch.hslu.pren.t32.desktopapplication.view.DesktopViewer;

/**
 *
 * @author Niklaus
 */
public class Test {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        DesktopViewer viewer = new DesktopViewer();
//        viewer.setVisible(true);
        DesktopViewerEventHandler test = new DesktopViewerEventHandler();
        test.connectBluetoothMouseClicked();
    }
    
}
