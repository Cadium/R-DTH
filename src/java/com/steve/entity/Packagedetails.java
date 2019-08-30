/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "packagedetails")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Packagedetails.findAll", query = "SELECT p FROM Packagedetails p")
    , @NamedQuery(name = "Packagedetails.findByPackagedetailid", query = "SELECT p FROM Packagedetails p WHERE p.packagedetailid = :packagedetailid")
    , @NamedQuery(name = "Packagedetails.findByPackagetype", query = "SELECT p FROM Packagedetails p WHERE p.packagetype = :packagetype")})
public class Packagedetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "PACKAGEDETAILID")
    private Integer packagedetailid;
    @Size(max = 255)
    @Column(name = "PACKAGETYPE")
    private String packagetype;
    @OneToMany(mappedBy = "packagedetailPackagedetailid")
    private Collection<Servicessubscriptions> servicessubscriptionsCollection;

    public Packagedetails() {
    }

    public Packagedetails(Integer packagedetailid) {
        this.packagedetailid = packagedetailid;
    }

    public Integer getPackagedetailid() {
        return packagedetailid;
    }

    public void setPackagedetailid(Integer packagedetailid) {
        this.packagedetailid = packagedetailid;
    }

    public String getPackagetype() {
        return packagetype;
    }

    public void setPackagetype(String packagetype) {
        this.packagetype = packagetype;
    }

    @XmlTransient
    public Collection<Servicessubscriptions> getServicessubscriptionsCollection() {
        return servicessubscriptionsCollection;
    }

    public void setServicessubscriptionsCollection(Collection<Servicessubscriptions> servicessubscriptionsCollection) {
        this.servicessubscriptionsCollection = servicessubscriptionsCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (packagedetailid != null ? packagedetailid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Packagedetails)) {
            return false;
        }
        Packagedetails other = (Packagedetails) object;
        if ((this.packagedetailid == null && other.packagedetailid != null) || (this.packagedetailid != null && !this.packagedetailid.equals(other.packagedetailid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.steve.entity.Packagedetails[ packagedetailid=" + packagedetailid + " ]";
    }
    
}
