/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.model.User;
import com.dat076hage.hage.persistence.AbstractDAO;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kim
 */
@Stateless
public class UserRegistry extends AbstractDAO<User, String> {
    
    private final int FEATURED_COUNT = 4;
    
    @PersistenceContext(unitName="hage_pu")
    private EntityManager em;
            
    public UserRegistry(){
        super(User.class);
    }
    
    public List getFeaturedUsers() {
        return em.createQuery("select m from User m").setMaxResults(FEATURED_COUNT).getResultList();
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
}
