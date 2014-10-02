/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

/**
 *
 * @author kim
 */
public class User {
    private long id;
    private String firstname;
    private String lastname;
    private static int idCount;
    
    public User(){
        this.id = getId();
        this.firstname = "";
        this.lastname = "";
    }
    
    public User(String firstname, String lastname){
        this.id = getId();
        this.firstname = firstname;
        this.lastname = lastname;
    }
    
    private long getId(){
        return ++idCount;
    }
    
    public String toString(){
        return String.format("id:%s firstname:%s lastname:%s", id, firstname, lastname);
    }
}
