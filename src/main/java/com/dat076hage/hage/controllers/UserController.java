/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.model.User;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.auth.ApiKey;
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
import javax.ws.rs.HeaderParam;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.mindrot.jbcrypt.BCrypt;


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
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    public User validateApiKey(String key){
        ApiKey apiKey = apiKeyReg.find(key);
        if(apiKey != null){
            return apiKey.getUser();
        }else{
            return null;
        }
    }
    
    @POST
    public Response createUser(String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        
        String username = json.get("username").getAsString();
        String description = json.get("description").getAsString();
        String password = json.get("password").getAsString();
        String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User user = new User(username, description, passHash, "");
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
    
    // Working
    @PUT
    @Path("/me")
    public Response updateUser(@HeaderParam("Authentication") String authentication, String contentBody) {
        User askingUser = validateApiKey(authentication);
        if(askingUser == null){
            return Response.status(401).build();
            //return "{\"error\": \"401, Not authorized\"}";
        }
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        String description = json.get("description").getAsString();
        askingUser.setDescription(description);
        userReg.update(askingUser);
        
        return Response.created(URI.create("/users/" + askingUser.getUsername())).build();
    }
    
    @DELETE
    @Path("/me")
    public Response deleteUser(@HeaderParam("Authentication") String authentication) {
        User askingUser = validateApiKey(authentication);
        if(askingUser == null){
            return Response.status(401).build();
            //return "{\"error\": \"401, Not authorized\"}";
        }
        userReg.delete(askingUser.getUsername());
        return Response.noContent().build();
    }
    
    // Working
    @GET
    @Path("/me")
    public String findMe(@HeaderParam("Authentication") String authentication){
        User askingUser = validateApiKey(authentication);
        if(askingUser == null){
            //return Response.status(401).build();
            return "{\"error\": \"401, Not authorized\"}";
        }
        return gson.toJson(askingUser);
    }
    
    @GET
    @Path("{username}")
    public String findUser(@HeaderParam("Authentication") String authentication, @PathParam("username") String username){
        User askingUser = validateApiKey(authentication);
        if(askingUser == null){
            //return Response.status(401).build();
            return "{\"error\": \"401, Not authorized\"}";
        }
        User user = userReg.find(username);
        return gson.toJson(user);
    }
}
