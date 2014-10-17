/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

/**
 *
 * @author stek
 */
public class GPS {
    
    private double[] coordinates = new double[2];
    
    public GPS() {}
    
    public GPS(double x, double y) {
        this.coordinates[0] = x;
        this.coordinates[1] = y;
    }
    
    public double[] getCoordinates() {
        return coordinates;
    }
    
    
    
}
