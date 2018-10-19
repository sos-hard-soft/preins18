/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mabsalhi.sos
 */
@Entity
@Table(name = "t_sujet")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sujet.findAll", query = "SELECT s FROM Sujet s")
    , @NamedQuery(name = "Sujet.findByIdSujet", query = "SELECT s FROM Sujet s WHERE s.idSujet = :idSujet")
    , @NamedQuery(name = "Sujet.findByDescription", query = "SELECT s FROM Sujet s WHERE s.description = :description")
    , @NamedQuery(name = "Sujet.findByEncadrant", query = "SELECT s FROM Sujet s WHERE s.responsable = :responsable")
    , @NamedQuery(name = "Sujet.findByIntitule", query = "SELECT s FROM Sujet s WHERE s.intitule = :intitule")
    , @NamedQuery(name = "Sujet.findByLabo", query = "SELECT s FROM Sujet s WHERE s.idLaboratoire = :idLaboratoire")
    , @NamedQuery(name = "Sujet.findByNbPlace", query = "SELECT s FROM Sujet s WHERE s.nbPlace = :nbPlace")
    , @NamedQuery(name = "Sujet.findByOptimisticLock", query = "SELECT s FROM Sujet s WHERE s.optimisticLock = :optimisticLock")})
public class Sujet implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_sujet")
    private Integer idSujet;
    @Size(max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "description")
    private String description;    
    @JoinColumn(name = "person", referencedColumnName = "id_person")
    @ManyToOne
    private Person responsable;
    @Column(name = "nb_place")
    private Integer nbPlace;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @JoinColumn(name = "laboratoire", referencedColumnName = "choixList")
    @ManyToOne
    private Laboratoire idLaboratoire;
    @OneToMany(mappedBy = "idSujet")
    private List<Choix> choixList;

    
    public Sujet() {
    }

    public Integer getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(Integer idSujet) {
        this.idSujet = idSujet;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Person getResponsable() {
        return responsable;
    }

    public void setResponsable(Person responsable) {
        this.responsable = responsable;
    }

    public Integer getNbPlace() {
        return nbPlace;
    }

    public void setNbPlace(Integer nbPlace) {
        this.nbPlace = nbPlace;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public Laboratoire getIdLaboratoire() {
        return idLaboratoire;
    }

    public void setIdLaboratoire(Laboratoire idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }

    public List<Choix> getChoixList() {
        return choixList;
    }

    public void setChoixList(List<Choix> choixList) {
        this.choixList = choixList;
    }

    
   

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Sujet[ idSujet=" + idSujet + " ]";
    }
    
}
