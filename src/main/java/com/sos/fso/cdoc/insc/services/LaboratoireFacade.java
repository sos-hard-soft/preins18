/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Laboratoire;
import com.sos.fso.cdoc.insc.entities.Person;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author master05
 */
@Stateless
public class LaboratoireFacade extends AbstractFacade<Laboratoire> {

    @PersistenceContext(unitName = "insPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LaboratoireFacade() {
        super(Laboratoire.class);
    }

    public List<Laboratoire> findLaboByDirecteur(Person directeur) {
        return em.createNamedQuery("Laboratoire.findByDirecteur", Laboratoire.class).setParameter("directeur", directeur).getResultList();

    }
    
    
}
