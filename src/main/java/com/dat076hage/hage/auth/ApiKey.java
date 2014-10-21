/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.auth;

import com.dat076hage.hage.model.User;
import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.rmi.server.UID;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author kim
 */
@Entity
public class ApiKey implements Serializable {
    @Id
    @Expose private String apiKey;
    
    @ManyToOne
    private User user;
    
    public ApiKey(){
        this.apiKey = new UID().toString();
    }
    
    public ApiKey(User user){
        this.user = user;
        this.apiKey = new UID().toString();
    }
    
    public String getKey(){
        return apiKey;
    }
    
    public User getUser(){
        return user;
    }
}
