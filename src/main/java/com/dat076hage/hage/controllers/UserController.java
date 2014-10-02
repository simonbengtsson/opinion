/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.*;
import java.util.ArrayList;
import java.util.List;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author kim
 */
@Path("/users")
public class UserController {
    
    @GET
    @Produces(value = {MediaType.APPLICATION_JSON})
    public Response findAll(){
        List<User> userList = new ArrayList<User>();
        
        userList.add(new User("Kim", "Kling"));
        userList.add(new User("Simon", "Bengtsson"));
        userList.add(new User("Simon", "Bengtsson"));

        GenericEntity<List<User>> genericEntProd = new GenericEntity<List<User>>(userList) {};
        
        
        return Response.ok(genericEntProd).build();
    }
}
