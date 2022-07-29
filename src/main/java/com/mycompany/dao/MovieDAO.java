package com.mycompany.dao;

import com.mycompany.entity.Movie;
import com.mycompany.helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.Query;

public class MovieDAO {
    EntityManager em = SessionHelper.getEntityManager();
    
    
    public Movie findById(int id){
        
        Movie movie = em.find(Movie.class, id);
        
        if(movie==null){
            System.out.println("movie inexistant");
        }
        return movie;     
    }
    
    public void create(Movie movie){
        if(movie == null){
            System.out.println("L'objet movie ne peut pas etre null");
            return;
        }
        // On declare notre transaction avec pour valeur par defaut null
        EntityTransaction trans = null; 
        try{
            trans = em.getTransaction();
            trans.begin();
            em.persist(movie);
            trans.commit();
        }catch(Exception e){
            System.out.println("Une erreur est survenu lors de la creation");
            // Une erreur est survenue, on discard les actions entam�s dans la transaction
            if(trans == null){
                trans.rollback();
            }           
        }
    }
    
    public void update(int id, Movie movie) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Movie movieToUpdate = em.find(Movie.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (movieToUpdate == null) {
            System.out.println("Le film avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        
        movieToUpdate.copy(movie);
        
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.merge(movieToUpdate);           
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }
    
    public List<Movie> findAll(){
        Query query = em.createQuery("SELECT m FROM Movie m"); 
        return query.getResultList();
    }
    
    
    
    public void delete(int id) {
        
        // On r�cup�re le role qu'on souhaite modifier
        Movie movieToDeletee = em.find(Movie.class, id);
        // Si le role n'existe pas on ne fait pas d'update
        if (movieToDeletee == null) {
            System.out.println("Le movie avec l'id:" + id + " n'existe pas");
            return;
        }
        // on set les donn�es uniquement si elle ne sont pas null
        EntityTransaction tx = null;
        try {
            tx = em.getTransaction();
            tx.begin();
            em.remove(movieToDeletee);
            tx.commit();
        } catch (Exception e) {
            System.out.println("Une erreur est survenu lors de la modification");
            if (tx != null) {
                tx.rollback();
            }
        }
    }

    public List<Movie> search(String query, int count) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        Query searchQuery = entityManager.createQuery("select m from Movie m where m.name like :query or m.originCountry like :query");
        searchQuery.setParameter("query", "%" + query + "%");
        searchQuery.setMaxResults(count);

        return searchQuery.getResultList();
    }
    
    
}
