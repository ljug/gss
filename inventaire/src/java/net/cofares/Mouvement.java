/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "Mouvement")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mouvement.findAll", query = "SELECT m FROM Mouvement m"),
    @NamedQuery(name = "Mouvement.findByIdMouvement", query = "SELECT m FROM Mouvement m WHERE m.idMouvement = :idMouvement"),
    @NamedQuery(name = "Mouvement.findByQt", query = "SELECT m FROM Mouvement m WHERE m.qt = :qt"),
    @NamedQuery(name = "Mouvement.findByMouvementcol", query = "SELECT m FROM Mouvement m WHERE m.mouvementcol = :mouvementcol")})
public class Mouvement implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idMouvement")
    private Integer idMouvement;
    @Basic(optional = false)
    @NotNull
    @Column(name = "qt")
    private int qt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Mouvementcol")
    @Temporal(TemporalType.TIMESTAMP)
    private Date mouvementcol;
    @JoinColumn(name = "idArticle", referencedColumnName = "idArticle")
    @ManyToOne(optional = false)
    private Articles idArticle;
    @JoinColumn(name = "MouvementTo", referencedColumnName = "idDestination")
    @ManyToOne(optional = false)
    private Destination mouvementTo;
    @JoinColumn(name = "MouvementFrom", referencedColumnName = "idDestination")
    @ManyToOne(optional = false)
    private Destination mouvementFrom;

    public Mouvement() {
    }

    public Mouvement(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public Mouvement(Integer idMouvement, int qt, Date mouvementcol) {
        this.idMouvement = idMouvement;
        this.qt = qt;
        this.mouvementcol = mouvementcol;
    }

    public Integer getIdMouvement() {
        return idMouvement;
    }

    public void setIdMouvement(Integer idMouvement) {
        this.idMouvement = idMouvement;
    }

    public int getQt() {
        return qt;
    }

    public void setQt(int qt) {
        this.qt = qt;
    }

    public Date getMouvementcol() {
        return mouvementcol;
    }

    public void setMouvementcol(Date mouvementcol) {
        this.mouvementcol = mouvementcol;
    }

    public Articles getIdArticle() {
        return idArticle;
    }

    public void setIdArticle(Articles idArticle) {
        this.idArticle = idArticle;
    }

    public Destination getMouvementTo() {
        return mouvementTo;
    }

    public void setMouvementTo(Destination mouvementTo) {
        this.mouvementTo = mouvementTo;
    }

    public Destination getMouvementFrom() {
        return mouvementFrom;
    }

    public void setMouvementFrom(Destination mouvementFrom) {
        this.mouvementFrom = mouvementFrom;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idMouvement != null ? idMouvement.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Mouvement)) {
            return false;
        }
        Mouvement other = (Mouvement) object;
        if ((this.idMouvement == null && other.idMouvement != null) || (this.idMouvement != null && !this.idMouvement.equals(other.idMouvement))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "[" + idMouvement + ":" + idArticle + "De "+ mouvementFrom+ "A " + mouvementTo + " ]";
    }
    
}
