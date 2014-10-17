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
import javax.ejb.EJB;
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
    @EJB
    UserRegistry userReg;
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    @Path(value = "/persist")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findAll(){
        User user = new User("kimkling", "About me", "1234");
        
        userReg.create(user);
        
        return "{'message':'User persisted'}";
    }
    
    @GET
    @Path(value = "/username")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String find(@PathParam("username") final String username) {
        User user = userReg.find("kimkling");
        return gson.toJson(user);
        
    }
}
