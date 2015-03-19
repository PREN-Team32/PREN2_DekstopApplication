/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.model;
import java.io.Serializable;


/**
 *
 * Wrapperclass used to store and transmit the configuration details for the detector.
 * @author Nikk
 */
public class ConfigurationItem implements Serializable {
    public float luminanceThreshold;
    
    //Width & Height which will be analysed (Rest of Image will be cut off)
    public int widthToObserve;
    public int heightToObserve;
    
    //Amount of visited adjacent Pixels to determine a shape.
    public int visitedPixels;
    
    //Boolean flag to indicate start
    public boolean startSignal;
    
    public ConfigurationItem() {
        this.luminanceThreshold = 0.3f;
        this.widthToObserve = 488;
        this.heightToObserve = 500;
        this.visitedPixels = 3;
        this.startSignal = false;
    }
}  
