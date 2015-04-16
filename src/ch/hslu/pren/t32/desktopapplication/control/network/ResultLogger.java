/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control.network;

import ch.hslu.pren.t32.model.*;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import javax.imageio.ImageIO;
import sun.misc.BASE64Decoder;

/**
 *
 * @author Niklaus
 */
public class ResultLogger implements Serializable {
    private ValueItem values;
    
    public ResultLogger() {
        this.values = ValueItem.getInstance();
    }
    
    public void addItem(ValueItem newValues) {
        this.values = newValues;
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
    
}
