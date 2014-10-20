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
import java.net.URI;
import javax.ejb.EJB;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
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
import javax.ws.rs.core.Response;


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
    public Response createUser(String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        String username = json.get("username").getAsString();
        String description = json.get("description").getAsString();
        String hash = "uniqueHASH";
        
        User user = new User(username, description, hash);
        //TODO: Exceptions not caught?
        try{
            userReg.create(user);
        }catch(EntityExistsException e){
            Response.notAcceptable(null).build();
        }catch(PersistenceException e){
            Response.notAcceptable(null).build();
        }
        
        return Response.created(URI.create("/users/" + username)).build();
    }
    
    @PUT
    @Path("{username}")
    public Response updateUser(@PathParam("name") String username, String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        String description = json.get("description").getAsString();
        
        User user = userReg.find(username);
        user.setDescription(description);
        
        userReg.update(user);
        
        return Response.created(URI.create("/users/" + username)).build();
    }
    
    @DELETE
    public String deleteUser(@QueryParam("dusername") String username) {
        return "user to be deleted: " + username;
    }
        
    @GET
    @Path("{username}")
    public String findUser(@PathParam("username") String username){
        User user = userReg.find(username);
        return gson.toJson(user);
    }
}
