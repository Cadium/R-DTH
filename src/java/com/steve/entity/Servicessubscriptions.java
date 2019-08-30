/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.entity;

import java.io.Serializable;
import java.util.Collection;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "servicessubscriptions")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Servicessubscriptions.findAll", query = "SELECT s FROM Servicessubscriptions s")
    , @NamedQuery(name = "Servicessubscriptions.findByServicesubscribeid", query = "SELECT s FROM Servicessubscriptions s WHERE s.servicesubscribeid = :servicesubscribeid")
    , @NamedQuery(name = "Servicessubscriptions.findBySubscribedate", query = "SELECT s FROM Servicessubscriptions s WHERE s.subscribedate = :subscribedate")})
public class Servicessubscriptions implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "SERVICESUBSCRIBEID")
    private Integer servicesubscribeid;
    @Column(name = "SUBSCRIBEDATE")
    @Temporal(TemporalType.DATE)
    private Date subscribedate;
    @JoinColumn(name = "PACKAGEDETAIL_PACKAGEDETAILID", referencedColumnName = "PACKAGEDETAILID")
    @ManyToOne
    private Packagedetails packagedetailPackagedetailid;
    @OneToMany(mappedBy = "serviceSubscribeId")
    private Collection<Customers> customersCollection;

    public Servicessubscriptions() {
    }

    public Servicessubscriptions(Integer servicesubscribeid) {
        this.servicesubscribeid = servicesubscribeid;
    }

    public Integer getServicesubscribeid() {
        return servicesubscribeid;
    }

    public void setServicesubscribeid(Integer servicesubscribeid) {
        this.servicesubscribeid = servicesubscribeid;
    }

    public Date getSubscribedate() {
        return subscribedate;
    }

    public void setSubscribedate(Date subscribedate) {
        this.subscribedate = subscribedate;
    }

    public Packagedetails getPackagedetailPackagedetailid() {
        return packagedetailPackagedetailid;
    }

    public void setPackagedetailPackagedetailid(Packagedetails packagedetailPackagedetailid) {
        this.packagedetailPackagedetailid = packagedetailPackagedetailid;
    }

    @XmlTransient
    public Collection<Customers> getCustomersCollection() {
        return customersCollection;
    }

    public void setCustomersCollection(Collection<Customers> customersCollection) {
        this.customersCollection = customersCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (servicesubscribeid != null ? servicesubscribeid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Servicessubscriptions)) {
            return false;
        }
        Servicessubscriptions other = (Servicessubscriptions) object;
        if ((this.servicesubscribeid == null && other.servicesubscribeid != null) || (this.servicesubscribeid != null && !this.servicesubscribeid.equals(other.servicesubscribeid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.steve.entity.Servicessubscriptions[ servicesubscribeid=" + servicesubscribeid + " ]";
    }
    
}
