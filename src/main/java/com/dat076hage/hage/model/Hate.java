/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author kim
 */
@Entity
public class Hate implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose private long id;
    
    @ManyToOne
    @Expose private User user;
    
    @ManyToOne
    @Expose private Post post;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date dateTime;
    
    public Hate(){
        
    }
    
    public Hate(User user, Post post){
        this.user = user;
        this.post = post;
        this.dateTime = new Date();
    }
    
    public long getId(){
        return id;
    }
    
    public User getUser(){
        return user;
    }
    
    public Post getPost(){
        return post;
    }
    
    /**
     * Creates a copy of the date and time, and returns it.
     * @return Date dateTime
     */
    public Date getCreatedDate(){
        return new Date(dateTime.getTime());
    }
    
}