/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.controller;

import com.steve.controller.exceptions.NonexistentEntityException;
import com.steve.controller.exceptions.RollbackFailureException;
import com.steve.entity.Customers;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.steve.entity.Servicessubscriptions;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author USER
 */
public class CustomersJpaController implements Serializable {

    public CustomersJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Customers customers) throws RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Servicessubscriptions serviceSubscribeId = customers.getServiceSubscribeId();
            if (serviceSubscribeId != null) {
                serviceSubscribeId = em.getReference(serviceSubscribeId.getClass(), serviceSubscribeId.getServicesubscribeid());
                customers.setServiceSubscribeId(serviceSubscribeId);
            }
            em.persist(customers);
            if (serviceSubscribeId != null) {
                serviceSubscribeId.getCustomersCollection().add(customers);
                serviceSubscribeId = em.merge(serviceSubscribeId);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Customers customers) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customers persistentCustomers = em.find(Customers.class, customers.getCustomerid());
            Servicessubscriptions serviceSubscribeIdOld = persistentCustomers.getServiceSubscribeId();
            Servicessubscriptions serviceSubscribeIdNew = customers.getServiceSubscribeId();
            if (serviceSubscribeIdNew != null) {
                serviceSubscribeIdNew = em.getReference(serviceSubscribeIdNew.getClass(), serviceSubscribeIdNew.getServicesubscribeid());
                customers.setServiceSubscribeId(serviceSubscribeIdNew);
            }
            customers = em.merge(customers);
            if (serviceSubscribeIdOld != null && !serviceSubscribeIdOld.equals(serviceSubscribeIdNew)) {
                serviceSubscribeIdOld.getCustomersCollection().remove(customers);
                serviceSubscribeIdOld = em.merge(serviceSubscribeIdOld);
            }
            if (serviceSubscribeIdNew != null && !serviceSubscribeIdNew.equals(serviceSubscribeIdOld)) {
                serviceSubscribeIdNew.getCustomersCollection().add(customers);
                serviceSubscribeIdNew = em.merge(serviceSubscribeIdNew);
            }
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = customers.getCustomerid();
                if (findCustomers(id) == null) {
                    throw new NonexistentEntityException("The customers with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Customers customers;
            try {
                customers = em.getReference(Customers.class, id);
                customers.getCustomerid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The customers with id " + id + " no longer exists.", enfe);
            }
            Servicessubscriptions serviceSubscribeId = customers.getServiceSubscribeId();
            if (serviceSubscribeId != null) {
                serviceSubscribeId.getCustomersCollection().remove(customers);
                serviceSubscribeId = em.merge(serviceSubscribeId);
            }
            em.remove(customers);
            utx.commit();
        } catch (Exception ex) {
            try {
                utx.rollback();
            } catch (Exception re) {
                throw new RollbackFailureException("An error occurred attempting to roll back the transaction.", re);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Customers> findCustomersEntities() {
        return findCustomersEntities(true, -1, -1);
    }

    public List<Customers> findCustomersEntities(int maxResults, int firstResult) {
        return findCustomersEntities(false, maxResults, firstResult);
    }

    private List<Customers> findCustomersEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Customers.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Customers findCustomers(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Customers.class, id);
        } finally {
            em.close();
        }
    }

    public int getCustomersCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Customers> rt = cq.from(Customers.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
