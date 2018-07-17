/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Filiere;
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
public class FiliereFacade extends AbstractFacade<Filiere> {

    @PersistenceContext(unitName = "cdocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public FiliereFacade() {
        super(Filiere.class);
    }
    public Filiere findByResponsable(Person responsable) {
        Filiere filiere;
        try {
            filiere = em.createNamedQuery("Choix.findByIdFiliere", Filiere.class).setParameter("idFiliere", responsable.getFiliereList().get(0).getIdFiliere()).getSingleResult();
            return filiere;
        } catch (NoResultException ex) {
            return null;
        }
        
    }
}
