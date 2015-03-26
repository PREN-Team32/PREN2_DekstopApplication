/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.model;

import java.io.File;
import java.io.Serializable;

/**
 *
 * Wrapperclass used to store and transmit the results of the object detector
 * @author Niklaus
 */
public class ValueItem implements Serializable{
    private static ValueItem singeltonInstance;
    
    //Values:
    public File originalImage;
    public File editedImage;
    
    public int mainArea;
    public int objectBorder;
    public int totalTimeUsed;
    public boolean foundShape;
    public double calculatedAngle;
    
    private ValueItem() {
        this.mainArea = 0;
        this.totalTimeUsed = 0;
        this.foundShape = false;
        this.calculatedAngle = 0;
    }
    
    public static final ValueItem getInstance() {
        if(singeltonInstance == null) {
            singeltonInstance = new ValueItem();
        }
        return singeltonInstance;
    }
}
