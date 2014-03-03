/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.util.Collection;
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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Destination")
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
    @Column(name = "idDestination")
    private Integer idDestination;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "referenceDestination")
    private String referenceDestination;
    @Lob
    @Size(max = 65535)
    @Column(name = "description")
    private String description;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mouvementTo")
    private Collection<Mouvement> mouvementCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "mouvementFrom")
    private Collection<Mouvement> mouvementCollection1;

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
    public Collection<Mouvement> getMouvementCollection() {
        return mouvementCollection;
    }

    public void setMouvementCollection(Collection<Mouvement> mouvementCollection) {
        this.mouvementCollection = mouvementCollection;
    }

    @XmlTransient
    public Collection<Mouvement> getMouvementCollection1() {
        return mouvementCollection1;
    }

    public void setMouvementCollection1(Collection<Mouvement> mouvementCollection1) {
        this.mouvementCollection1 = mouvementCollection1;
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
        return "["+ idDestination + ":"+ referenceDestination+" ]";
    }
    
}
