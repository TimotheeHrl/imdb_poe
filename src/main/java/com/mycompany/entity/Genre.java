package com.mycompany.entity;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.Objects;

@Entity
@Table(name = "genres")
@XmlRootElement(name = "genre")
public class Genre {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int idGenre;

    @Column(name = "name", length = 50)
    private String name;

    public Genre() {
    }

    public Genre(String name) {
        this.name = name;
    }

    public int getIdGenre() {
        return idGenre;
    }

    public void setIdGenre(int idGenre) {
        this.idGenre = idGenre;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

   
    
    
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Genre)) return false;
        Genre genre = (Genre) o;
        return getIdGenre() == genre.getIdGenre();
    }

    @Override
    public int hashCode() {
        return Objects.hash(getIdGenre());
    }

    @Override
    public String toString() {
        return "Genre{" +
                "id=" + idGenre +
                ", name='" + name + '\'' +
                '}';
    }
    
        public void copy(Genre data) {
        if (data.getName() != null) {
            this.name = data.getName();
        }
    }
}
