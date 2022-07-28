/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.jpa;

import com.mycompany.helper.SessionHelper;
import javax.persistence.EntityManager;

/**
 *
 * @author maxla
 */
public class JpaHibernate {
    
    public static void main(String[] args) {
        EntityManager entityManager = SessionHelper.getEntityManager();
        
        entityManager.close();
    }

}
