/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.User;
import com.dat076hage.hage.UserRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;


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
    public String createUser(String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        System.out.print(json);
        String username = json.get("username").getAsString();
        String description = json.get("description").getAsString();
        String hash = "uniqueHASH";
        User user = new User(username, description, hash);
        
        return gson.toJson(user);
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
