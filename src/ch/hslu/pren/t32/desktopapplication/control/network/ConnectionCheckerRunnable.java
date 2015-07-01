/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.pren.model.ValueItem;
import java.awt.FlowLayout;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.net.Socket;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Niklaus *
 * Checks the BluetoothConnection for wheter new Values are incoming or not.
 */
public class ConnectionCheckerRunnable implements Runnable {
    private static ConnectionCheckerRunnable theInstance = null;
    private ValueItem newValues;
    private String hostIP;
    
    private ConnectionCheckerRunnable() {
        super();   
        newValues = ValueItem.getInstance();
    }
    
    public static ConnectionCheckerRunnable getInstance() {
        if(theInstance == null) {
            theInstance = new ConnectionCheckerRunnable();
        }
        return theInstance;
    }

    public void setHostIP(String hostIP) {
        this.hostIP = hostIP;
    }    
    
    @Override
    public void run() {
        while(true) {
            System.out.println("#ConnectionChecker: Starting to check for incoming ValueItems.");
            try (Socket clientSocket = new Socket(hostIP, 11111)) {
                ObjectInputStream ois = new ObjectInputStream(clientSocket.getInputStream());
                ValueItem tmp = (ValueItem) ois.readObject();
                
                if(tmp.finished){
                   System.out.println("Endsignal erhalten!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
                   System.out.println("Time used: " + tmp.totalTimeUsed);
                   PlaySound();
                   break;
                }
                
                System.out.println("#ConnectionChecker: New Values received.");
                newValues.overrideValues(tmp);
                //displayImage(Base64toImage(newValues.editedImage));
                break;
            }
            catch(IOException | ClassNotFoundException e) {
                try {
                    Thread.sleep(100);
                    System.err.println("#ConnectionChecker: " + e.getMessage());
                } catch (InterruptedException ex) {}
            }
        }        
    }
    
    public void PlaySound() throws IOException{
    String gongFile = "C:\\Users\\livio\\Downloads\\smb_mariodie.wav";
    InputStream in = new FileInputStream(gongFile);
 
    // create an audiostream from the inputstream
    AudioStream audioStream = new AudioStream(in);
 
    // play the audio clip with the audioplayer class
    AudioPlayer.player.start(audioStream);
    
    //Allenfalls auskommentieren
    System.out.println("---------------------------------------------------------------");
    System.out.println("--------------Endsignal erhalten!!!!!!!!-----------------------");    
    System.out.println("---------------------------------------------------------------");
    BufferedImage img=ImageIO.read(new File("C:\\temp\\finish.jpg"));
    displayImage(img);
    }
    
        public void displayImage(Image img2) {
        //BufferedImage img=ImageIO.read(new File("/HelloOpenCV/lena.png"));
        ImageIcon icon = new ImageIcon(img2);
        JFrame frame = new JFrame();
        frame.setLayout(new FlowLayout());
        frame.setSize(img2.getWidth(null) + 50, img2.getHeight(null) + 50);
        JLabel lbl = new JLabel();
        lbl.setIcon(icon);
        frame.add(lbl);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }
       
    public BufferedImage Base64toImage(String imageString){
          BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }
}
