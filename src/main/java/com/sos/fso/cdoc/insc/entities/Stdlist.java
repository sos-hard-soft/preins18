/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mabsalhi.sos
 */
@Entity
@Table(name = "stdlist")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Stdlist.findAll", query = "SELECT s FROM Stdlist s")
    , @NamedQuery(name = "Stdlist.findByCne", query = "SELECT s FROM Stdlist s WHERE s.cne = :cne")
    , @NamedQuery(name = "Stdlist.findByCin", query = "SELECT s FROM Stdlist s WHERE s.cin = :cin")
    , @NamedQuery(name = "Stdlist.findByDateNaissance", query = "SELECT s FROM Stdlist s WHERE s.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Stdlist.findByDateObtention", query = "SELECT s FROM Stdlist s WHERE s.dateObtention = :dateObtention")
    , @NamedQuery(name = "Stdlist.findByDatePremiereInscription", query = "SELECT s FROM Stdlist s WHERE s.datePremiereInscription = :datePremiereInscription")
    , @NamedQuery(name = "Stdlist.findByDiplome", query = "SELECT s FROM Stdlist s WHERE s.diplome = :diplome")
    , @NamedQuery(name = "Stdlist.findByLieuNaissance", query = "SELECT s FROM Stdlist s WHERE s.lieuNaissance = :lieuNaissance")
    , @NamedQuery(name = "Stdlist.findByMention", query = "SELECT s FROM Stdlist s WHERE s.mention = :mention")
    , @NamedQuery(name = "Stdlist.findByMoyenne", query = "SELECT s FROM Stdlist s WHERE s.moyenne = :moyenne")
    , @NamedQuery(name = "Stdlist.findByNationalite", query = "SELECT s FROM Stdlist s WHERE s.nationalite = :nationalite")
    , @NamedQuery(name = "Stdlist.findByNom", query = "SELECT s FROM Stdlist s WHERE s.nom = :nom")
    , @NamedQuery(name = "Stdlist.findByNote1", query = "SELECT s FROM Stdlist s WHERE s.note1 = :note1")
    , @NamedQuery(name = "Stdlist.findByNote2", query = "SELECT s FROM Stdlist s WHERE s.note2 = :note2")
    , @NamedQuery(name = "Stdlist.findByNote3", query = "SELECT s FROM Stdlist s WHERE s.note3 = :note3")
    , @NamedQuery(name = "Stdlist.findByNote4", query = "SELECT s FROM Stdlist s WHERE s.note4 = :note4")
    , @NamedQuery(name = "Stdlist.findByNote5", query = "SELECT s FROM Stdlist s WHERE s.note5 = :note5")
    , @NamedQuery(name = "Stdlist.findByNote6", query = "SELECT s FROM Stdlist s WHERE s.note6 = :note6")
    , @NamedQuery(name = "Stdlist.findByPrenom", query = "SELECT s FROM Stdlist s WHERE s.prenom = :prenom")
    , @NamedQuery(name = "Stdlist.findBySexe", query = "SELECT s FROM Stdlist s WHERE s.sexe = :sexe")})
public class Stdlist implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "cne")
    private Long cne;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Column(name = "date_obtention")
    @Temporal(TemporalType.DATE)
    private Date dateObtention;
    @Column(name = "date_premiere_inscription")
    @Temporal(TemporalType.DATE)
    private Date datePremiereInscription;
    @Size(max = 255)
    @Column(name = "diplome")
    private String diplome;
    @Size(max = 255)
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    @Size(max = 255)
    @Column(name = "mention")
    private String mention;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "moyenne")
    private Double moyenne;
    @Size(max = 255)
    @Column(name = "nationalite")
    private String nationalite;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Column(name = "note1")
    private Double note1;
    @Column(name = "note2")
    private Double note2;
    @Column(name = "note3")
    private Double note3;
    @Column(name = "note4")
    private Double note4;
    @Column(name = "note5")
    private Double note5;
    @Column(name = "note6")
    private Double note6;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "sexe")
    private Boolean sexe;

    public Stdlist() {
    }

    public Stdlist(Long cne) {
        this.cne = cne;
    }

    public Long getCne() {
        return cne;
    }

    public void setCne(Long cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Date getDatePremiereInscription() {
        return datePremiereInscription;
    }

    public void setDatePremiereInscription(Date datePremiereInscription) {
        this.datePremiereInscription = datePremiereInscription;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
    }

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Double getNote1() {
        return note1;
    }

    public void setNote1(Double note1) {
        this.note1 = note1;
    }

    public Double getNote2() {
        return note2;
    }

    public void setNote2(Double note2) {
        this.note2 = note2;
    }

    public Double getNote3() {
        return note3;
    }

    public void setNote3(Double note3) {
        this.note3 = note3;
    }

    public Double getNote4() {
        return note4;
    }

    public void setNote4(Double note4) {
        this.note4 = note4;
    }

    public Double getNote5() {
        return note5;
    }

    public void setNote5(Double note5) {
        this.note5 = note5;
    }

    public Double getNote6() {
        return note6;
    }

    public void setNote6(Double note6) {
        this.note6 = note6;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cne != null ? cne.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Stdlist)) {
            return false;
        }
        Stdlist other = (Stdlist) object;
        if ((this.cne == null && other.cne != null) || (this.cne != null && !this.cne.equals(other.cne))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Stdlist[ cne=" + cne + " ]";
    }
    
}
