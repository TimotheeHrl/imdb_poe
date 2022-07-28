package com.mycompany.dao;

<<<<<<< HEAD
=======
import com.mycompany.entity.Movie;
>>>>>>> e0ec4084a0e56540fafe81d89990a7781457f633
import com.mycompany.entity.User;
import com.mycompany.helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

<<<<<<< HEAD
=======
/**
 *
 * @author ben
 */
>>>>>>> e0ec4084a0e56540fafe81d89990a7781457f633
public class UserDao {
    EntityManager em = SessionHelper.getEntityManager();

    public User findById(long id){
        
        User user = em.find(User.class, id);
        
        if(user==null){
            System.out.println("movie inexistant");
        }
        return user;     
    }
    
    public void create(User user){
        if(user == null){
            System.out.println("L'objet user ne peut pas etre null");
            return;
        }
        // On declare notre transaction avec pour valeur par defaut null
        EntityTransaction trans = null; 
        try{
            trans = em.getTransaction();
            trans.begin();
            em.persist(user);
            trans.commit();
        }catch(Exception e){
            System.out.println("Une erreur est survenu lors de la creation");
            // Une erreur est survenue, on discard les actions entam�s dans la transaction
            if(trans == null){
                trans.rollback();
            }           
        }
    }
    
    public void update(Long id, User user) {
        
        // On r�cup�re le role qu'on souhaite modifier
        User userToUpdate = em.find(User.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (userToUpdate == null) {
            System.out.println("Le film avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        
        userToUpdate.copy(user);
        
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(userToUpdate);           
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
    public List<User> findAll(){
        Query query = em.createQuery("SELECT u FROM User u"); 
        return query.getResultList();
    }
    
    
    
    public void delete(Long id) {
        
        // On r�cup�re le role qu'on souhaite modifier
        User userToDeletee = em.find(User.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (userToDeletee == null) {
            System.out.println("Le movie avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(userToDeletee);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
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