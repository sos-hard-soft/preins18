/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mabsalhi.sos
 */
@Stateless
public class PersonFacade extends AbstractFacade<Person> {

    @PersistenceContext(unitName = "insPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }
    

    public PersonFacade() {
        super(Person.class);
    }
    
    public Person findByCin(String cin) {
        Person personne;
        try {
            personne = em.createNamedQuery("Person.findByCin", Person.class).setParameter("cin", cin).getSingleResult();
            return personne;
        } catch (NoResultException ex) {
            return null;
        }
        
    }
}
