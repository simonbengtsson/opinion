/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;

import com.dat076hage.hage.Post;
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
    public Response findAll() {
        List<Post> postList = new ArrayList<Post>(); 
        Post post = new Post();
        Post post2 = new Post();
        post.setMsg("hej");
        post2.setMsg("hej2");
        postList.add(post);
        postList.add(post2);
        

        GenericEntity<List<Post>> genericEntProd = new GenericEntity<List<Post>>(postList) {};
        

        return Response.ok(genericEntProd).build();
    }  
    
}
