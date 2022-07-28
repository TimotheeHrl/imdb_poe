package com.mycompany.entity;

import java.util.Date;
import javax.persistence.CascadeType;
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

@XmlRootElement(name = "comment")
@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int idComment;
    
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Column(name = "content", length = 250)
    private String content;
    
    @ManyToOne(cascade = {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.REMOVE}, fetch = FetchType.LAZY)
    @JoinColumn(name = "id_movie", nullable = false)
    private Movie movie;
    
    @Column(name = "creation_date", length = 100)
    private String creationDate;

    public Comment() {
        
    }

    public Comment(User user, String content, Movie movie, String creationDate) {
        this.user = user;
        this.content = content;
        this.movie = movie;
        this.creationDate = creationDate;
    }

    public int getId() {
        return idComment;
    }

    public User getUser() {
        return user;
    }

    public String getContent() {
        return content;
    }

    public Movie getMovie() {
        return movie;
    }

    public String getCreationDate() {
        return creationDate;
    }

    public void setId(int id) {
        this.idComment = id;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public void setCreationDate(String creationDate) {
        this.creationDate = creationDate;
    }
    
    public void copy(Comment data) {
        if (data.getContent() != null) {
            this.content = data.getContent();
        }
        if (data.getMovie() != null) {
            this.movie = data.getMovie();
        }
        if (data.getCreationDate() != null) {
           this.creationDate = data.getCreationDate();
        }
        if (data.getUser() != null) {
           this.user = data.getUser();
        }
    }
}
