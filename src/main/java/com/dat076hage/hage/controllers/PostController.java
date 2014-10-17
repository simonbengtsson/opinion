/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;

import com.dat076hage.hage.Post;
import com.dat076hage.hage.User;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.*;



/**
 *
 * @author stek
 */
@Path("/posts")
public class PostController {
  
    public PostController() {
        initializeTest();
    }
    
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON}) 
    public String findAll(@QueryParam("username") String userName, @QueryParam("hagetag") String hageTag) {
        if(hageTag != null)
            return "Username: " + userName + ", searched hagetag: " + hageTag;
        else return "Username: " + userName;
    };

    
    @GET
    @Path("/findByUserId")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findByUserId(@QueryParam("id") long id) {
        List<User> userList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        
        // subtract id with 1 to compensate for list indexing
        postList.addAll(userList.get(((int) id - 1)).getPosts());
        
        Gson gson = new Gson();
        return gson.toJson(postList);
        
    }
    
    private List<User> initializeTest() {
        
        List<User> userList = new ArrayList<>();
        
        User firstUser = new User("steken", "", "");
        User secondUser = new User("alfons", "", "");
        User thirdUser = new User("glenn", "", "");
        
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        
        firstUser.createNewPost("hej1");
        firstUser.createNewPost("hej2");
        firstUser.createNewPost("hej3");
        
        secondUser.createNewPost("hej");
        secondUser.createNewPost("fisk");
        
        thirdUser.createNewPost("ninja");
        
        return userList;
    }
    
    
    @POST
    @Path("/createPost")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    public String create(@FormParam("userID") int userId, @FormParam("content") String content) {
        
        //User.getUsers().get(userId - 1).addPost(new Post(content));
        Gson gson = new Gson();
        
        return gson.toJson("testweb");
        
    }
    
    
}
