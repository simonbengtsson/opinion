package com.dat076hage.hage.controllers;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.model.Hate;
import com.dat076hage.hage.HateRegistry;
import com.dat076hage.hage.auth.ApiKey;
import com.dat076hage.hage.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.net.URI;
import javax.ejb.EJB;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.*;

@Path("hates")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class HateController {
    
    @EJB
    HttpServletRequest request;
    
    @EJB
    HateRegistry hateReg;
    
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
    public Response createHate(@HeaderParam("Authorization") String authorization, String contentBody){
        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        long postId = json.get("postId").getAsLong();
        Hate hate = new Hate(askingUser, null);
        hateReg.create(hate);
        return Response.created(URI.create(request.getServletContext() + "/api/hate/" + postId)).build();
    }
}
