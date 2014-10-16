/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

/**
 *
 * @author kim
 */
@Entity
public class User {
    @Id
    @Column(nullable = false, updatable = false, length = 50)
    @Expose private String username;
    private String hash;
    @Expose private String description;
    
    //TODO: http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
    @ManyToMany
    private List<User> following = new ArrayList<User>();
    
    @OneToMany(mappedBy = "user") 
    private List<Post> posts = new ArrayList<Post>();
    
    //TODO: Remove when database is in place
    @Expose private static List<User> users = new ArrayList<User>();
    
    public User(){
        this("");
    }
    
    public User(String username){
        this.username = username;
        users.add(this);
    }
    
    public void addPost(Post post){
        posts.add(post);
    }
    
    public List<Post> getPosts(){
        return new ArrayList<Post>(posts);
    }
    
    public String toString(){
        return String.format("username:%s", username);
    }
    
    public static List<User> getUsers(){
        return new ArrayList<User>(users);
    }
    
    public static void initTestUsers(){
        if(User.getUsers().isEmpty()){
            new User("KimKling");
            new User("SimonBengtsson");
            new User("SimonPlanhage");
            new User("CarolineKabat");
        }
    }
}
