/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.entity.Actor;
import com.mycompany.entity.Movie;
import com.mycompany.helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author maxla
 */
public class ActorDao {
    EntityManager em = SessionHelper.getEntityManager();
    
    
    public Actor findById(long id){
        
        Actor actor = em.find(Actor.class, id);
        
        if(actor==null){
            System.out.println("movie inexistant");
        }
        return actor;     
    }
    
    public void create(Actor actor){
        if(actor == null){
            System.out.println("L'objet actor ne peut pas etre null");
            return;
        }
        // On declare notre transaction avec pour valeur par defaut null
        EntityTransaction trans = null; 
        try{
            trans = em.getTransaction();
            trans.begin();
            em.persist(actor);
            trans.commit();
        }catch(Exception e){
            System.out.println("Une erreur est survenu lors de la creation");
            // Une erreur est survenue, on discard les actions entam�s dans la transaction
            if(trans == null){
                trans.rollback();
            }           
        }
    }
    
    public void update(Long id, Actor actor) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Actor actorToUpdate = em.find(Actor.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (actorToUpdate == null) {
            System.out.println("Le film avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        
        actorToUpdate.copy(actor);
        
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(actorToUpdate);           
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
    public List<Actor> findAll(){
        Query query = em.createQuery("SELECT a FROM Actor a"); 
        return query.getResultList();
    }
    
    
    
    public void delete(Long id) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Actor ActorToDeletee = em.find(Actor.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (ActorToDeletee == null) {
            System.out.println("L'acteur avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(ActorToDeletee);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
