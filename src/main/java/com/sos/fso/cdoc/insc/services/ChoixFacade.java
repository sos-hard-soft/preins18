/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Choix;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Sujet;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class ChoixFacade extends AbstractFacade<Choix> {
    @PersistenceContext(unitName = "insPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChoixFacade() {
        super(Choix.class);
    }
    
    public List<Choix> findByIdEtudiant(Etudiant current){
        return em.createNamedQuery("Choix.findByIdEtudiant").setParameter("idEtudiant", current).getResultList();        
    }
    
    public List<Choix> findByIdSujet(Sujet selected){
        return em.createNamedQuery("Choix.findBySujet").setParameter("idSujet", selected).getResultList();        
    }
    
}
