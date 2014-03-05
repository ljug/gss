/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package net.cofares;

import java.io.Serializable;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author pfares
 */
@Entity
@Table(name = "PlusMoins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PlusMoins.findAll", query = "SELECT p FROM PlusMoins p"),
    @NamedQuery(name = "PlusMoins.findByIdPM", query = "SELECT p FROM PlusMoins p WHERE p.idPM = :idPM"),
    @NamedQuery(name = "PlusMoins.findById", query = "SELECT p FROM PlusMoins p WHERE p.id = :id"),
    @NamedQuery(name = "PlusMoins.findByMto", query = "SELECT p FROM PlusMoins p WHERE p.mto = :mto"),
    @NamedQuery(name = "PlusMoins.findByFr", query = "SELECT p FROM PlusMoins p WHERE p.fr = :fr"),
    @NamedQuery(name = "PlusMoins.findByPlusqt", query = "SELECT p FROM PlusMoins p WHERE p.plusqt = :plusqt"),
    @NamedQuery(name = "PlusMoins.findByMoinsqt", query = "SELECT p FROM PlusMoins p WHERE p.moinsqt = :moinsqt")})
public class PlusMoins implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Size(max = 23)
    @Column(name = "idPM")
    private String idPM;
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private int id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mto")
    private int mto;
    @Column(name = "fr")
    private Integer fr;
    @Column(name = "plusqt")
    private BigInteger plusqt;
    @Column(name = "moinsqt")
    private BigInteger moinsqt;

    public PlusMoins() {
    }

    public String getIdPM() {
        return idPM;
    }

    public void setIdPM(String idPM) {
        this.idPM = idPM;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getMto() {
        return mto;
    }

    public void setMto(int mto) {
        this.mto = mto;
    }

    public Integer getFr() {
        return fr;
    }

    public void setFr(Integer fr) {
        this.fr = fr;
    }

    public BigInteger getPlusqt() {
        return plusqt;
    }

    public void setPlusqt(BigInteger plusqt) {
        this.plusqt = plusqt;
    }

    public BigInteger getMoinsqt() {
        return moinsqt;
    }

    public void setMoinsqt(BigInteger moinsqt) {
        this.moinsqt = moinsqt;
    }
    
}
