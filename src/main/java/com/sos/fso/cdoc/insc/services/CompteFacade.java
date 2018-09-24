/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Compte;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class CompteFacade extends AbstractFacade<Compte> {
    @PersistenceContext(unitName = "insPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CompteFacade() {
        super(Compte.class);
    }
    
     public Compte findByCin(String cin){
        return em.createNamedQuery("Compte.findByCin", Compte.class).setParameter("cin", cin).getSingleResult();
    }
    
    
}
