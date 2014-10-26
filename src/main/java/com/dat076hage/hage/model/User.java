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
    @Expose private String name;
    
    @Expose private String description;
    
    @Temporal(javax.persistence.TemporalType.DATE)
    @Expose private Date memberSince;
    
    /*@JoinTable(name = "stalkers", joinColumns = {
        @JoinColumn(name = "stalker", referencedColumnName = "username", nullable = false)
    }, inverseJoinColumns = {
        @JoinColumn(name = "target", referencedColumnName = "username", nullable = false)})*/
    @ManyToMany
    private List<User> following;
    
    @ManyToMany(mappedBy = "following")
    private List<User> followers;
    
    @OneToMany(mappedBy = "user") 
    private List<Post> posts;

    @Expose private String picture;

    private String twitterApiHash;
    private String passwordHash;

    public User(){
    }

    public User(String username, String description, String passwordHash, String twitterApiHash, String picture, String name){

        this.username = username;
        this.name = name;
        this.description = description;
        this.passwordHash = passwordHash;
        this.twitterApiHash = twitterApiHash;
        this.picture = picture;
        
        memberSince = new Date();
        posts = new ArrayList<>();
        following = new ArrayList<>();
        followers = new ArrayList<>();
        
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        return posts;
    }
    
    public List<Post> getPostsRange(int fromIndex, int toIndex) {
        return posts.subList(fromIndex, toIndex);
    }


    public List<User> getFollowing(){


        return new ArrayList<>(following);
    }
    
    public List<User> getFollowers(){
        return new ArrayList<>(followers);

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
        posts.add(post);
        return post;
    }
    
    
    // ACTIONS WITH FOLLOWED USERS
    
    public void follow(User user){
        user.followers.add(this);
        following.add(user);
    }
    

    public void unfollow(User user){
        following.remove(user);
        user.followers.remove(this);
    }
    
    public boolean isFollowedBy(User user) {
        return followers.contains(user);
    }
    
    public void emptyUsersIAmFollowing(){
        following.clear();
    }
    
    public void emptyUsersWhoArefollowersOfMe(){
        followers.clear();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        User user = (User) o;
        return username.equals(user.username);
    }

    @Override
    public int hashCode() {
        return username != null ? username.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "User{" +
                "username='" + username + '\'' +
                ", description='" + description + '\'' +
                ", memberSince=" + memberSince +
                ", following=" + following +
                ", followers=" + followers +
                ", posts=" + posts +
                ", picture='" + picture + '\'' +
                ", twitterApiHash='" + twitterApiHash + '\'' +
                ", passwordHash='" + passwordHash + '\'' +
                '}';
    }
}
