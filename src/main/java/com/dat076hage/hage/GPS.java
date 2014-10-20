/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import javax.persistence.Embeddable;

/**
 *
 * @author stek
 */
@Embeddable
public class GPS {
    
    @Expose private double lat;
    @Expose private double lon;
    
    public GPS() {}
    
    public GPS(GPS pos){
        this.lat = pos.getLat();
        this.lon = pos.getLon();
    }
    
    public GPS(double lat, double lon) {
        this.lat = lat;
        this.lon = lon;
    }
    
    public double getLat(){
        return lat;
    }
    
    public double getLon(){
        return lon;
    }
    
}
