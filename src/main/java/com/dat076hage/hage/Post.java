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
import javax.persistence.Id;
import javax.persistence.ManyToOne;
/**
 *
 * @author stek
 */
@Entity
public class Post implements Serializable {
    
    @Expose private String content;
    @Id
    @Expose private long postId;
    private String picturePath;
    private Date postDate;
    private String link;
    private ArrayList hageTagList;
    private int[] coordinates = new int[2];
    privat
    
    
    private static int idCount;
    @ManyToOne
    private User user;
    
    public Post (String content) {
        this.content = content;
        this.postId = getId();        
    }
    
    
    private int getId() {
        return ++idCount;
    }
    
}
