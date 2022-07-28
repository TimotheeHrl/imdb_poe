/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author maxla
 */
@Entity
@Table(name = "movies")
@XmlRootElement(name = "movie")
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_movie")
    private int idMovie;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date date;

    @Column(name = "duration")
    private Integer duration;

    @Column(name = "rating")
    private Float rating;
    @Column(name = "synopsis", columnDefinition = "TEXT")
    private String synopsis;

    @Column(name = "genre")
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "genre",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_genre"))
    private List<Genre> genre;

    
    @Column(name = "actors")
    @ManyToMany(fetch = FetchType.LAZY)

    @JoinTable(name = "actors_movie",
            joinColumns = @JoinColumn(name = "id_movie"),
            inverseJoinColumns = @JoinColumn(name = "id_actor"))
    private List<Actor> actors;

    @Column(name = "originCountry")
    private String originCountry;

    //@CollectionTable a voir
    @Column(name = "languages")
    private List<String> languages;

    @OneToMany(orphanRemoval = true, cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, mappedBy = "idComment", fetch = FetchType.LAZY)
    @Column(name = "comments")
    private List<Comment> comments;

    public Movie() {
    }

    public Movie(String name, Date date, int duration, float rating, String synopsis, List<Genre> genre, List<Actor> actors, String originCountry, List<String> languages, List<Comment> comments) {
        this.name = name;
        this.date = date;
        this.duration = duration;
        this.rating = rating;
        this.synopsis = synopsis;
        this.genre = genre;
        this.actors = actors;
        this.originCountry = originCountry;
        this.languages = languages;
        this.comments = comments;
    }

    public int getIdMovie() {
        return idMovie;
    }

    public void setIdMovie(int idMovie) {
        this.idMovie = idMovie;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Float getRating() {
        return rating;
    }

    public void setRating(Float rating) {
        this.rating = rating;
    }

    public String getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String synopsis) {
        this.synopsis = synopsis;
    }

    public List<Genre> getGenre() {
        return genre;
    }

    public void setGenre(List<Genre> genre) {
        this.genre = genre;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    public String getOriginCountry() {
        return originCountry;
    }

    public void setOriginCountry(String originCountry) {
        this.originCountry = originCountry;
    }

    public List<String> getLanguages() {
        return languages;
    }

    public void setLanguages(List<String> languages) {
        this.languages = languages;
    }

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public void copy(Movie data) {
        if (data.getName() != null) {
            this.name = data.getName();
        }
        if (data.getDate() != null) {
            this.date = data.getDate();
        }
        if (data.getDuration() != null) {
            this.duration = data.getDuration();
        }
        if (data.getRating() != null) {
            this.rating = data.getRating();
        }
        if (data.getSynopsis() != null) {
            this.synopsis = data.getSynopsis();
        }
        if (data.getGenre() != null) {
            this.genre = data.getGenre();
        }
        if (data.getActors() != null) {
            this.actors = data.getActors();
        }
        if (data.getOriginCountry() != null) {
            this.originCountry = data.getOriginCountry();
        }
        if (data.getLanguages() != null) {
            this.languages = data.getLanguages();
        }
        if (data.getComments() != null) {
            this.comments = data.getComments();
        }
    }
}
