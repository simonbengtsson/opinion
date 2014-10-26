/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.model.User;
import com.dat076hage.hage.persistence.AbstractDAO;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kim
 */
@Stateless
public class PostRegistry extends AbstractDAO<Post, Long>{

    @PersistenceContext(unitName="hage_pu")
    private EntityManager em;
    
    public PostRegistry() {
        super(Post.class);
    }
    
    @Override
    public EntityManager getEntityManager() {
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
    
    
    public List<Post> getPostsFromUsersIFollow(User user){
        List<Post> posts = new ArrayList<>();
        for (User u : user.getFollowing()) {
            posts.addAll(em.createQuery("SELECT p FROM Post p WHERE p.user = :user", Post.class).setParameter("user", u).getResultList());
        }
        return posts;
                
    }
    
}
