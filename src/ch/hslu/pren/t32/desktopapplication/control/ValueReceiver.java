/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.model.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Niklaus
 */
public class ValueReceiver {
    private ValueItem values;
    
    public ValueReceiver() {
        this.values = ValueItem.getInstance();
    }
    
    public void setItem(ValueItem newValues) {
        this.values = newValues;
    }
    
    public BufferedImage getOriginalImage() {
        BufferedImage originalImage = Base64toImage(values.originalImage);
        if(originalImage == null) {
            System.out.println("OriginalImage could not be loaded.");
        }
        return originalImage;
    }
    
    public BufferedImage getEditedImage() {
        BufferedImage editedImage = Base64toImage(values.editedImage);
        if(editedImage == null) {
            System.out.println("EditedImage could not be loaded.");
        }
        return editedImage;
    }
    
    public int getMainArea() {
        return values.mainArea;
    }
    
    public int getObjectBorder() {
        return values.objectBorder;
    }
    
    public int getTotalTimeUsed() {
        return values.totalTimeUsed;
    }
    
    public boolean hasFoundShape() {
        return values.foundShape;
    }
    
    public double getCalculatedAngle() {
        return values.calculatedAngle;
    }
    
    //ToDo: Methode löschen wenn wirklich nicht mehr gebraucht
    private BufferedImage loadImage(File file){
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException ex) {
            System.err.println("Error in InputHandler: " + ex.getMessage());
        }
        return null;
    }
    
    //Added by Livio
     private BufferedImage Base64toImage(String imageString){
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
