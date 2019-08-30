/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.controller;

import com.steve.entity.Packagedetails;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author USER
 */
@Stateless
public class PackagedetailsFacade extends AbstractFacade<Packagedetails> {

    @PersistenceContext(unitName = "WebApplication6PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public PackagedetailsFacade() {
        super(Packagedetails.class);
    }
    
}
