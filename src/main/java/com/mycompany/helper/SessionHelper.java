package com.mycompany.helper;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class SessionHelper {
    
    private static EntityManager entityManager;
    
    public static EntityManager getEntityManager() {
        if (entityManager == null) {
            EntityManagerFactory emf = Persistence.createEntityManagerFactory("bdd_imdb");
            entityManager = emf.createEntityManager();
        }
        return entityManager;
    }
}
