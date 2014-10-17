/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.persistence.AbstractDAO;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kim
 */
public class UserRegistry extends AbstractDAO<User, String> {
    @PersistenceContext
    private EntityManager em;
            
    public UserRegistry(){
        super(User.class);
    }

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
}
