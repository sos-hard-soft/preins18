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
@Table(name = "t_laboratoire")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Laboratoire.findAll", query = "SELECT l FROM Laboratoire l")
    , @NamedQuery(name = "Laboratoire.findByIdLaboratoire", query = "SELECT l FROM Laboratoire l WHERE l.idLaboratoire = :idLaboratoire")
    , @NamedQuery(name = "Laboratoire.findByDescription", query = "SELECT l FROM Laboratoire l WHERE l.description = :description")
    , @NamedQuery(name = "Laboratoire.findByIntitule", query = "SELECT l FROM Laboratoire l WHERE l.intitule = :intitule")
    , @NamedQuery(name = "Laboratoire.findByDirecteur", query = "SELECT l FROM Laboratoire l WHERE l.directeur = :directeur")})
public class Laboratoire implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_laboratoire")
    private Integer idLaboratoire;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Size(max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "intitule_ar")
    private String intituleAR;
    @Size(max = 255)
    @Column(name = "description")
    private String description;
    @OneToMany(mappedBy = "laboratoire")
    private List<Sujet> sujetList;
    @OneToMany(mappedBy = "laboratoire")
    private List<Person> membersList;
    @JoinColumn(name = "directeur", referencedColumnName = "id_person")
    @ManyToOne
    private Person directeur;
    
    public Laboratoire() {
    }

    public Laboratoire(Integer idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }

    public Integer getIdLaboratoire() {
        return idLaboratoire;
    }

    public void setIdLaboratoire(Integer idLaboratoire) {
        this.idLaboratoire = idLaboratoire;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    @XmlTransient
    public List<Sujet> getSujetList() {
        return sujetList;
    }

    public void setSujetList(List<Sujet> sujetList) {
        this.sujetList = sujetList;
    }

    public Person getDirecteur() {
        return directeur;
    }

    public void setDirecteur(Person directeur) {
        this.directeur = directeur;
    }

    public String getIntituleAR() {
        return intituleAR;
    }

    public void setIntituleAR(String intituleAR) {
        this.intituleAR = intituleAR;
    }

   

    public List<Person> getMembersList() {
        return membersList;
    }

    public void setMembersList(List<Person> membersList) {
        this.membersList = membersList;
    }

    
    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idLaboratoire != null ? idLaboratoire.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Laboratoire)) {
            return false;
        }
        Laboratoire other = (Laboratoire) object;
        if ((this.idLaboratoire == null && other.idLaboratoire != null) || (this.idLaboratoire != null && !this.idLaboratoire.equals(other.idLaboratoire))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Laboratoire[ idLaboratoire=" + idLaboratoire + " ]";
    }
    
}
