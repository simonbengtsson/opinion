package com.dat076hage.hage.controllers;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("hashtags")
@Produces(value = {MediaType.APPLICATION_JSON})
@Consumes(value = {MediaType.APPLICATION_JSON})
public class HashtagController {
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    public Response getFeaturedUsers(@HeaderParam("Authorization") String authorization) {
        String[] hashtags = {"awesome", "bp15", "Lamela", "Gothenburg", "Ullevi", "awesome", "bp15", "Lamela", "Gothenburg", "Ullevi"};
        return Response.ok(gson.toJson(hashtags)).build();
    }
    
}
