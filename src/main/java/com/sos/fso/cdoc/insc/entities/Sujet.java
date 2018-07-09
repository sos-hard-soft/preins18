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
    , @NamedQuery(name = "Sujet.findByEncadrant", query = "SELECT s FROM Sujet s WHERE s.encadrant = :encadrant")
    , @NamedQuery(name = "Sujet.findByEtablissement", query = "SELECT s FROM Sujet s WHERE s.etablissement = :etablissement")
    , @NamedQuery(name = "Sujet.findByIntitule", query = "SELECT s FROM Sujet s WHERE s.intitule = :intitule")
    , @NamedQuery(name = "Sujet.findByLabo", query = "SELECT s FROM Sujet s WHERE s.labo = :labo")
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
    @Column(name = "description")
    private String description;
    @Size(max = 255)
    @Column(name = "encadrant")
    private String encadrant;
    @Size(max = 255)
    @Column(name = "etablissement")
    private String etablissement;
    @Size(max = 255)
    @Column(name = "intitule")
    private String intitule;
    @Size(max = 255)
    @Column(name = "labo")
    private String labo;
    @Column(name = "nb_place")
    private Integer nbPlace;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @JoinColumn(name = "branche", referencedColumnName = "id_branche")
    @ManyToOne
    private Branche branche;
    @OneToMany(mappedBy = "idSujet")
    private List<Choix> choixList;

    public Sujet() {
    }

    public Sujet(Integer idSujet) {
        this.idSujet = idSujet;
    }

    public Integer getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(Integer idSujet) {
        this.idSujet = idSujet;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getEncadrant() {
        return encadrant;
    }

    public void setEncadrant(String encadrant) {
        this.encadrant = encadrant;
    }

    public String getEtablissement() {
        return etablissement;
    }

    public void setEtablissement(String etablissement) {
        this.etablissement = etablissement;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }

    public String getLabo() {
        return labo;
    }

    public void setLabo(String labo) {
        this.labo = labo;
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

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    @XmlTransient
    public List<Choix> getChoixList() {
        return choixList;
    }

    public void setChoixList(List<Choix> choixList) {
        this.choixList = choixList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idSujet != null ? idSujet.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sujet)) {
            return false;
        }
        Sujet other = (Sujet) object;
        if ((this.idSujet == null && other.idSujet != null) || (this.idSujet != null && !this.idSujet.equals(other.idSujet))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Sujet[ idSujet=" + idSujet + " ]";
    }
    
}
