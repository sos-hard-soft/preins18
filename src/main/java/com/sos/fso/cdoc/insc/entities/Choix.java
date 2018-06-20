/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mab.salhi
 */
@Entity
@Table(name = "t_choix")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Choix.findAll", query = "SELECT c FROM Choix c"),
    @NamedQuery(name = "Choix.findByIdChoix", query = "SELECT c FROM Choix c WHERE c.idChoix = :idChoix"),
    @NamedQuery(name = "Choix.findByOptimisticLock", query = "SELECT c FROM Choix c WHERE c.optimisticLock = :optimisticLock"),
    @NamedQuery(name = "Choix.findByIdEtudiant", query = "SELECT c FROM Choix c WHERE c.idEtudiant = :idEtudiant")
})
public class Choix implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_choix")
    private Integer idChoix;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @JoinColumn(name = "id_sujet", referencedColumnName = "id_sujet")
    @ManyToOne(optional = false)
    private Sujet idSujet;
    @JoinColumn(name = "id_etudiant", referencedColumnName = "id_etudiant")
    @ManyToOne(optional = false)
    private Etudiant idEtudiant;

    public Choix() {
    }

    public Choix(Integer idChoix) {
        this.idChoix = idChoix;
    }

    public Integer getIdChoix() {
        return idChoix;
    }

    public void setIdChoix(Integer idChoix) {
        this.idChoix = idChoix;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public Sujet getIdSujet() {
        return idSujet;
    }

    public void setIdSujet(Sujet idSujet) {
        this.idSujet = idSujet;
    }

    public Etudiant getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Etudiant idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idChoix != null ? idChoix.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Choix)) {
            return false;
        }
        Choix other = (Choix) object;
        if ((this.idChoix == null && other.idChoix != null) || (this.idChoix != null && !this.idChoix.equals(other.idChoix))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Choix[ idChoix=" + idChoix + " ]";
    }
    
}
