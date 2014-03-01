/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Destination", catalog = "GestionStock", schema = "", uniqueConstraints = {
    @UniqueConstraint(columnNames = {"referenceDestination"})})
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Destination.findAll", query = "SELECT d FROM Destination d"),
    @NamedQuery(name = "Destination.findByIdDestination", query = "SELECT d FROM Destination d WHERE d.idDestination = :idDestination"),
    @NamedQuery(name = "Destination.findByReferenceDestination", query = "SELECT d FROM Destination d WHERE d.referenceDestination = :referenceDestination")})
public class Destination implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDestination", nullable = false)
    private Integer idDestination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "referenceDestination", nullable = false, length = 45)
    private String referenceDestination;
    @Lob
    @Size(max = 65535)
    @Column(name = "description", length = 65535)
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mouvementTo")
    private List<Mouvement> mouvementList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mouvementFrom")
    private List<Mouvement> mouvementList1;

    public Destination() {
    }

    public Destination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public Destination(Integer idDestination, String referenceDestination) {
        this.idDestination = idDestination;
        this.referenceDestination = referenceDestination;
    }

    public Integer getIdDestination() {
        return idDestination;
    }

    public void setIdDestination(Integer idDestination) {
        this.idDestination = idDestination;
    }

    public String getReferenceDestination() {
        return referenceDestination;
    }

    public void setReferenceDestination(String referenceDestination) {
        this.referenceDestination = referenceDestination;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public List<Mouvement> getMouvementList() {
        return mouvementList;
    }

    public void setMouvementList(List<Mouvement> mouvementList) {
        this.mouvementList = mouvementList;
    }

    @XmlTransient
    public List<Mouvement> getMouvementList1() {
        return mouvementList1;
    }

    public void setMouvementList1(List<Mouvement> mouvementList1) {
        this.mouvementList1 = mouvementList1;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDestination != null ? idDestination.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Destination)) {
            return false;
        }
        Destination other = (Destination) object;
        if ((this.idDestination == null && other.idDestination != null) || (this.idDestination != null && !this.idDestination.equals(other.idDestination))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "net.cofares.entity.Destination[ idDestination=" + idDestination + " ]";
    }
    
}
