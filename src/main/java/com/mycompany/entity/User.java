package com.mycompany.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "user")
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idUser;

    @Column(name = "lastname", length = 100)
    private String lastname;

    @Column(name = "firstname", length = 100)
    private String firstname;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "role", columnDefinition = "varchar(255) default 'Visitors'")
    private Role role;

    @Column(name = "email", length = 100)
    private String email;

    @Column(name = "password", length = 40)
    private String password;

    public User() {
    }

    public User(String lastname, String firstname, Role role, String email, String password) {
        this.lastname = lastname;
        this.firstname = firstname;
        this.role = role;
        this.email = email;
        this.password = password;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void copy(User data) {
        if (data.getLastname() != null) {
            this.lastname = data.getLastname();
        }
        if (data.getFirstname() != null) {
            this.firstname = data.getFirstname();
        }
        if (data.getEmail() != null) {
            this.email = data.getEmail();
        }
        if (data.getPassword() != null) {
            this.password = data.getPassword();
        }
    }
}
