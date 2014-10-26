package com.dat076hage.hage;

import com.dat076hage.hage.model.Post;

import java.util.Comparator;

class PostComparator implements Comparator<Post> {
    
    @Override
    public int compare(Post o1, Post o2) {
        return o1.getDate().compareTo(o2.getDate());
    }
    
}