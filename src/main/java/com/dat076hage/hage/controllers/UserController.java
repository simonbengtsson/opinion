/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.UserRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.*;

/**
 *
 * @author kim
 */
@Path("users")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class UserController {
    @EJB
    UserRegistry userReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    

    
    @POST
    public String createUser(@QueryParam("cusername") String username, @QueryParam("description") String description) {
        
        return "create user: " + username + ", description: " + description;
    }
    
    @PUT
    public String updateUser(@QueryParam("uusername") String username, @QueryParam("description") String description) {
        
        return "update user: " + username + ", description: " + description;
    }
    
    @DELETE
    public String deleteUser(@QueryParam("dusername") String username) {
        return "user to be deleted: " + username;
    }
        
    @GET
    @Path("{name}")
    public String findUser(@PathParam("name") String name  ){
    
        return "searched username: " + name;
    
    }
}
