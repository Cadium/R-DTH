/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.entity;

import java.io.Serializable;
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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author USER
 */
@Entity
@Table(name = "customers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Customers.findAll", query = "SELECT c FROM Customers c")
    , @NamedQuery(name = "Customers.findByCustomerid", query = "SELECT c FROM Customers c WHERE c.customerid = :customerid")
    , @NamedQuery(name = "Customers.findByCardnumber", query = "SELECT c FROM Customers c WHERE c.cardnumber = :cardnumber")
    , @NamedQuery(name = "Customers.findByCustomername", query = "SELECT c FROM Customers c WHERE c.customername = :customername")
    , @NamedQuery(name = "Customers.findByEmailaddress", query = "SELECT c FROM Customers c WHERE c.emailaddress = :emailaddress")
    , @NamedQuery(name = "Customers.findByPassword", query = "SELECT c FROM Customers c WHERE c.password = :password")})
public class Customers implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CUSTOMERID")
    private Integer customerid;
    @Size(max = 255)
    @Column(name = "CARDNUMBER")
    private String cardnumber;
    @Size(max = 255)
    @Column(name = "CUSTOMERNAME")
    private String customername;
    @Size(max = 255)
    @Column(name = "EMAILADDRESS")
    private String emailaddress;
    @Size(max = 255)
    @Column(name = "PASSWORD")
    private String password;
    @JoinColumn(name = "serviceSubscribeId", referencedColumnName = "SERVICESUBSCRIBEID")
    @ManyToOne
    private Servicessubscriptions serviceSubscribeId;

    public Customers() {
    }

    public Customers(Integer customerid) {
        this.customerid = customerid;
    }

    public Integer getCustomerid() {
        return customerid;
    }

    public void setCustomerid(Integer customerid) {
        this.customerid = customerid;
    }

    public String getCardnumber() {
        return cardnumber;
    }

    public void setCardnumber(String cardnumber) {
        this.cardnumber = cardnumber;
    }

    public String getCustomername() {
        return customername;
    }

    public void setCustomername(String customername) {
        this.customername = customername;
    }

    public String getEmailaddress() {
        return emailaddress;
    }

    public void setEmailaddress(String emailaddress) {
        this.emailaddress = emailaddress;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Servicessubscriptions getServiceSubscribeId() {
        return serviceSubscribeId;
    }

    public void setServiceSubscribeId(Servicessubscriptions serviceSubscribeId) {
        this.serviceSubscribeId = serviceSubscribeId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (customerid != null ? customerid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Customers)) {
            return false;
        }
        Customers other = (Customers) object;
        if ((this.customerid == null && other.customerid != null) || (this.customerid != null && !this.customerid.equals(other.customerid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.steve.entity.Customers[ customerid=" + customerid + " ]";
    }
    
}
