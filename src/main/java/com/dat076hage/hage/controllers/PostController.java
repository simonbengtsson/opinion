/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;

import com.dat076hage.hage.Post;
import com.dat076hage.hage.PostRegistry;
import com.dat076hage.hage.User;
import com.dat076hage.hage.UserRegistry;
import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;



/**
 *
 * @author stek
 */
@Path("/posts")
public class PostController {
  
    @EJB
    PostRegistry postReg;
    
    public PostController() {
        initializeTest();
    }
    

    @GET
    @Produces(value = {MediaType.APPLICATION_JSON}) 
    public String findAll(@QueryParam("username") String userName, @QueryParam("hagetag") String hageTag) {
        
        //TODO: temporary test, to be replaced
        if(hageTag != null) { // return json with posts that are to be displayed for user and also has the following hagetag
            return "Username: " + userName + ", searched hagetag: " + hageTag;
        }
        else { // return json with posts that are to be displayed for user
            return "Username: " + userName;
        }
            
    };
    
    @POST
    @Produces(value = {MediaType.APPLICATION_JSON})
    @Consumes(value = {MediaType.APPLICATION_FORM_URLENCODED})
    public String createPost(@QueryParam("text") String text) {
        return "Create new post with text: " + text;
    }
    
    @PUT
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String updatePost(@QueryParam("postId") long postId, @QueryParam("newText") String newText) {
        return "postId: " + postId + ", text: " + newText;
    }
    
    @DELETE
    @Consumes(value = MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String deletePost(@QueryParam("postId") long postId) {
        return "post should be deleted: " + postId;
    }
    
    @GET
    @Path(value = "id")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findPost(@QueryParam("id") long postId) {
        return "postId to be found: " + postId;
    }
    
    
    
    

    private List<User> initializeTest() {
        
        List<User> userList = new ArrayList<>();
        
        User firstUser = new User("steken", "en kool stek", "2454");
        User secondUser = new User("alfons", "en kool stek", "2454");
        User thirdUser = new User("glenn", "en kool stek", "2454");

        userList.add(firstUser);
        userList.add(secondUser);
        userList.add(thirdUser);
        return userList;

    }
    
    
    
}
