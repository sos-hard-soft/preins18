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

/**
 *
 * @author master05
 */
@Entity
@Table(name = "t_laboratoire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratoire.findAll", query = "SELECT f FROM Laboratoire f")
    , @NamedQuery(name = "Laboratoire.findByIdLaboratoire", query = "SELECT f FROM Laboratoire f WHERE f.idLaboratoire = :idLaboratoire")
    , @NamedQuery(name = "Laboratoire.findByDescription", query = "SELECT f FROM Laboratoire f WHERE f.description = :description")
    , @NamedQuery(name = "Laboratoire.findByIntitule", query = "SELECT f FROM Laboratoire f WHERE f.intitule = :intitule")
    , @NamedQuery(name = "Laboratoire.findByOptimisticLock", query = "SELECT f FROM Laboratoire f WHERE f.optimisticLock = :optimisticLock")})

public class Laboratoire implements Serializable {

     private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_laboratoire")
    private Integer idLaboratoire;    
    @Size(max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @OneToMany(mappedBy = "idLaboratoire")
    private List<Sujet> choixList;
    @OneToMany(mappedBy = "id_person")
    private List<Person> membresList;
    @JoinColumn(name = "person", referencedColumnName = "id_person")
    @ManyToOne
    private Person responsable;

    public Laboratoire(Integer idLaboratoire, String intitule, String description, Integer optimisticLock, List<Sujet> choixList, List<Person> membresList, Person responsable) {
        this.idLaboratoire = idLaboratoire;
        this.intitule = intitule;
        this.description = description;
        this.optimisticLock = optimisticLock;
        this.choixList = choixList;
        this.membresList = membresList;
        this.responsable = responsable;
    }

    public Laboratoire() {
    }

    public Integer getIdLaboratoire() {
        return idLaboratoire;
    }

    public void setIdLaboratoire(Integer idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
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

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public List<Sujet> getChoixList() {
        return choixList;
    }

    public void setChoixList(List<Sujet> choixList) {
        this.choixList = choixList;
    }

    public List<Person> getMembresList() {
        return membresList;
    }

    public void setMembresList(List<Person> membresList) {
        this.membresList = membresList;
    }

    public Person getResponsable() {
        return responsable;
    }

    public void setResponsable(Person responsable) {
        this.responsable = responsable;
    }

    @Override
    public String toString() {
        return "Laboratoire{" + "intitule=" + intitule + '}';
    }
    
    
    
}
