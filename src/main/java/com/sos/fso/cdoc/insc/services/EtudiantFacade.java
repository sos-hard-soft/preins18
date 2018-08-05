/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.entities.Students;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;

/**
 *
 * @author mab.salhi
 */
@Stateless
public class EtudiantFacade extends AbstractFacade<Etudiant> {
    @PersistenceContext(unitName = "cdocPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtudiantFacade() {
        super(Etudiant.class);
    }

    public Etudiant findByCne(long cne) {
        Etudiant etudiant;
        try {
            etudiant = em.createNamedQuery("Etudiant.findByCne", Etudiant.class).setParameter("cne", cne).getSingleResult();
            return etudiant;
        } catch (NoResultException ex) {
            return null;
        }
        
    }
    
    public Etudiant findByCin(String cin) {
        Etudiant etudiant;
        try {
            etudiant = em.createNamedQuery("Etudiant.findByCin", Etudiant.class).setParameter("cin", cin).getSingleResult();
            return etudiant;
        } catch (NoResultException ex) {
            return null;
        }
        
    }
    
    public List<Students> getPreinscrit(String intitule){
        System.out.println("Procedure recuperation list  estudiant filiere responsable");
        //List<Object[]> preinscrit;
        List<Students> myList;
        try {
    //            preinscrit = em.createNativeQuery("SELECT e.cin, e.nom, f.intitule as IntituleFiliere "
    //                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c ON e.id_etudiant = c.id_etudiant "
    //                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere Where f.intitule = '" + intitule + "';").getResultList();
    //            System.out.println("Works fine!!!!!");  
            
            
            myList = em.createNativeQuery("SELECT e.cin, e.nom, e.prenom, q.note1, q.note2, q.diplome, f.intitule AS IntituleFiliere "
                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c "
                    + "ON e.id_etudiant = c.id_etudiant "
                    + "INNER JOIN t_qualification AS q ON e.id_etudiant = q.etudiant "
                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere "
                    + "Where f.intitule = '" + intitule + "' and (q.diplome = 'Licence fondamentale' or q.diplome = 'Licence professionnelle');", Students.class).getResultList();
            System.out.println("Works fine!!!!!");
            System.out.println(myList.get(0).getCin());
        } catch (NoResultException ex) {
            throw new NoResultException(ex.getMessage());            
        }
        return myList;
    }
    
    
}
