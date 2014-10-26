/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage.controllers;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import com.dat076hage.hage.UserRegistry;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.persistence.EntityExistsException;
import javax.persistence.PersistenceException;
import javax.servlet.ServletContext;
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

    @javax.ws.rs.core.Context
    ServletContext context;
    
    @EJB
    UserRegistry userReg;
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    public Response getFeaturedUsers(@HeaderParam("Authorization") String authorization) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        List<User> list = userReg.getFeaturedUsers();
        List<JsonObject> respList = new ArrayList<>();
        for(User u : list) {
            respList.add(prepareUser(u, askingUser));
        }
        return Response.ok(gson.toJson(respList)).build();
    }
    
    /*
     * Create User via registration form. Not currently used, but might be used in the future.
     */
    @POST
    public Response createUser(String contentBody) {
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        String username = json.get("username").getAsString();
        String description = json.get("description").getAsString();
        String password = json.get("password").getAsString();
        String passHash = BCrypt.hashpw(password, BCrypt.gensalt());
        
        User user = new User(username, description, passHash, "", "", "");
        try{
            userReg.create(user);
        }catch(EntityExistsException e){
            Response.notAcceptable(null).build();
        }catch(PersistenceException e){
            Response.notAcceptable(null).build();
        }
        
        return Response.created(URI.create("/users/" + username)).build();
    }
    
    /*
     * Update the user who sent the request with incoming data.
     */
    @PUT
    @Path("/me")
    public Response updateUser(@HeaderParam("Authorization") String authorization, String contentBody) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        
        String description = json.get("description").getAsString();
        String name = json.get("name").getAsString();
        askingUser.setDescription(description);
        askingUser.setName(name);
        userReg.update(askingUser);
        
        String str = gson.toJson(askingUser);
        return Response.ok(str).build();
    }
    
    @DELETE
    @Path("/me")
    public Response deleteUser(@HeaderParam("Authorization") String authorization) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        userReg.delete(askingUser.getUsername());
        return Response.noContent().build();
    }
    
    /**
     * Retrieve information about the user who made the request.
     * Useful when newly logged in via Twitter and front-end needs to get username and picture.
     */
    @GET
    @Path("/me")
    public Response findMe(@HeaderParam("Authorization") String authorization){
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        String json = gson.toJson(prepareUser(askingUser, askingUser));
        return Response.ok(json, MediaType.APPLICATION_JSON).build(); 
    }
    
    @GET
    @Path("{username}")
    public Response findUser(@HeaderParam("Authorization") String authorization, @PathParam("username") String username){
        User askingUser = apiKeyReg.validateApiKey(authorization);
        User user = userReg.find(username);
        if(user == null) {
            return Response.status(404).build();
        }
        String json = gson.toJson(prepareUser(user, askingUser));
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }

    // TODO refactor so that count queries is used
    private JsonObject prepareUser(User user, User askingUser) {
        JsonObject obj = gson.toJsonTree(user).getAsJsonObject();
        obj.addProperty("followersCount", user.getFollowers().size());
        obj.addProperty("followingCount", user.getFollowing().size());
        obj.addProperty("isFollowing", askingUser.isFollowing(user));
        obj.addProperty("opinionsCount", user.getPosts().size());
        return obj;
    }
    
    @POST
    @Path("{username}/followers")
    public Response addToFollowers(@HeaderParam("Authorization") String authorization, @PathParam("username") String username){
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        User user = userReg.find(username);
        if(user == null) {
            return Response.status(404).build();
        }
        askingUser.follow(user);
        user.getFollowers().add(askingUser);
        
        try {
            userReg.update(askingUser);
            userReg.update(user);
        } catch(EJBException e) {
            System.out.println("Already following user. Followers count: " + askingUser.getFollowing().size());
            return Response.status(Response.Status.BAD_REQUEST).entity("{\"msg\": \"Already following\"}").build();
        }
        
        return Response.ok().build();
    }
    
    @GET
    @Path("{username}/followers")
    public Response getFollowers(@HeaderParam("Authorization") String authorization, @PathParam("username") String username){
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        User user = userReg.find(username);
        //System.out.println(user);
        if(user == null) {
            return Response.status(404).build();
        }
        List<User> users = user.getFollowers();
        for(User listedUser : users){
            listedUser.emptyRelations(); // To prevent circular arrays in gson. This wont be saved in DB.
        }
        String json = gson.toJson(users);
        
        System.out.println("*** GET Followers ***");
        System.out.println("Asking user " + askingUser.getUsername() + " followers: " + askingUser.getFollowers()+ " following: " + askingUser.getFollowing());
        System.out.println("User " + user.getUsername() + " followers: " + user.getFollowers() + " following: " + user.getFollowing());
        
        return Response.ok(json, MediaType.APPLICATION_JSON).build();
    }
    
    @DELETE
    @Path("{username}/followers/me")
    public Response removeMeAsFollower(@HeaderParam("Authorization") String authorization, @PathParam("username") String username){
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        User user = userReg.find(username);
        if(user == null) {
            return Response.status(404).build();
        }
        askingUser.unfollow(user);
        userReg.update(askingUser);
        userReg.update(user);
        return Response.ok().build();
    }

    @GET
    @Path("{username}/posts")
    public Response removeMeAsFollower(@PathParam("username") String username){
        User user = userReg.find(username);
        if(user == null) {
            return Response.status(404).build();
        }
        
        List<Post> posts = user.getPosts();
        posts.add(new Post(user, "This is not an opinion"));
        return Response.ok(gson.toJson(posts)).build();
    }
    
}
