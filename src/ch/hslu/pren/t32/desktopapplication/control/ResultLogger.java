/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ch.hslu.pren.t32.desktopapplication.control;

import ch.pren.model.ValueItem;
import ch.pren.model.ConfigurationItem;
import java.io.Serializable;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author Niklaus
 */
public class ResultLogger implements  Observer, Serializable {
    private ValueItem values;
    
    public ResultLogger() {
        this.values = ValueItem.getInstance();
    }
    
    public void addItem(ValueItem newValues) {
        this.values = newValues;
    }

    @Override
    public void update(Observable o, Object item) {
        if(item.getClass().equals(ValueItem.class)) {
            
        }
        if(item.getClass().equals(ConfigurationItem.class)) {
            
        }
    }
    
}
