/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.auth.ApiKey;
import com.dat076hage.hage.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.net.URI;
import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kim
 */
@Path("apikeys")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class ApiKeyController {
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    @EJB
    UserRegistry userReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @POST
    public String createApiKey(String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String username = json.get("username").getAsString();
        String password = json.get("password").getAsString();
        
        User user = userReg.find(username);
        // Validate password
        //if( somFunction( password ) . equals( user.getHash() ) )
        
        ApiKey apiKey = new ApiKey(user);
        
        apiKeyReg.create(apiKey);
        
        return gson.toJson(apiKey);
    }
    
    @DELETE
    @Path("{username}")
    public Response deleteApiKey(@PathParam("username") String username) {
        return null;
    }
    
}
