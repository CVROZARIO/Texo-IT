package com.texoit.movies.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity(name = "Producer")
@Table(name = "producer")
public class Producer implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length = 100, nullable = false, unique = true)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, mappedBy = "producers", fetch = FetchType.EAGER)
    @JsonIgnore
    @Where(clause = "isWinner=true")
    @OrderBy("year ASC")
    private List<Movie> movieWinners = new ArrayList<>();

    public Producer() {
        super();
    }

    public Producer(String name) {
        super();
        this.name = name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Movie> getMovieWinners() {
        return movieWinners;
    }

    public void setMovieWinners(List<Movie> movies) {
        this.movieWinners = movies;
    }
}
