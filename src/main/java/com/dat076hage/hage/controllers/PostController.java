/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;

import com.dat076hage.hage.Post;
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
        List<Post> postList = new ArrayList<Post>(); 
        
        
        
        return null;
    }  
    
    @GET
    @Path("/findByUserId")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findByUserId() {
        
        List<Post> postList = new ArrayList<Post>();
        Gson gson = new Gson();
        
        return gson.toJson("hej");
        
    }
    
}
