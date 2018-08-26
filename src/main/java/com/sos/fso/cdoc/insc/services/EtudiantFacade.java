/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.services;

import com.sos.fso.cdoc.insc.entities.Etudiant;
import com.sos.fso.cdoc.insc.helpers.StdList;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
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
    
    public List<Object[]> getPreinscrit(String intitule){
        System.out.println("Procedure recuperation list  estudiant filiere responsable");
        List<Object[]> preinscrit;
        //List<Students> myList;
        try {
    //            preinscrit = em.createNativeQuery("SELECT e.cin, e.nom, f.intitule as IntituleFiliere "
    //                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c ON e.id_etudiant = c.id_etudiant "
    //                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere Where f.intitule = '" + intitule + "';").getResultList();
    //            System.out.println("Works fine!!!!!");  
            
            
            preinscrit = em.createNativeQuery("SELECT e.cin, e.nom, e.prenom, q.note1, q.note2, q.diplome, f.intitule AS IntituleFiliere "
                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c "
                    + "ON e.id_etudiant = c.id_etudiant "
                    + "INNER JOIN t_qualification AS q ON e.id_etudiant = q.etudiant "
                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere "
                    + "Where f.intitule = '" + intitule + "' and (q.diplome = 'Licence fondamentale' or q.diplome = 'Licence professionnelle');").getResultList();
            System.out.println("Works fine!!!!!");
            System.out.println(preinscrit.get(0).toString());
        } catch (NoResultException ex) {
            throw new NoResultException(ex.getMessage());            
        }
        return preinscrit;
    }
    
    public List<StdList> getStudents(String intitule){
        System.out.println("Procedure recuperation list  estudiant filiere responsable");
        List<StdList> preinscrit = new ArrayList<>();
        //List<Students> myList;
        try {
    //            preinscrit = em.createNativeQuery("SELECT e.cin, e.nom, f.intitule as IntituleFiliere "
    //                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c ON e.id_etudiant = c.id_etudiant "
    //                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere Where f.intitule = '" + intitule + "';").getResultList();
    //
    //            System.out.println("Works fine!!!!!");  
            
            
            preinscrit = em.createNativeQuery("SELECT e.cne, e.cin, e.nom, e.prenom, e.date_naissance, e.lieu_naissance, e.sexe, e.nationalite, q.diplome, q.date_obtention, q.date_premiere_inscription, q.mention, q.note1, q.note2, q.note3, q.note4, q.note5, q.note6, "
                    + "(q.note1+q.note2+q.note3+q.note4+q.note5+q.note6)/6 as moyenne "
                    + "FROM  t_etudiant AS e INNER JOIN t_choix AS c "
                    + "ON e.id_etudiant = c.id_etudiant "
                    + "INNER JOIN t_qualification AS q ON e.id_etudiant = q.etudiant "
                    + "INNER JOIN t_filiere AS f ON c.id_filiere = f.id_filiere "
                    + "Where f.intitule = '" + intitule + "' and (q.diplome = 'Licence fondamentale' or q.diplome = 'Licence professionnelle');", StdList.class).getResultList();
            System.out.println("Works fine!!!!!");
            System.out.println(preinscrit.get(0).toString());
        } catch (NoResultException ex) {
            throw new NoResultException(ex.getMessage());            
        }
        return preinscrit;
    }

}
