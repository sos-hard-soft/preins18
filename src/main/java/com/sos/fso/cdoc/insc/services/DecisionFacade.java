/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Decision;
import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Person;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.NonUniqueResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author master05
 */
@Stateless
public class DecisionFacade extends AbstractFacade<Decision> {

    @PersistenceContext(unitName = "insPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DecisionFacade() {
        super(Decision.class);
    }

    public void deleteDecision(Etudiant student, Person decideur) {
         Query query = em.createQuery(
      "DELETE FROM Decision d WHERE d.decideur = :pd and d.selectionne = :ps");
  int deletedCount = query.setParameter("pd", decideur).setParameter("ps", student).executeUpdate();
        System.out.println("le nombre de decision suprimé est " + deletedCount);
    }
    
    public Decision findByEtudiant(Etudiant traite, Person decideur){
        Decision decision;
        try {
            decision = em.createNamedQuery("Decision.findBySelectionne", Decision.class)
                    .setParameter("selectionne", traite).setParameter("decideur", decideur).getSingleResult();
        return decision;
        } catch (NoResultException ex) {
            ex.printStackTrace();
            return null;
        } catch (NonUniqueResultException e) {
            e.printStackTrace();
            return null;
        }       
    }
}
