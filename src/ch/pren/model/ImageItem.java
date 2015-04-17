/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.pren.model;

import ch.hslu.pren.t32.desktopapplication.control.ImageHandler;
import java.awt.image.BufferedImage;

/**
 *
 * @author Niklaus
 */
public class ImageItem {
    private BufferedImage editedImage;
    private BufferedImage originalImage;
    
    
    public void retrieveImages(ValueItem values) {
        this.editedImage = ImageHandler.getEditedImage(values);
        this.originalImage = ImageHandler.getOriginalImage(values);
    }

    public BufferedImage getEditedImage() {
        return editedImage;
    }

    public BufferedImage getOriginalImage() {
        return originalImage;
    }    
}
