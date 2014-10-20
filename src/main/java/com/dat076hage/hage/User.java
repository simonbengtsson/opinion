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
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
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
    @Expose private ArrayList<User> following;
    
    @OneToMany(mappedBy = "user") 
    @Expose private ArrayList<Post> posts;
    
    public User(){
    }
    
    public User(String username, String description, String hash){
        posts = new ArrayList<>();
        following = new ArrayList<>();
        this.username = username;
        this.description = description;
        this.hash = hash;
    }
    
    public String getUsername(){
        return username;
    }
    
    public String getDescription(){
        return description;
    }
    
    public Date getMemberDate(){
        return new Date(memberSince.getTime());
    }
    
    public List<Post> getPosts(){
        return new ArrayList<>(posts);
    }
    
    public List<User> getFollowedUsers(){
        return new ArrayList<>(following);
    }
    
    public void setDescription(String description) {
        this.description = description;
    }
    
    public void assaignPost(Post post){
        posts.add(post);
    }
    
    public void addFollowedUsers(User user){
        following.add(user);
    }
    
    public void removeFollowedUsers(User user){
        
        for(int i = 0; i < following.size(); i++){
            if(following.get(i).getUsername().equals(user.getUsername())){
                following.remove(i);
                break;
            }
        }
    }
    
    public Post createNewPost(String content){
        Post post = new Post(this, content);
        posts.add(post);
        return post;
    }
    
    @Override
    public String toString(){
        return String.format("username: %s | description: %s", username, description);
    }

}
