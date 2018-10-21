/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sos.fso.cdoc.insc.entities;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mabsalhi.sos
 */
@Entity
@Table(name = "t_person")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Person.findAll", query = "SELECT p FROM Person p")
    , @NamedQuery(name = "Person.findByIdPerson", query = "SELECT p FROM Person p WHERE p.idPerson = :idPerson")
    , @NamedQuery(name = "Person.findByCin", query = "SELECT p FROM Person p WHERE p.cin = :cin")
    , @NamedQuery(name = "Person.findByNom", query = "SELECT p FROM Person p WHERE p.nom = :nom")
    , @NamedQuery(name = "Person.findByNomAr", query = "SELECT p FROM Person p WHERE p.nomAr = :nomAr")
    , @NamedQuery(name = "Person.findByPrenom", query = "SELECT p FROM Person p WHERE p.prenom = :prenom")
    , @NamedQuery(name = "Person.findByPrenomAr", query = "SELECT p FROM Person p WHERE p.prenomAr = :prenomAr")
    , @NamedQuery(name = "Person.findByDateNaissance", query = "SELECT p FROM Person p WHERE p.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Person.findByEmail", query = "SELECT p FROM Person p WHERE p.email = :email")
    , @NamedQuery(name = "Person.findByAdresse", query = "SELECT p FROM Person p WHERE p.adresse = :adresse")
    , @NamedQuery(name = "Person.findByEtatMatrimonial", query = "SELECT p FROM Person p WHERE p.etatMatrimonial = :etatMatrimonial")
    , @NamedQuery(name = "Person.findByFonction", query = "SELECT p FROM Person p WHERE p.fonction = :fonction")
    , @NamedQuery(name = "Person.findByLieuNaissance", query = "SELECT p FROM Person p WHERE p.lieuNaissance = :lieuNaissance")
    , @NamedQuery(name = "Person.findByLieuNaissanceAr", query = "SELECT p FROM Person p WHERE p.lieuNaissanceAr = :lieuNaissanceAr")
    , @NamedQuery(name = "Person.findByNationalite", query = "SELECT p FROM Person p WHERE p.nationalite = :nationalite")
    , @NamedQuery(name = "Person.findByNumeroTelephonne", query = "SELECT p FROM Person p WHERE p.numeroTelephonne = :numeroTelephonne")
    , @NamedQuery(name = "Person.findByOptimisticLock", query = "SELECT p FROM Person p WHERE p.optimisticLock = :optimisticLock")
    , @NamedQuery(name = "Person.findBySexe", query = "SELECT p FROM Person p WHERE p.sexe = :sexe")})
public class Person implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_person")
    private Integer idPerson;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "nom_ar")
    private String nomAr;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "prenom_ar")
    private String prenomAr;
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Column(name = "etat_matrimonial")
    private Boolean etatMatrimonial;
    @Size(max = 255)
    @Column(name = "fonction")
    private String fonction;
    @Size(max = 255)
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    @Size(max = 255)
    @Column(name = "lieu_naissance_ar")
    private String lieuNaissanceAr;
    @Size(max = 255)
    @Column(name = "nationalite")
    private String nationalite;
    @Column(name = "numero_telephonne")
    private BigInteger numeroTelephonne;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Column(name = "sexe")
    private Boolean sexe;
    @OneToMany(mappedBy = "person")
    private List<Filiere> filiereList;
    @OneToMany(mappedBy = "responsable")
    private List<Sujet> sujetList;
    @OneToMany(mappedBy = "directeur")
    private List<Laboratoire> laboratoireList;
    @JoinColumn(name = "laboratoire", referencedColumnName = "id_laboratoire")
    @ManyToOne
    private Laboratoire laboratoire;

    public Person() {
    }

    public Person(Integer idPerson) {
        this.idPerson = idPerson;
    }

    public Integer getIdPerson() {
        return idPerson;
    }

    public void setIdPerson(Integer idPerson) {
        this.idPerson = idPerson;
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

    public String getNomAr() {
        return nomAr;
    }

    public void setNomAr(String nomAr) {
        this.nomAr = nomAr;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public Boolean getEtatMatrimonial() {
        return etatMatrimonial;
    }

    public void setEtatMatrimonial(Boolean etatMatrimonial) {
        this.etatMatrimonial = etatMatrimonial;
    }

    public String getFonction() {
        return fonction;
    }

    public void setFonction(String fonction) {
        this.fonction = fonction;
    }

    public String getLieuNaissance() {
        return lieuNaissance;
    }

    public void setLieuNaissance(String lieuNaissance) {
        this.lieuNaissance = lieuNaissance;
    }

    public String getLieuNaissanceAr() {
        return lieuNaissanceAr;
    }

    public void setLieuNaissanceAr(String lieuNaissanceAr) {
        this.lieuNaissanceAr = lieuNaissanceAr;
    }

    public String getNationalite() {
        return nationalite;
    }

    public void setNationalite(String nationalite) {
        this.nationalite = nationalite;
    }

    public BigInteger getNumeroTelephonne() {
        return numeroTelephonne;
    }

    public void setNumeroTelephonne(BigInteger numeroTelephonne) {
        this.numeroTelephonne = numeroTelephonne;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public byte[] getPhoto() {
        return photo;
    }

    public void setPhoto(byte[] photo) {
        this.photo = photo;
    }

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public Laboratoire getLaboratoire() {
        return laboratoire;
    }

    public void setLaboratoire(Laboratoire laboratoire) {
        this.laboratoire = laboratoire;
    }
    

    @XmlTransient
    public List<Filiere> getFiliereList() {
        return filiereList;
    }

    public void setFiliereList(List<Filiere> filiereList) {
        this.filiereList = filiereList;
    }

    @XmlTransient
    public List<Sujet> getSujetList() {
        return sujetList;
    }

    public void setSujetList(List<Sujet> sujetList) {
        this.sujetList = sujetList;
    }

    @XmlTransient
    public List<Laboratoire> getLaboratoireList() {
        return laboratoireList;
    }

    public void setLaboratoireList(List<Laboratoire> laboratoireList) {
        this.laboratoireList = laboratoireList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPerson != null ? idPerson.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Person)) {
            return false;
        }
        Person other = (Person) object;
        if ((this.idPerson == null && other.idPerson != null) || (this.idPerson != null && !this.idPerson.equals(other.idPerson))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Person[ idPerson=" + idPerson + " ]";
    }
    
}
