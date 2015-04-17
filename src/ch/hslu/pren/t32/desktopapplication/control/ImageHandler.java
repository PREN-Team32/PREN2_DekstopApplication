/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.pren.model.ValueItem;
import com.sun.prism.paint.Color;
import java.awt.Graphics2D;
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
public final class ImageHandler {        
    protected static int INITIAL_IMAGE_WIDTH = 888;
    protected static int INITIAL_IMAGE_HEIGHT = 500;
    protected static int WIDTH_TO_OBSERVE = 488;
    protected static int HEIGHT_TO_OBSERVE = 500;
    
    private ImageHandler() {
        //static Top-Level class doesn't need to be instantiated..
    }

    public static void setObservedWidth(int WIDTH_TO_OBSERVE) {
        ImageHandler.WIDTH_TO_OBSERVE = WIDTH_TO_OBSERVE;
    }

    public static void setObservedHeight(int HEIGHT_TO_OBSERVE) {
        ImageHandler.HEIGHT_TO_OBSERVE = HEIGHT_TO_OBSERVE;
    }
    
    public static BufferedImage loadImage(String imageName) {
        File file = new File(imageName);
        BufferedImage image;
        BufferedImage tmp;
        image = new BufferedImage(INITIAL_IMAGE_WIDTH, INITIAL_IMAGE_HEIGHT, BufferedImage.TYPE_INT_ARGB);
        try {
            tmp = ImageIO.read(file);
            //Resize the picture to 888x500 px (= INITIAL_IMAGE_WIDHT & _HEIGHT)
            Graphics2D g = image.createGraphics();
            g.drawImage(tmp, 0, 0, INITIAL_IMAGE_WIDTH, INITIAL_IMAGE_HEIGHT, null);
            g.dispose();
            //Cut out the black borders (background)
            image = image.getSubimage((INITIAL_IMAGE_WIDTH-WIDTH_TO_OBSERVE)/2,(INITIAL_IMAGE_HEIGHT-HEIGHT_TO_OBSERVE)/2 , WIDTH_TO_OBSERVE, HEIGHT_TO_OBSERVE);
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
        }
        return image;
    }
    
    public static void drawVerticalLine(BufferedImage image, int x, Color color) {
        for(int y = image.getHeight()-1; y > 0; y--) {
           image.setRGB(x, y, color.getIntArgbPre());
           if(x > 1 && x < image.getWidth()-1) {
            image.setRGB(x-1, y, color.getIntArgbPre());
            image.setRGB(x+1, y, color.getIntArgbPre());
           }
        }
    }
    
    public static BufferedImage getOriginalImage(ValueItem values) {
        BufferedImage originalImage = Base64toImage(values.originalImage);
        if(originalImage == null) {
            System.out.println("#ImageHandler: OriginalImage could not be loaded.");
        }
        return originalImage;
    }
    
    public static BufferedImage getEditedImage(ValueItem values) {
        BufferedImage editedImage = Base64toImage(values.editedImage);
        if(editedImage == null) {
            System.out.println("#ImageHandler: EditedImage could not be loaded.");
        }
        return editedImage;
    }
    
    //Added by Livio
     private static BufferedImage Base64toImage(String imageString){
          BufferedImage image = null;
        byte[] imageByte;
        try {
            BASE64Decoder decoder = new BASE64Decoder();
            imageByte = decoder.decodeBuffer(imageString);
            ByteArrayInputStream bis = new ByteArrayInputStream(imageByte);
            image = ImageIO.read(bis);
            bis.close();
        } catch (Exception e) {
            System.err.println("#ImageHandler: Error in Base64toImage()." + e.getMessage());
        }
        return image;
    }
}
