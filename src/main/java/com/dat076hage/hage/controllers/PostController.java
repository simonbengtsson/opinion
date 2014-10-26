/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.dat076hage.hage.controllers;


import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.PostRegistry;
import com.dat076hage.hage.Tools;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.auth.ApiKey;
import com.dat076hage.hage.model.GPS;
import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.EJBException;
import javax.ws.rs.*;
import javax.ws.rs.core.*;


/**
 *
 * @author stek
 */
@Path("posts")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PostController {
    @EJB
    PostRegistry postReg;
    
    @EJB
    UserRegistry userReg;
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    public User validateApiKey(String key){
        if (key==null)
            return null;
        ApiKey apiKey = apiKeyReg.find(key);
        if(apiKey != null){
            return apiKey.getUser();
        }else{
            return null;
        }
    }
    
    @GET
    public String findAll(@HeaderParam("Authorization") String authorization, 
            @DefaultValue("1000") @QueryParam("lat") long lat, @DefaultValue("1000") @QueryParam("lon") long lon,
            @DefaultValue("global") @QueryParam("posttype") String postType, 
            @QueryParam("from") int fromIndex, @QueryParam("to") int toIndex) {
        


        //User askingUser = userReg.find("simonopinion");

        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            //return "unauthorized";
        }
        User user = userReg.find("simonopinion");
        
        /*
        List<String> tags = new ArrayList<>();
        tags.add("awesome");
        tags.add("hashtags");
        
        Post kimPost1 = new Post(kim, "This is my first, simple Post!");
        Post kimPost2 = new Post(kim, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
        Post kimPost3 = new Post(kim, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
        Post simonBPost1 = new Post(simonB, "This is my first, simple Post!");
        Post simonBPost2 = new Post(simonB, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
        Post simonBPost3 = new Post(simonB, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
        Post simonPPost1 = new Post(simonP, "This is my first, simple Post!");
        Post simonPPost2 = new Post(simonP, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
        Post simonPPost3 = new Post(simonP, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
        Post carolinePost1 = new Post(caroline, "This is my first, simple Post!");
        Post carolinePost2 = new Post(caroline, "This is my second post, with link and position!", "", "http://feber.se", new ArrayList(), new GPS(57.689470, 11.973038));
        Post carolinePost3 = new Post(caroline, "This is my third, #awesome post with #hashtags!", "", "", tags, null);
        postReg.create(kimPost1);
        postReg.create(kimPost2);
        postReg.create(kimPost3);
        postReg.create(simonBPost1);
        postReg.create(simonBPost2);
        postReg.create(simonBPost3);
        postReg.create(simonPPost1);
        postReg.create(simonPPost2);
        postReg.create(simonPPost3);
        postReg.create(carolinePost1);
        postReg.create(carolinePost2);
        postReg.create(carolinePost3);
        */
        
        User kim = userReg.find("kim");
        User caroline = userReg.find("caroline");
        User simonB = userReg.find("simonb");
        User simonP = userReg.find("simonp");
        user.follow(kim);
        user.follow(caroline);
        user.follow(simonB);
        user.follow(simonP);
        try {
                userReg.update(kim);
                userReg.update(caroline);
                userReg.update(simonB);
                userReg.update(simonP);
                userReg.update(user);
            } catch(EJBException e) {
                // If already following
                return "aboo";
            }
        
        /*
        if ((lat != 1000L) && (lon != 1000L)) {
            // search with coordinates lat+- 0.5, lon 1
        }
        */
        
        /*
        Först loggar jag in via twitter-knappen med mitt vanliga twitterkonto,
        därefter får jag ändå hårdkoda in vilken användare jag är, eller vem det är som frågar kan man säga,
        på rad 74 i samma fil. Debuggar man rad 133 och framåt under körning ser man att man får in listan med
        users jag följer och de ska ha posts enligt databasen, men när jag försöker få ut postsen blir listan ändå tom.
        
        */
        List<Post> postList = new ArrayList<>();
        
        if (postType.equals("following")) {

            List<User> followList = user.getFollowers();
            for(User u : followList) {
                for(Post p: u.getPosts())
                    System.out.println(p.toString());
                postList.addAll(userReg.find(u.getUsername()).getPosts());

            }
            
            for(User u : followList) {
                u.emptyUsersIAmFollowing(); // prevent circular arrays in gson
                u.emptyUsersWhoArefollowersOfMe();
            }  
            
            
        }
        /*
        if (postType.equals("global")) {
            ArrayList<Post> globalList = new ArrayList<>();   
        }
       */
        
        return gson.toJson(postList);
        
        // Get the most recent 10  posts from the followed users...
        
    }
    
    @POST
    public Response createPost(@HeaderParam("Authorization") String authorization, String contentBody) {
        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(Response.Status.UNAUTHORIZED).build();
        }
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String text = json.get("text").getAsString();
        
        JsonElement picObj = json.get("picture");
        String picture = null;
        if(picObj != null) {
            picture = picObj.getAsString();
        }
        JsonElement latObj = json.get("lat");
        JsonElement lonObj = json.get("lon");
        GPS gps = null;
        
        if (latObj != null && lonObj != null) {
            gps = new GPS(latObj.getAsDouble(), lonObj.getAsDouble());
        }

        Post newPost = new Post(askingUser, text, picture, "", new ArrayList<String>(), gps);

        try {
            postReg.create(newPost);
        } catch (Exception e) { // duplicate posts in database
            return Response.notAcceptable(null).build();
        }

        Response.ResponseBuilder res = Response.status(Response.Status.CREATED);
        res.entity(gson.toJson(newPost));
        res.contentLocation(URI.create(Tools.URL_FOLDER + "/api/posts/" + newPost.getId()));

        return res.build();
    }
    
    // Working
    @PUT
    @Path("{id}")
    public Response updatePost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId, String contentBody) {
        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
            //return "{\"error\": \"401, Not authorized\"}";
        }
        
        //TODO: Validate if this user should be able to edit post...
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String newContent = json.get("content").getAsString();
        Post p = postReg.find(postId);
        p.setText(newContent);
        postReg.update(p);
        
        return Response.created(URI.create("/posts/" + postId)).build();
    }
    
    // Working
    @DELETE
    @Path("{id}")
    public Response deletePost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId) {
        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
            //return "{\"error\": \"401, Not authorized\"}";
        }
        postReg.delete(postId);
        return Response.noContent().build();
    }
    
    // Working
    @GET
    @Path("{id}")
    public String findPost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId) {
        User askingUser = validateApiKey(authorization);
        if(askingUser == null){
            //return Response.status(401).build();
            return "{\"error\": \"401, Not authorized\"}";
        }
        Post post = postReg.find(postId);
        return gson.toJson(post);
    }
}
