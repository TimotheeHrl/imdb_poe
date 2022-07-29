package com.mycompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "role")
@Entity
@Table(name = "roles")
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "identifiant", length = 100)
    private String identifiant;

    @Column(name = "description", length = 100)
    private String description;
    
    public Role() {
    }
    
    public Role(String identifiant, String desciption) {
        this.identifiant = identifiant;
        this.description = desciption;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdentifiant() {
        return identifiant;
    }

    public void setIdentifiant(String identifiant) {
        this.identifiant = identifiant;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String desciption) {
        this.description = desciption;
    }
    
    public void copy(Role data) {
        if (data.getIdentifiant() != null) {
            this.identifiant = data.getIdentifiant();
        }
        if (data.getDescription() != null) {
            this.description = data.getDescription();
        }
    }
}
