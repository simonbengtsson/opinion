/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import java.util.logging.Level;
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
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findAll(){
        User.initTestUsers();
        return gson.toJson(User.getUsers());
    }
    
    @GET
    @Path(value = "{id}")
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String find(@PathParam("id") final int id) {
        User.initTestUsers();
        if(0 < id && id-1 < User.getUsers().size()){
            return gson.toJson(User.getUsers().get(id-1));
        }else{
            return "Invalid id";
        }
        
    }
}
