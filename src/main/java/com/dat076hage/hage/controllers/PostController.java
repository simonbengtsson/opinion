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
    
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findAll() {
        List<Post> postList = new ArrayList<>(); 
        
        
        
        return null;
    }  
    
    @GET
    @Path("/findByUserId")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findByUserId(@QueryParam("id") long id) {
        
        
        List<User> userList = new ArrayList<>();
        List<Post> postList = new ArrayList<>();
        
        
        
        User firstUser = new User("steken", "stek");
        User secondUser = new User("alfons", "aoberg");
        User thirdUser = new User("glenn", "hysen");
        
        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        
        firstUser.addPost(new Post("hej1"));
        firstUser.addPost(new Post("hej2"));
        firstUser.addPost(new Post("hej3"));
        
        secondUser.addPost(new Post("hej"));
        secondUser.addPost(new Post("fisk"));
        
        thirdUser.addPost(new Post("ninja"));
        
        
        // subtract id with 1 to compensate for list indexing
        postList.addAll(userList.get(((int) id - 1)).getPosts());
        
        
        
        
        Gson gson = new Gson();
        
        return gson.toJson(postList);
        
    }
    
    /**
    @PUT
    @Path("/createPost")
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    **/
    
}
