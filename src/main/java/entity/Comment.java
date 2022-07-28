package entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "comments")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private int id;
    
    @Column(name = "user", length = 100)
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;
    
    @Column(name = "content", length = 250)
    private String content;
    
    @Column(name = "movie", length = 100)
    @ManyToOne(fetch = FetchType.LAZY)
    private Movie movie;
    
    @Column(name = "creation_date", length = 100)
    private Date creationDate;

    public Comment() {
        
    }

    public Comment(User user, String content, Movie movie, Date creationDate) {
        this.user = user;
        this.content = content;
        this.movie = movie;
        this.creationDate = creationDate;
    }

    public int getId() {
        return id;
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

    public Date getCreationDate() {
        return creationDate;
    }

    public void setId(int id) {
        this.id = id;
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

    public void setCreationDate(Date creationDate) {
        this.creationDate = creationDate;
    }
    

}
