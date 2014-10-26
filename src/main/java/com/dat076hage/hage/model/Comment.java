/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.model;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;

/**
 *
 * @author kimkling
 */
@Entity
public class Comment implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose private long commentId;
    
    @Expose private String text;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date postDate;
    
    @Expose private List<String> tagList;
    
    @ManyToOne
    private User user;
    
    @ManyToOne
    private Post post;
    
    public Comment(){
    } 
    
    public Comment(User user, Post post, String text) {
        this.user = user;
        this.post = post;
        this.text = text;
        this.postDate = new Date();
        
        tagList = new ArrayList<>();
    }
    
    public Comment(User user, Post post, String text, ArrayList<String> tagList) {
        this.user = user;
        this.post = post;
        this.text = text;
        this.postDate = new Date();
        
        this.tagList = tagList;
    }
    
    public User getUser(){
        return user;
    }
    
    public Post getPost(){
        return post;
    }
    
    public String getText(){
        return text;
    }
    
    public Date getPostDate(){
        return new Date(postDate.getTime());
    }
    
    public List<String> getTagList(){
        return new ArrayList<>(tagList);
    }
    
    public String toString(){
        return "id: " + commentId +
                "text: " + text;
    }
    
}
