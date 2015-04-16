package ch.hslu.pren.t32.desktopapplication;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import ch.hslu.pren.t32.desktopapplication.control.ResultLogger;
import ch.hslu.pren.t32.desktopapplication.control.ViewerControls;
import ch.hslu.pren.t32.desktopapplication.control.network.ConnectionCheckerRunnable;
import ch.hslu.pren.t32.desktopapplication.view.DesktopViewer;
/**
 *
 * @author Niklaus
 */
public class Main {
    public static void main(String[] args) {
        initLookAndFeel();
        /* Create and display the Viewer */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                ConnectionCheckerRunnable connectionChecker = ConnectionCheckerRunnable.getInstance();
                ViewerControls controls = new ViewerControls(connectionChecker);
                DesktopViewer viewer = new DesktopViewer(controls);
                ResultLogger logger = new ResultLogger();
                
                viewer.setVisible(true);
            }
        });
    }

    private static void initLookAndFeel() {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DesktopViewer.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
    }
}
