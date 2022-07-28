package com.mycompany.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import com.mycompany.entity.Movie;

@XmlRootElement(name = "actor")
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idActor;

    @Column(length = 100)
    private String firstname;

    @Column(length = 100)
    private String lastname;

    @Column(length = 100)
    private String birthDate;

    @Column(name = "movies")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "actors_movie",
            joinColumns = @JoinColumn(name = "id_actor"),
            inverseJoinColumns = @JoinColumn(name = "id_movie"))
    private List<Movie> Movies;

    public Actor() {
    }

    public Actor(String firstname, String lastname, String birthDate, List<Movie> Movies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.Movies = Movies;
    }

    public int getIdActor() {
        return idActor;
    }

    public void setIdActor(int idActor) {
        this.idActor = idActor;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }

    public List<Movie> getMovies() {
        return Movies;
    }

    public void setMovies(List<Movie> Movies) {
        this.Movies = Movies;
    }

   public void copy(Actor data) {
        if (data.getFirstname() != null) {
            this.firstname = data.getFirstname();
        }
        if (data.getLastname() != null) {
            this.lastname = data.getLastname();
        }
        if (data.getBirthDate() != null) {
           this.birthDate = data.getBirthDate();
        }
        if (data.getMovies() != null) {
           this.Movies = data.getMovies();
        }
    }
}
