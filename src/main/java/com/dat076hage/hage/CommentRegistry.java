/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.Comment;
import com.dat076hage.hage.persistence.AbstractDAO;
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
        System.out.print(em.toString());
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
}