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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mabsalhi.sos
 */
@Entity
@Table(name = "t_pieces")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pieces.findAll", query = "SELECT q FROM Pieces q")
    , @NamedQuery(name = "Pieces.findByIdPieces", query = "SELECT p FROM Pieces p WHERE p.idPieces = :idPieces")
    , @NamedQuery(name = "Pieces.findByPathScan", query = "SELECT p FROM Pieces p WHERE p.pathScan = :pathScan")
    , @NamedQuery(name = "Pieces.findByDateObtention", query = "SELECT p FROM Pieces p WHERE p.dateObtention = :dateObtention")
    , @NamedQuery(name = "Pieces.findByIntitule", query = "SELECT p FROM Pieces p WHERE p.intitule = :intitule")
    , @NamedQuery(name = "Pieces.findByOptimisticLock", query = "SELECT p FROM Pieces p WHERE p.optimisticLock = :optimisticLock")
    , @NamedQuery(name = "Pieces.findByPartieDelivrante", query = "SELECT p FROM Pieces p WHERE p.partieDelivrante = :partieDelivrante")})
public class Pieces implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id_pieces")
    private Integer idPieces;
    @Size(max = 255)
    @Column(name = "path_scan")
    private String pathScan;
    @Column(name = "date_obtention")
    @Temporal(TemporalType.DATE)
    private Date dateObtention;
    @Size(max = 255)
    @Column(name = "intitiulé")
    private String intitule;
    @Column(name = "optimistic_lock")
    private Integer optimisticLock;
    @Size(max = 255)
    @Column(name = "partie_delivrante")
    private String partieDelivrante;
    @JoinColumn(name = "etudiant", referencedColumnName = "id_etudiant")
    @ManyToOne
    private Etudiant etudiant;

    public Pieces() {
    }

    public Pieces(Integer idPieces) {
        this.idPieces = idPieces;
    }

    public Integer getIdPieces() {
        return idPieces;
    }

    public void setIdPieces(Integer idPieces) {
        this.idPieces = idPieces;
    }

    public String getPathScan() {
        return pathScan;
    }

    public String getIntitule() {
        return intitule;
    }

    public void setIntitule(String intitule) {
        this.intitule = intitule;
    }
    
    public void setPathScan(String pathScan) {
        this.pathScan = pathScan;
    }

    public Date getDateObtention() {
        return dateObtention;
    }

    public void setDateObtention(Date dateObtention) {
        this.dateObtention = dateObtention;
    }

    public Integer getOptimisticLock() {
        return optimisticLock;
    }

    public void setOptimisticLock(Integer optimisticLock) {
        this.optimisticLock = optimisticLock;
    }

    public String getPartieDelivrante() {
        return partieDelivrante;
    }

    public void setPartieDelivrante(String partieDelivrante) {
        this.partieDelivrante = partieDelivrante;
    }

    public Etudiant getEtudiant() {
        return etudiant;
    }

    public void setEtudiant(Etudiant etudiant) {
        this.etudiant = etudiant;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPieces != null ? idPieces.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pieces)) {
            return false;
        }
        Pieces other = (Pieces) object;
        if ((this.idPieces == null && other.idPieces != null) || (this.idPieces != null && !this.idPieces.equals(other.idPieces))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sos.fso.cdoc.insc.entities.Pieces[ idPieces=" + idPieces + " ]";
    }

}
