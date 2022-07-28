/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.entity;

import java.util.Date;
import java.util.List;
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
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author maxla
 */
@Entity
@Table(name="movie")
public class Movie {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_movie")
    private int id;
    
    @Column(name="name",  length=100)
    private String name;
    
    @Column(name="date", columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    @UpdateTimestamp
    private Date date;
    
    @Column(name="duration")
    private int duration;
    
    @Column(name="rating")
    private float rating;
    @Column(name="synopsis", columnDefinition="TEXT")
    private String synopsis;
    
    @Column(name="genre")
    @ManyToMany(fetch = FetchType.LAZY ) 
    @JoinTable(name = "genre",
                joinColumns = @JoinColumn( name = "id_movie" ),
                inverseJoinColumns = @JoinColumn( name = "id_genre" ) )
    private List<Genre> genre;
    
    @Column(name="actors")
    @ManyToMany(fetch = FetchType.LAZY ) 

    @JoinTable(name = "actors_movie",
                joinColumns = @JoinColumn( name = "id_movie" ),
                inverseJoinColumns = @JoinColumn( name = "id_actor" ) )
    private List<Actor> actors;
    
    @Column(name="originCountry")
    private String originCountry;
    
    //@CollectionTable a voir
    @Column(name="languages")
    private List<String> languages;
    
    @Column(name="comments")
    private List<Comment> comments;

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

    public Movie() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
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

    
   
    
   
           
}