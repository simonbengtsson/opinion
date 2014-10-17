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
public class HateRegistry extends AbstractDAO<Hate, String>{

    @PersistenceContext
    private EntityManager em;
    
    public HateRegistry() {
        super(Hate.class);
    }
    
    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    
}