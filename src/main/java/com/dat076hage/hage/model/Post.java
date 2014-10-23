/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.model;

import java.io.Serializable;
import javax.persistence.Entity;
import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;
import javax.persistence.Embedded;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
/**
 *
 * @author stek
 */
@Entity
public class Post implements Serializable {
    
    @Expose private String content;
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Expose private long postId;
    @Expose private String picturePath;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date postDate;
    @Expose private String link;
    private ArrayList hageTagList;
    
    @Embedded
    @Expose private GPS position;
    
    @ManyToOne
    private User user;
    
    public Post(){
        
    }
    
    public Post (User user, String content) {
        this.user = user;
        this.content = content;
    }
    
    public Post(User user, String text, String picturePath, String link, ArrayList hageTags, GPS pos){
        this.user = user;
        this.content = text;
        this.picturePath = picturePath;
        this.link = link;
        this.hageTagList = new ArrayList(hageTags);
        this.position = pos;
    }
    
    public String getText(){
        return content;
    }
    
    public long getId(){
        return postId;
    }
    
    public GPS getPosition(){
        return new GPS(position);
    }
    
    public String getPicturePath(){
        return picturePath;
    }
    
    public Date getPostDate(){
        return new Date(postDate.getTime());
    }
    
    public String getLink(){
        return link;
    }
    
    public void setText(String text){
        this.content = text;
    }
    
    public void setPosition(GPS pos){
        this.position = new GPS(pos);
    }
    
    public void setPicturePath(String path){
        this.picturePath = path;
    }
    
    public void setPostDate(Date date){
        this.postDate = new Date(date.getTime());
    }
    
    public void setLink(String link){
        this.link = link;
    }
    
    public String toString() {
        return "post content: " + content;
    }
    
}
