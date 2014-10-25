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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
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
public class User implements Serializable {
    @Id
    @Column(nullable = false, updatable = false, length = 50)
    @Expose private String username;
    
    @Expose private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date memberSince;
    
    /*@JoinTable(name = "stalkers", joinColumns = {
        @JoinColumn(name = "stalker", referencedColumnName = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "target", referencedColumnName = "username", nullable = false)})*/
    @ManyToMany
    @Expose private List<User> usersIAmFollowing;
    
    @ManyToMany(mappedBy = "usersIAmFollowing")
    @Expose private List<User> usersWhoArefollowersOfMe;
    
    @OneToMany(mappedBy = "user") 
    @Expose private List<Post> posts;

    @Expose private String picture;

    private String twitterApiHash;
    private String passwordHash;

    public User(){
    }

    public User(String username, String description, String passwordHash, String twitterApiHash, String picture){

        this.username = username;
        this.description = description;
        this.passwordHash = passwordHash;
        this.twitterApiHash = twitterApiHash;
        this.picture = picture;
        
        memberSince = new Date();
        posts = new ArrayList<>();
        usersIAmFollowing = new ArrayList<>();
        usersWhoArefollowersOfMe = new ArrayList<>();
        
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
    
    public List<Post> getPostsRange(int fromIndex, int toIndex) {
        return posts.subList(fromIndex, toIndex);
    }


    public List<User> getUsersIAmFollowing(){

        return new ArrayList<>(usersIAmFollowing);
    }
    
    public List<User> getUsersWhoArefollowersOfMe(){
        return new ArrayList<>(usersWhoArefollowersOfMe);
    }
    
    public String getHash(){
        return passwordHash;
    }
    
    public String getTwitterApiHash(){
        return twitterApiHash;
    }
   
    public void setDescription(String description) {
        this.description = description;
    }
    
    // ACTIONS WITH POSTS
    
    public void assaignPost(Post post){
        posts.add(post);
    }
    
    public void removePost(Post post){
        for(int i = 0; i < posts.size(); i++){
            if(posts.get(i).getId() == post.getId()){
                posts.remove(i);
                break;
            }
        }
    }
    
    public Post createNewPost(String content){
        Post post = new Post(this, content);
        if(posts == null) {
            posts = new ArrayList<Post>();
        }
        posts.add(post);
        return post;
    }

    
    // ACTIONS WITH FOLLOWED USERS

    public void follow(User user){
        user.usersWhoArefollowersOfMe.add(this);
        usersIAmFollowing.add(user);
    }
    

    public void unfollow(User user){
        
        for(int i = 0; i < usersIAmFollowing.size(); i++){
            if(usersIAmFollowing.get(i).getUsername().equals(user.getUsername())){
                usersIAmFollowing.remove(i);
                break;
            }
        }
        
        for(int i = 0; i < user.usersWhoArefollowersOfMe.size(); i++){
            if(user.usersWhoArefollowersOfMe.get(i).getUsername().equals(user.getUsername())){
                user.usersWhoArefollowersOfMe.remove(i);
                break;
            }
        }
    }
    
    public void emptyUsersIAmFollowing(){
        usersIAmFollowing.clear();
    }
    
    public void emptyUsersWhoArefollowersOfMe(){
        usersWhoArefollowersOfMe.clear();
    }
    
    @Override
    public String toString(){
        return String.format("username: %s | description: %s", username, description);
    }
    
}
