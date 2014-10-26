/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.Comment;
import com.dat076hage.hage.model.Post;
import com.dat076hage.hage.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kimkling
 */
@Stateless
public class CommentRegistry extends AbstractDAO<Comment, Long>{

    @PersistenceContext(unitName="hage_pu")
    private EntityManager em;
    
    public CommentRegistry() {
        super(Comment.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
    
    public List<Comment> getCommentsOnPost(Post post){
        return em.createQuery("SELECT c FROM Comment c WHERE c.post = :postId", Comment.class).setParameter("postId", post).getResultList();
    }
    
    public List<Post> attachCommentsOnPosts(List<Post> posts){
        for(Post p : posts){
            p.getComments().clear();
            p.getComments().addAll(getCommentsOnPost(p));
        }
        return posts;
    }
}