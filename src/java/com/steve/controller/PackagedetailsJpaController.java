/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.steve.controller;

import com.steve.controller.exceptions.NonexistentEntityException;
import com.steve.controller.exceptions.RollbackFailureException;
import com.steve.entity.Packagedetails;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import com.steve.entity.Servicessubscriptions;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.transaction.UserTransaction;

/**
 *
 * @author USER
 */
public class PackagedetailsJpaController implements Serializable {

    public PackagedetailsJpaController(UserTransaction utx, EntityManagerFactory emf) {
        this.utx = utx;
        this.emf = emf;
    }
    private UserTransaction utx = null;
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Packagedetails packagedetails) throws RollbackFailureException, Exception {
        if (packagedetails.getServicessubscriptionsCollection() == null) {
            packagedetails.setServicessubscriptionsCollection(new ArrayList<Servicessubscriptions>());
        }
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Collection<Servicessubscriptions> attachedServicessubscriptionsCollection = new ArrayList<Servicessubscriptions>();
            for (Servicessubscriptions servicessubscriptionsCollectionServicessubscriptionsToAttach : packagedetails.getServicessubscriptionsCollection()) {
                servicessubscriptionsCollectionServicessubscriptionsToAttach = em.getReference(servicessubscriptionsCollectionServicessubscriptionsToAttach.getClass(), servicessubscriptionsCollectionServicessubscriptionsToAttach.getServicesubscribeid());
                attachedServicessubscriptionsCollection.add(servicessubscriptionsCollectionServicessubscriptionsToAttach);
            }
            packagedetails.setServicessubscriptionsCollection(attachedServicessubscriptionsCollection);
            em.persist(packagedetails);
            for (Servicessubscriptions servicessubscriptionsCollectionServicessubscriptions : packagedetails.getServicessubscriptionsCollection()) {
                Packagedetails oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionServicessubscriptions = servicessubscriptionsCollectionServicessubscriptions.getPackagedetailPackagedetailid();
                servicessubscriptionsCollectionServicessubscriptions.setPackagedetailPackagedetailid(packagedetails);
                servicessubscriptionsCollectionServicessubscriptions = em.merge(servicessubscriptionsCollectionServicessubscriptions);
                if (oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionServicessubscriptions != null) {
                    oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionServicessubscriptions.getServicessubscriptionsCollection().remove(servicessubscriptionsCollectionServicessubscriptions);
                    oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionServicessubscriptions = em.merge(oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionServicessubscriptions);
                }
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

    public void edit(Packagedetails packagedetails) throws NonexistentEntityException, RollbackFailureException, Exception {
        EntityManager em = null;
        try {
            utx.begin();
            em = getEntityManager();
            Packagedetails persistentPackagedetails = em.find(Packagedetails.class, packagedetails.getPackagedetailid());
            Collection<Servicessubscriptions> servicessubscriptionsCollectionOld = persistentPackagedetails.getServicessubscriptionsCollection();
            Collection<Servicessubscriptions> servicessubscriptionsCollectionNew = packagedetails.getServicessubscriptionsCollection();
            Collection<Servicessubscriptions> attachedServicessubscriptionsCollectionNew = new ArrayList<Servicessubscriptions>();
            for (Servicessubscriptions servicessubscriptionsCollectionNewServicessubscriptionsToAttach : servicessubscriptionsCollectionNew) {
                servicessubscriptionsCollectionNewServicessubscriptionsToAttach = em.getReference(servicessubscriptionsCollectionNewServicessubscriptionsToAttach.getClass(), servicessubscriptionsCollectionNewServicessubscriptionsToAttach.getServicesubscribeid());
                attachedServicessubscriptionsCollectionNew.add(servicessubscriptionsCollectionNewServicessubscriptionsToAttach);
            }
            servicessubscriptionsCollectionNew = attachedServicessubscriptionsCollectionNew;
            packagedetails.setServicessubscriptionsCollection(servicessubscriptionsCollectionNew);
            packagedetails = em.merge(packagedetails);
            for (Servicessubscriptions servicessubscriptionsCollectionOldServicessubscriptions : servicessubscriptionsCollectionOld) {
                if (!servicessubscriptionsCollectionNew.contains(servicessubscriptionsCollectionOldServicessubscriptions)) {
                    servicessubscriptionsCollectionOldServicessubscriptions.setPackagedetailPackagedetailid(null);
                    servicessubscriptionsCollectionOldServicessubscriptions = em.merge(servicessubscriptionsCollectionOldServicessubscriptions);
                }
            }
            for (Servicessubscriptions servicessubscriptionsCollectionNewServicessubscriptions : servicessubscriptionsCollectionNew) {
                if (!servicessubscriptionsCollectionOld.contains(servicessubscriptionsCollectionNewServicessubscriptions)) {
                    Packagedetails oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions = servicessubscriptionsCollectionNewServicessubscriptions.getPackagedetailPackagedetailid();
                    servicessubscriptionsCollectionNewServicessubscriptions.setPackagedetailPackagedetailid(packagedetails);
                    servicessubscriptionsCollectionNewServicessubscriptions = em.merge(servicessubscriptionsCollectionNewServicessubscriptions);
                    if (oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions != null && !oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions.equals(packagedetails)) {
                        oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions.getServicessubscriptionsCollection().remove(servicessubscriptionsCollectionNewServicessubscriptions);
                        oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions = em.merge(oldPackagedetailPackagedetailidOfServicessubscriptionsCollectionNewServicessubscriptions);
                    }
                }
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
                Integer id = packagedetails.getPackagedetailid();
                if (findPackagedetails(id) == null) {
                    throw new NonexistentEntityException("The packagedetails with id " + id + " no longer exists.");
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
            Packagedetails packagedetails;
            try {
                packagedetails = em.getReference(Packagedetails.class, id);
                packagedetails.getPackagedetailid();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The packagedetails with id " + id + " no longer exists.", enfe);
            }
            Collection<Servicessubscriptions> servicessubscriptionsCollection = packagedetails.getServicessubscriptionsCollection();
            for (Servicessubscriptions servicessubscriptionsCollectionServicessubscriptions : servicessubscriptionsCollection) {
                servicessubscriptionsCollectionServicessubscriptions.setPackagedetailPackagedetailid(null);
                servicessubscriptionsCollectionServicessubscriptions = em.merge(servicessubscriptionsCollectionServicessubscriptions);
            }
            em.remove(packagedetails);
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

    public List<Packagedetails> findPackagedetailsEntities() {
        return findPackagedetailsEntities(true, -1, -1);
    }

    public List<Packagedetails> findPackagedetailsEntities(int maxResults, int firstResult) {
        return findPackagedetailsEntities(false, maxResults, firstResult);
    }

    private List<Packagedetails> findPackagedetailsEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Packagedetails.class));
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

    public Packagedetails findPackagedetails(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Packagedetails.class, id);
        } finally {
            em.close();
        }
    }

    public int getPackagedetailsCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Packagedetails> rt = cq.from(Packagedetails.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
