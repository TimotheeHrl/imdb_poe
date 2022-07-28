package dao;

import helper.SessionHelper;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.*;
import entity.Actor;
import javassist.NotFoundException;

public class ActorDAO {

    EntityManager em = SessionHelper.getEntityManager();

    public Actor findById(int id) {
        Actor actor = em.find(Actor.class, id);
        return actor;
    }

    public List<Actor> findAll() {
        Query query = em.createQuery("SELECT u FROM Actor u");
        List<Actor> result = query.getResultList();
        return result;
    }

    public void create(Actor actor) {

        EntityTransaction trans = null;
        try {
            trans = em.getTransaction();
            trans.begin();
            em.persist(actor);
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
            throw e;
        }
    }

    public void delete(int id) {
        EntityTransaction trans = null;
        try {
            trans = em.getTransaction();
            trans.begin();
            em.remove(findById(id));
            trans.commit();
        } catch (Exception e) {
            if (trans != null) {
                trans.rollback();
            }
        }
    }

     public void update(int id, Actor actor) throws NotFoundException {
        // On récupère le utilisateur qu'on souhaite modifier
        Actor actorToUpdate = findById(id);

        // Si le utilisateur n'existe pas on ne fait pas d'update
        if (actorToUpdate == null) {
            throw new NotFoundException("L'actor avec l'id:" + id + " n'existe pas");
        }

        actorToUpdate.copy(actor);

        EntityTransaction tx = null;
​
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
            throw e;
        }
    }
    
    
}
