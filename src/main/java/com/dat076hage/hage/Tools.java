/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.Post;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

/**
 *
 * @author kim
 */
public class Tools {
    
    public static ArrayList<Post> sortPosts(ArrayList<Post> posts){
        ArrayList<Post> shallowCopy = new ArrayList<>(posts);
        Collections.sort(shallowCopy, new PostComparator());
        return shallowCopy;
    }
    
    
}

class PostComparator implements Comparator<Post> {
    @Override
    public int compare(Post o1, Post o2) {
        return o1.getPostDate().compareTo(o2.getPostDate());
    }
}