/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author stek
 */
public class Post {
    
    @Expose private String content;
    @Expose private long postId;
    private String picturePath;
    private Date postDate;
    private String link;
    private ArrayList hageTagList;
    private int[] coordinates = new int[2];
    
    
    private static int idCount;
    
    public Post (String content) {
        this.content = content;
        this.postId = getId();        
    }
    
    
    private int getId() {
        return ++idCount;
    }
    
}
