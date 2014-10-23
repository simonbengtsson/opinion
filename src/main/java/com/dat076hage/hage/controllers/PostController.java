/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;


import com.dat076hage.hage.PostRegistry;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.net.URI;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


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
    
    @EJB
    UserRegistry userReg;
    
    
    
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    
    @GET
    public String findAll() {
        
        return gson.toJson(postReg);
    }
    
    @POST
    @Path("{username}")
    public Response createPost(@PathParam("username") String username, String contentBody) {
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String content = json.get("content").getAsString();
        User user = userReg.find(username);
        Post newPost = new Post(user, content);
        
        try {
            postReg.create(newPost);
        } catch (Exception e) { // duplicate posts in database, TODO: generate unique postIds
            return Response.notAcceptable(null).build();
        }
        
        return Response.created(URI.create("/posts/" + newPost.getId())).build();
    }
    
    @PUT
    @Path("{id}")
    public Response updatePost(@PathParam("id") long postId, String contentBody) {
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String newContent = json.get("content").getAsString();
        Post p = postReg.find(postId);
        p.setText(newContent);
        postReg.update(p);
        
        return Response.created(URI.create("/posts/" + postId)).build();
        
    }
    
    @DELETE
    @Path("{id}")
    public Response deletePost(@PathParam("id") long postId) {
        postReg.delete(postId);
        return Response.noContent().build();
    }
    
    @GET
    @Path("{id}")
    public String findPost(@PathParam("id") long postId) {
        Post post = postReg.find(postId);
        return gson.toJson(post);
    }
}
