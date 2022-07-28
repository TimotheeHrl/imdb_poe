/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.dao;

import com.mycompany.entity.Comment;
import com.mycompany.entity.Genre;
import com.mycompany.helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

/**
 *
 * @author maxla
 */
public class CommentDao {
    EntityManager em = SessionHelper.getEntityManager();
    
    
    public Comment findById(long id){
        
        Comment comment = em.find(Comment.class, id);
        
        if(comment==null){
            System.out.println("movie inexistant");
        }
        return comment;     
    }
    
    public void create(Comment comment){
        if(comment == null){
            System.out.println("L'objet comment ne peut pas etre null");
            return;
        }
        // On declare notre transaction avec pour valeur par defaut null
        EntityTransaction trans = null; 
        try{
            trans = em.getTransaction();
            trans.begin();
            em.persist(comment);
            trans.commit();
        }catch(Exception e){
            System.out.println("Une erreur est survenu lors de la creation");
            // Une erreur est survenue, on discard les actions entam�s dans la transaction
            if(trans == null){
                trans.rollback();
            }           
        }
    }
    
    public void update(Long id, Comment comment) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Comment commentToUpdate = em.find(Comment.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (commentToUpdate == null) {
            System.out.println("Le genre avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        
        commentToUpdate.copy(comment);
        
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(commentToUpdate);           
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
    public List<Genre> findAll(){
        Query query = em.createQuery("SELECT c FROM Comment c"); 
        return query.getResultList();
    }
    
    
    
    public void delete(Long id) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Comment commentToDeletee = em.find(Comment.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (commentToDeletee == null) {
            System.out.println("Lobjet Genre avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(commentToDeletee);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
}
