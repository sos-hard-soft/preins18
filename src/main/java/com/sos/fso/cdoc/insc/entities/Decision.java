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
@Table(name = "t_decision")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Decision.findAll", query = "SELECT d FROM Decision d")
    , @NamedQuery(name = "Decision.findByIdDecision", query = "SELECT d FROM Decision d WHERE d.idDecision = :idDecision")
    , @NamedQuery(name = "Decision.findByDeci", query = "SELECT d FROM Decision d WHERE d.deci = :deci")
    , @NamedQuery(name = "Decision.findByDecideur", query = "SELECT d FROM Decision d WHERE d.decideur = :decideur")
    , @NamedQuery(name = "Decision.findBySelectionne", query = "SELECT d FROM Decision d WHERE d.selectionne = :selectionne and d.decideur = :decideur")})
public class Decision implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_decision")
    private Integer idDecision;
    @Column(name = "deci")
    private boolean deci;
    @JoinColumn(name = "decideur", referencedColumnName = "id_Person")
    @ManyToOne
    private Person decideur;
    @JoinColumn(name = "selectionne", referencedColumnName = "id_Etudiant")
    @ManyToOne
    private Etudiant selectionne;
    //@OneToMany(mappedBy = "idDecision")
    //private List<Choix> choixList;

    public Decision() {
    }

    public Decision(Integer idDecision) {
        this.idDecision = idDecision;
    }

    public Integer getIdDecision() {
        return idDecision;
    }

    public void setIdDecision(Integer idDecision) {
        this.idDecision = idDecision;
    }

    public boolean getDeci() {
        return deci;
    }

    public void setDeci(boolean deci) {
        this.deci = deci;
    }

    public Person getDecideur() {
        return decideur;
    }

    public void setDecideur(Person decideur) {
        this.decideur = decideur;
    }

    public Etudiant getSelectionne() {
        return selectionne;
    }

    public void setSelectionne(Etudiant selectionne) {
        this.selectionne = selectionne;
    }

    
    

//    @XmlTransient
//    public List<Choix> getChoixList() {
//        return choixList;
//    }
//
//    public void setChoixList(List<Choix> choixList) {
//        this.choixList = choixList;
//    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDecision != null ? idDecision.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Decision)) {
            return false;
        }
        Decision other = (Decision) object;
        if ((this.idDecision == null && other.idDecision != null) || (this.idDecision != null && !this.idDecision.equals(other.idDecision))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        String decisionAff;
        if(deci == false){
            decisionAff = "Rejeter";
        }else{
            decisionAff = "Accepter";
        }
        return "" + decisionAff + "";
    }
    
}
