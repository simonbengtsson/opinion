/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.google.gson.annotations.Expose;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Table;
import javax.persistence.Temporal;

/**
 *
 * @author kim
 */
@Entity
@Table(name = "users")
public class User implements Serializable{
    @Id
    @Column(nullable = false, updatable = false, length = 50)
    @Expose private String username;
    
    @Expose private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date memberSince;
    
    private String hash;
    
    //TODO: http://en.wikibooks.org/wiki/Java_Persistence/ManyToMany#Mapping_a_Join_Table_with_Additional_Columns
    @ManyToMany
    @Expose private List<User> following;
    
    @OneToMany(mappedBy = "user") 
    @Expose private List<Post> posts;
    
    //TODO: Remove when database is in place
    @Expose private static List<User> users = new ArrayList<User>();
    
    public User(){
    }
    
    public User(String username, String description, String hash){
        posts = new ArrayList<Post>();
        following = new ArrayList<User>();
        this.username = username;
        this.description = description;
        this.hash = hash;
        users.add(this);
    }
    
    public List<Post> getPosts(){
        return new ArrayList<Post>(posts);
    }
    
    public Post createNewPost(String content){
        Post post = new Post(this, content);
        posts.add(post);
        return post;
    }
    
    public String toString(){
        return String.format("username:%s", username);
    }
    
    public static List<User> getUsers(){
        return new ArrayList<User>(users);
    }
    
    public static void initTestUsers(){
        if(User.getUsers().isEmpty()){
            new User("KimKling", null, null);
            new User("SimonBengtsson", null, null);
            new User("SimonPlanhage", null, null);
            new User("CarolineKabat", null, null);
        }
    }
}
