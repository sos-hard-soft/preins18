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
@Table(name = "t_etudiant")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etudiant.findAll", query = "SELECT e FROM Etudiant e")
    , @NamedQuery(name = "Etudiant.findByIdEtudiant", query = "SELECT e FROM Etudiant e WHERE e.idEtudiant = :idEtudiant")
    , @NamedQuery(name = "Etudiant.findByActivite", query = "SELECT e FROM Etudiant e WHERE e.activite = :activite")
    , @NamedQuery(name = "Etudiant.findByAdresse", query = "SELECT e FROM Etudiant e WHERE e.adresse = :adresse")
    , @NamedQuery(name = "Etudiant.findByCin", query = "SELECT e FROM Etudiant e WHERE e.cin = :cin")
    , @NamedQuery(name = "Etudiant.findByCne", query = "SELECT e FROM Etudiant e WHERE e.cne = :cne")
    , @NamedQuery(name = "Etudiant.findByDateNaissance", query = "SELECT e FROM Etudiant e WHERE e.dateNaissance = :dateNaissance")
    , @NamedQuery(name = "Etudiant.findByEmail", query = "SELECT e FROM Etudiant e WHERE e.email = :email")
    , @NamedQuery(name = "Etudiant.findByEtatMatrimonial", query = "SELECT e FROM Etudiant e WHERE e.etatMatrimonial = :etatMatrimonial")
    , @NamedQuery(name = "Etudiant.findByLieuNaissance", query = "SELECT e FROM Etudiant e WHERE e.lieuNaissance = :lieuNaissance")
    , @NamedQuery(name = "Etudiant.findByLieuNaissanceAr", query = "SELECT e FROM Etudiant e WHERE e.lieuNaissanceAr = :lieuNaissanceAr")
    , @NamedQuery(name = "Etudiant.findByNationalite", query = "SELECT e FROM Etudiant e WHERE e.nationalite = :nationalite")
    , @NamedQuery(name = "Etudiant.findByNom", query = "SELECT e FROM Etudiant e WHERE e.nom = :nom")
    , @NamedQuery(name = "Etudiant.findByNomAr", query = "SELECT e FROM Etudiant e WHERE e.nomAr = :nomAr")
    , @NamedQuery(name = "Etudiant.findByNumeroTelephonne", query = "SELECT e FROM Etudiant e WHERE e.numeroTelephonne = :numeroTelephonne")
    , @NamedQuery(name = "Etudiant.findByOptimisticLock", query = "SELECT e FROM Etudiant e WHERE e.optimisticLock = :optimisticLock")
    , @NamedQuery(name = "Etudiant.findByPrenom", query = "SELECT e FROM Etudiant e WHERE e.prenom = :prenom")
    , @NamedQuery(name = "Etudiant.findByPrenomAr", query = "SELECT e FROM Etudiant e WHERE e.prenomAr = :prenomAr")
    , @NamedQuery(name = "Etudiant.findBySexe", query = "SELECT e FROM Etudiant e WHERE e.sexe = :sexe")})
public class Etudiant implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_etudiant")
    private Integer idEtudiant;
    @Column(name = "activite")
    private Boolean activite;
    @Size(max = 255)
    @Column(name = "adresse")
    private String adresse;
    @Size(max = 255)
    @Column(name = "cin")
    private String cin;
    @Column(name = "cne")
    private Long cne;
    @Column(name = "date_naissance")
    @Temporal(TemporalType.DATE)
    private Date dateNaissance;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Size(max = 255)
    @Column(name = "email")
    private String email;
    @Column(name = "etat_matrimonial")
    private Boolean etatMatrimonial;
    @Size(max = 255)
    @Column(name = "lieu_naissance")
    private String lieuNaissance;
    @Size(max = 255)
    @Column(name = "lieu_naissance_ar")
    private String lieuNaissanceAr;
    @Size(max = 255)
    @Column(name = "nationalite")
    private String nationalite;
    @Size(max = 255)
    @Column(name = "nom")
    private String nom;
    @Size(max = 255)
    @Column(name = "nom_ar")
    private String nomAr;
    @Column(name = "numero_telephonne")
    private BigInteger numeroTelephonne;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Lob
    @Column(name = "photo")
    private byte[] photo;
    @Size(max = 255)
    @Column(name = "prenom")
    private String prenom;
    @Size(max = 255)
    @Column(name = "prenom_ar")
    private String prenomAr;
    @Column(name = "sexe")
    private Boolean sexe;
    @JoinColumn(name = "branche", referencedColumnName = "id_branche")
    @ManyToOne
    private Branche branche;
    @OneToMany(mappedBy = "etudiant")
    private List<Qualification> qualificationList;
    @OneToMany(mappedBy = "idEtudiant")
    private List<Choix> choixList;
    @OneToMany(mappedBy = "etudiant")
    private List<Pieces> piecesList;

    public Etudiant() {
    }

    public Etudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Integer getIdEtudiant() {
        return idEtudiant;
    }

    public void setIdEtudiant(Integer idEtudiant) {
        this.idEtudiant = idEtudiant;
    }

    public Boolean getActivite() {
        return activite;
    }

    public void setActivite(Boolean activite) {
        this.activite = activite;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getCin() {
        return cin;
    }

    public void setCin(String cin) {
        this.cin = cin;
    }

    public Long getCne() {
        return cne;
    }

    public void setCne(Long cne) {
        this.cne = cne;
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

    public Boolean getEtatMatrimonial() {
        return etatMatrimonial;
    }

    public void setEtatMatrimonial(Boolean etatMatrimonial) {
        this.etatMatrimonial = etatMatrimonial;
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

    public Boolean getSexe() {
        return sexe;
    }

    public void setSexe(Boolean sexe) {
        this.sexe = sexe;
    }

    public Branche getBranche() {
        return branche;
    }

    public void setBranche(Branche branche) {
        this.branche = branche;
    }

    @XmlTransient
    public List<Qualification> getQualificationList() {
        return qualificationList;
    }

    public void setQualificationList(List<Qualification> qualificationList) {
        this.qualificationList = qualificationList;
    }

    @XmlTransient
    public List<Choix> getChoixList() {
        return choixList;
    }

    public void setChoixList(List<Choix> choixList) {
        this.choixList = choixList;
    }

    @XmlTransient
    public List<Pieces> getPiecesList() {
        return piecesList;
    }

    public void setPiecesList(List<Pieces> piecesList) {
        this.piecesList = piecesList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idEtudiant != null ? idEtudiant.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etudiant)) {
            return false;
        }
        Etudiant other = (Etudiant) object;
        if ((this.idEtudiant == null && other.idEtudiant != null) || (this.idEtudiant != null && !this.idEtudiant.equals(other.idEtudiant))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Etudiant[ idEtudiant=" + idEtudiant + " ]";
    }
    
}
