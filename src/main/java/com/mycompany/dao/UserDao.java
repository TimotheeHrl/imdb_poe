package com.mycompany.dao;

import com.mycompany.entity.User;
import com.mycompany.helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class UserDao {
    
    EntityManager em = SessionHelper.getEntityManager();
    int nextId =101;
    
    public List<User> findAll() {
        Query query = em.createQuery("Select u FROM User u");
        List <User> res = query.getResultList();
        return res;
    }
    
        public User findById(long id) {
        User a = em.find(User.class, id);
        return a;
    }

    public User create(User a) {
        EntityTransaction trans = null;
        a.setId(nextId);
        nextId++;
        try {
            trans = em.getTransaction();
            trans.begin();
            em.persist(a);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
        }
        return a;
    }



    public void update(User ad) {
        EntityTransaction trans = null;
        User u = findById(ad.getId());
        u.setLastname(ad.getLastname()); 
        u.setFirstname(ad.getFirstname()); 
        u.setRole(ad.getRole()); 
        u.setEmail(ad.getEmail()); 
        u.setPassword(ad.getPassword()); 
        try {
            trans = em.getTransaction();
            trans.begin();
            em.merge(u);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
        }
    }
    
    public void delete(User a) {
        EntityTransaction trans = null;
        User ad = em.find(User.class, a.getId());
        try {
            trans = em.getTransaction();
            trans.begin();
            em.remove(ad);
            trans.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (trans != null) {
                trans.rollback();
            }
        }
    }
}