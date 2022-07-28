package entity;

import java.util.Date;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import entity.Movie;

@XmlRootElement(name = "actors")
@Entity
@Table(name = "actors")
public class Actor {

    @Id
    @Column(name = "id_actor")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(length = 100)
    private String firstname;

    @Column(length = 100)
    private String lastname;

    @Column(length = 100)
    private String birthDate;

    @Column(name="movies")
    @ManyToMany(fetch = FetchType.LAZY ) 
    @JoinTable(name = "actors_movie",
                joinColumns = @JoinColumn( name = "id_actor" ),
                inverseJoinColumns = @JoinColumn( name = "id_movie" ) )
    private List<Movie> Movies;

    public Actor() {
    }

    public Actor( String firstname, String lastname, String birthDate, List<Movie> Movies) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.birthDate = birthDate;
        this.Movies = Movies;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    
    
}
