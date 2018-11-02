/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.helpers;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mabsalhi.sos
 */
@Entity
@XmlRootElement
public class StdList implements Serializable{
    private static final long serialVersionUID = 1L;
    @Id
    @Column(name = "cne")
    private long cne;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    @Size(max = 255)
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    @Column(name = "sexe")
    private Boolean sexe;
    @Size(max = 255)
    @Column(name = "nationalite")
    private String nationalite;
    @Size(max = 255)
    @Column(name = "diplome")
    private String diplome;
    @Size(max = 255)
    @Column(name = "partie_delivrante")
    private String partieDelivrante;
    @Column(name = "date_obtention")
    @Temporal(TemporalType.DATE)
    private Date dateObtention;
    
    @Column(name = "date_premiere_inscription")
    @Temporal(TemporalType.DATE)
    private Date datePremiereInscription;
    @Size(max = 255)
    @Column(name = "mention")
    private String mention;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
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
    @Column(name = "moyenne")
    private Double moyenne;

    public StdList() {
    }

    public StdList(long cne, String cin, String nom, String prenom, Date dateNaissance, String lieuNaissance, Boolean sexe, String nationalite, String diplome, String partieDelivrante, Date dateObtention, Date datePremiereInscription, String mention, Double note1, Double note2, Double note3, Double note4, Double note5, Double note6, Double moyenne) {
        this.cne = cne;
        this.cin = cin;
        this.nom = nom;
        this.prenom = prenom;
        this.dateNaissance = dateNaissance;
        this.lieuNaissance = lieuNaissance;
        this.sexe = sexe;
        this.nationalite = nationalite;
        this.diplome = diplome;
        this.partieDelivrante = partieDelivrante;
        this.dateObtention = dateObtention;
        this.datePremiereInscription = datePremiereInscription;
        this.mention = mention;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.note4 = note4;
        this.note5 = note5;
        this.note6 = note6;
        this.moyenne = moyenne;
    }

    public long getCne() {
        return cne;
    }

    public void setCne(long cne) {
        this.cne = cne;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public String getPartieDelivrante() {
        return partieDelivrante;
    }

    public void setPartieDelivrante(String partieDelivrante) {
        this.partieDelivrante = partieDelivrante;
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

    public String getMention() {
        return mention;
    }

    public void setMention(String mention) {
        this.mention = mention;
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

    public Double getMoyenne() {
        return moyenne;
    }

    public void setMoyenne(Double moyenne) {
        this.moyenne = moyenne;
    }

    @Override
    public String toString() {
        return "StdList{" + "nom=" + nom + ", prenom=" + prenom + '}';
    }

    
    
    
}
