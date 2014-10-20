/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;

import com.dat076hage.hage.Post;
import com.dat076hage.hage.PostRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
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
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    public String findAll() {
        Post post = postReg.find(1L);
        return gson.toJson(post);
    }
    
    @POST
    public String createPost(Post post) {
        
        postReg.create(post);
        return gson.toJson(post);
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
        return gson.toJson(postReg.find(id));
    }
}
