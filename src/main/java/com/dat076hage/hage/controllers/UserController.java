/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kim
 */
@Path("/users")
public class UserController {
    
    @PersistenceContext
    private EntityManager em;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    @Path(value = "/persist")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findAll(){
        User user = new User("kimkling", "About me", "1234");
        em.persist(user);
        return "{'message':'User persisted'}";
        //return gson.toJson(User.getUsers());
    }
    
    @GET
    @Path(value = "/username/{username}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String find(@PathParam("username") final String username) {
        User user = em.find(User.class, username);
        return gson.toJson(user);
        
    }
}
