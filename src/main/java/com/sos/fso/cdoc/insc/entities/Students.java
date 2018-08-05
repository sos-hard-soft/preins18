/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;
import javax.persistence.EntityResult;
import javax.persistence.FieldResult;
import javax.persistence.SqlResultSetMapping;

/**
 *
 * @author mabsalhi.sos
 */
@SqlResultSetMapping(name = "Students",
        entities = {
            @EntityResult(
                    entityClass = Etudiant.class,
                    fields = {
                        @FieldResult(name = "id", column = "id_etudiant"),
                        @FieldResult(name = "cin", column = "cin"),
                        @FieldResult(name = "cne", column = "cne"),
                        @FieldResult(name = "nom", column = "nom"),
                        @FieldResult(name = "nomAr", column = "nom_ar"),
                        @FieldResult(name = "prenom", column = "prenom"),
                        @FieldResult(name = "prenom_ar", column = "prenom_ar"),
                        @FieldResult(name = "date_naissance", column = "date_naissance")
                        }),
            @EntityResult(
                    entityClass = Qualification.class,
                    fields = {
                        @FieldResult(name = "diplome", column = "diplome"),
                        @FieldResult(name = "mention", column = "mention"),
                        @FieldResult(name = "note1", column = "note1"),
                        @FieldResult(name = "note2", column = "note2"),
                        @FieldResult(name = "note3", column = "note3"),
                        @FieldResult(name = "note4", column = "note4"),
                        @FieldResult(name = "note5", column = "note5"),
                        @FieldResult(name = "note6", column = "note6"),
                        @FieldResult(name = "partie_delivrante", column = "partie_delivrante")})})
public class Students implements Serializable{
    private String cin;
    private Integer cne;
    private String nom;
    private String prenom;
    private String nomAr;
    private String prenomAr;
    private Date dateNaissance;
    private String diplome;
    private long note1;
    private long note2;
    private long note3;
    private long note4;
    private long note5;
    private long note6;
    private String partiedelivrante;

    public Students(String cin, Integer cne, String nom, String prenom, String nomAr, String prenomAr, Date dateNaissance, String diplome, long note1, long note2, long note3, long note4, long note5, long note6, String partiedelivrante) {
        this.cin = cin;
        this.cne = cne;
        this.nom = nom;
        this.prenom = prenom;
        this.nomAr = nomAr;
        this.prenomAr = prenomAr;
        this.dateNaissance = dateNaissance;
        this.diplome = diplome;
        this.note1 = note1;
        this.note2 = note2;
        this.note3 = note3;
        this.note4 = note4;
        this.note5 = note5;
        this.note6 = note6;
        this.partiedelivrante = partiedelivrante;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Integer getCne() {
        return cne;
    }

    public void setCne(Integer cne) {
        this.cne = cne;
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

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getPrenomAr() {
        return prenomAr;
    }

    public void setPrenomAr(String prenomAr) {
        this.prenomAr = prenomAr;
    }

    public Date getDateNaissance() {
        return dateNaissance;
    }

    public void setDateNaissance(Date dateNaissance) {
        this.dateNaissance = dateNaissance;
    }

    public String getDiplome() {
        return diplome;
    }

    public void setDiplome(String diplome) {
        this.diplome = diplome;
    }

    public long getNote1() {
        return note1;
    }

    public void setNote1(long note1) {
        this.note1 = note1;
    }

    public long getNote2() {
        return note2;
    }

    public void setNote2(long note2) {
        this.note2 = note2;
    }

    public long getNote3() {
        return note3;
    }

    public void setNote3(long note3) {
        this.note3 = note3;
    }

    public long getNote4() {
        return note4;
    }

    public void setNote4(long note4) {
        this.note4 = note4;
    }

    public long getNote5() {
        return note5;
    }

    public void setNote5(long note5) {
        this.note5 = note5;
    }

    public long getNote6() {
        return note6;
    }

    public void setNote6(long note6) {
        this.note6 = note6;
    }

    public String getPartiedelivrante() {
        return partiedelivrante;
    }

    public void setPartiedelivrante(String partiedelivrante) {
        this.partiedelivrante = partiedelivrante;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.cin);
        hash = 29 * hash + Objects.hashCode(this.cne);
        hash = 29 * hash + Objects.hashCode(this.nom);
        hash = 29 * hash + Objects.hashCode(this.prenom);
        hash = 29 * hash + Objects.hashCode(this.nomAr);
        hash = 29 * hash + Objects.hashCode(this.prenomAr);
        hash = 29 * hash + Objects.hashCode(this.dateNaissance);
        hash = 29 * hash + Objects.hashCode(this.diplome);
        hash = 29 * hash + (int) (this.note1 ^ (this.note1 >>> 32));
        hash = 29 * hash + (int) (this.note2 ^ (this.note2 >>> 32));
        hash = 29 * hash + (int) (this.note3 ^ (this.note3 >>> 32));
        hash = 29 * hash + (int) (this.note4 ^ (this.note4 >>> 32));
        hash = 29 * hash + (int) (this.note5 ^ (this.note5 >>> 32));
        hash = 29 * hash + (int) (this.note6 ^ (this.note6 >>> 32));
        hash = 29 * hash + Objects.hashCode(this.partiedelivrante);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Students other = (Students) obj;
        if (this.note1 != other.note1) {
            return false;
        }
        if (this.note2 != other.note2) {
            return false;
        }
        if (this.note3 != other.note3) {
            return false;
        }
        if (this.note4 != other.note4) {
            return false;
        }
        if (this.note5 != other.note5) {
            return false;
        }
        if (this.note6 != other.note6) {
            return false;
        }
        if (!Objects.equals(this.cin, other.cin)) {
            return false;
        }
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.nomAr, other.nomAr)) {
            return false;
        }
        if (!Objects.equals(this.prenomAr, other.prenomAr)) {
            return false;
        }
        if (!Objects.equals(this.diplome, other.diplome)) {
            return false;
        }
        if (!Objects.equals(this.partiedelivrante, other.partiedelivrante)) {
            return false;
        }
        if (!Objects.equals(this.cne, other.cne)) {
            return false;
        }
        if (!Objects.equals(this.dateNaissance, other.dateNaissance)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Students : cin= " + cin + " " + nom + ", " + prenom + ", diplome = " + diplome;
    }
    
    
    
    
}
