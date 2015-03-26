/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.hslu.pren.t32.model.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

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
        BufferedImage originalImage = loadImage(values.originalImage);
        if(originalImage == null) {
            System.out.println("OriginalImage could not be loaded.");
        }
        return originalImage;
    }
    
    public BufferedImage getEditedImage() {
        BufferedImage editedImage = loadImage(values.editedImage);
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
    
    private BufferedImage loadImage(File file){
        try {
            BufferedImage image = ImageIO.read(file);
            return image;
        } catch (IOException ex) {
            System.err.println("Error in InputHandler: " + ex.getMessage());
        }
        return null;
    }
}
