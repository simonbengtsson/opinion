/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 *
 * @author kim
 */
@Entity
public class Hate implements Serializable {
    @Id
    @ManyToOne
    @Expose private User user;
    
    @Id
    @ManyToOne
    @Expose private Post post;
    
    @Expose private Date dateTime;
    
    public Hate(){
        
    }
    
    public Hate(User user, Post post){
        this.user = user;
        this.post = post;
        this.dateTime = new Date();
    }
    
}