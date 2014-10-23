/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.dat076hage.hage;

import com.dat076hage.hage.auth.ApiKey;
import com.dat076hage.hage.persistence.AbstractDAO;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author kim
 */
@Stateless
public class ApiKeyRegistry extends AbstractDAO<ApiKey, String>{

    @PersistenceContext(unitName="hage_pu")
    private EntityManager em;
    
    public ApiKeyRegistry() {
        super(ApiKey.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
    // Below is all special methods for Posts, see AbstractDAO for the rest
}
