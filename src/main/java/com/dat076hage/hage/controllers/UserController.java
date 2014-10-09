/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.User;
import com.google.gson.Gson;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author kim
 */
@Path("/users")
public class UserController {
    
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public String findAll(){
        if(User.getUsers().size() == 0){
            new User("Kim", "Kling");
            new User("Simon", "Bengtsson");
            new User("Simon", "Planhage");
            new User("Caroline", "Kabat");
        }
        Gson gson = new Gson();
        return gson.toJson(User.getUsers());
    }
}
