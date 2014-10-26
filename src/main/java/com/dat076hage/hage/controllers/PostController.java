package com.dat076hage.hage.controllers;

import com.dat076hage.hage.ApiKeyRegistry;
import com.dat076hage.hage.CommentRegistry;
import com.dat076hage.hage.PostRegistry;
import com.dat076hage.hage.UserRegistry;
import com.dat076hage.hage.model.Comment;
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


import javax.servlet.ServletContext;

import javax.ws.rs.*;
import javax.ws.rs.core.*;

@Path("posts")
@Consumes(value = MediaType.APPLICATION_JSON)
@Produces(value = MediaType.APPLICATION_JSON)
public class PostController {

    @javax.ws.rs.core.Context
    ServletContext context;
    
    @EJB
    PostRegistry postReg;
    
    @EJB
    UserRegistry userReg;
    
    @EJB
    ApiKeyRegistry apiKeyReg;
    
    @EJB
    CommentRegistry commentReg;
    
    Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
    
    @GET
    public Response findAll(@HeaderParam("Authorization") String authorization,
                            @DefaultValue("1000") @QueryParam("lat") long lat,
                            @DefaultValue("1000") @QueryParam("lon") long lon,
                            @DefaultValue("global") @QueryParam("type") String postType,
                            @QueryParam("from") int fromIndex,
                            @QueryParam("to") int toIndex) {

        User askingUser = apiKeyReg.validateApiKey(authorization);

        switch (postType) {
            case "global":
                ArrayList<Post> posts = new ArrayList<>();
                posts.add(new Post(askingUser, "This is a beautiful post"));
                posts.add(new Post(askingUser, "This is a beautiful post 1"));
                posts.add(new Post(askingUser, "This is a beautiful post 2"));
                posts.add(new Post(askingUser, "This is a beautiful post 3"));
                return Response.ok(gson.toJson(posts)).build();
            case "local":
                return Response.ok().build();
            case "following":
                return getFollowingPosts(askingUser);
            default:
                throw new WebApplicationException(400);
        }
    }

    private Response getFollowingPosts(User askingUser) {
        if (askingUser == null) {
            return Response.status(401).build();
        }
        return Response.ok(gson.toJson(postReg.getPostsFromUsersIFollow(askingUser)), MediaType.APPLICATION_JSON).build();
        
    }
    
    @POST
    public Response createPost(@HeaderParam("Authorization") String authorization, String contentBody) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
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
        res.contentLocation(URI.create(context.getContextPath() + "/api/posts/" + newPost.getId()));

        return res.build();
    }
    
    @PUT
    @Path("{id}")
    public Response updatePost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId, String contentBody) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
            //return "{\"error\": \"401, Not authorized\"}";
        }
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);
        String newContent = json.get("content").getAsString();
        
        Post p = postReg.find(postId);
        if(p.getAuthor().getUsername().equals(askingUser.getUsername())){
            // Same username on original postcreator and the user now trying to update it.
            p.setText(newContent);
            postReg.update(p);
            return Response.created(URI.create("/posts/" + postId)).build();
        }else{
            return Response.status(401).build();
        }
    }
    
    @DELETE
    @Path("{id}")
    public Response deletePost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        postReg.delete(postId);
        return Response.noContent().build();
    }
    
    @GET
    @Path("{id}")
    public String findPost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            //return Response.status(401).build();
            return "{\"error\": \"401, Not authorized\"}";
        }
        Post post = postReg.find(postId);
        return gson.toJson(post);
    }
    
    // COMMENTS
    
    @POST
    @Path("{id}/comments")
    public Response createCommentOnPost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId, String contentBody) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        Post post = postReg.find(postId);
        
        JsonObject json = gson.fromJson(contentBody, JsonObject.class);

        String newContent = json.get("text").getAsString();
        Comment comment = new Comment(askingUser, post, newContent);
        commentReg.create(comment);
        
        return Response.status(201).entity(gson.toJson(comment)).build();
    }
    
    @GET
    @Path("{id}/comments")
    public Response getCommentOnPost(@HeaderParam("Authorization") String authorization, @PathParam("id") long postId) {
        User askingUser = apiKeyReg.validateApiKey(authorization);
        if(askingUser == null){
            return Response.status(401).build();
        }
        
        Post post = postReg.find(postId);
        List<Comment> comments = commentReg.getCommentsOnPost(post);
        
        return Response.ok(gson.toJson(comments), MediaType.APPLICATION_JSON).build();
    }
    
}
