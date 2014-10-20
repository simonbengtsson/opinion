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
import sun.rmi.runtime.Log;



/**
 *
 * @author stek
 */
@Path("posts")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PostController {
    @EJB
    PostRegistry postReg;
    
    @GET
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
    public String createPost(@QueryParam("user") User user, @QueryParam("text") String text) {
        Post newPost = new Post(user, text);
        postReg.create(newPost);
        System.out.println(this.getClass().getName() + ": " + "post created: " + user.toString() + ", text: " + newPost.toString());
        return "Create new post with text: " + text;
    }
    
    @PUT
    public String updatePost(@QueryParam("id") long postId, @QueryParam("text") String newText) {
        return "postId: " + postId + ", text: " + newText;
    }
    
    @DELETE
    public String deletePost(@QueryParam("id") long postId) {
        return "post should be deleted: " + postId;
    }
    
    @GET
    @Path("{id}")
    public String findPost(@PathParam("id") long id) {
        return "postId to be found: " + id;
    }
}
