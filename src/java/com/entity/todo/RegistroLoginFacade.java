/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.entity.todo;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author jbenitez
 */
@Stateless
public class RegistroLoginFacade extends AbstractFacade<RegistroLogin>{
    
    @PersistenceContext(unitName = "RestaurantOfTheFuture3PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public RegistroLoginFacade() {
        super(RegistroLogin.class);
    }
    
}
