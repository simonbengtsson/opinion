/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

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
    
    public String getText(){
        return content;
    }
    
    public String toString() {
        return "post content: " + content;
    }
    
}
