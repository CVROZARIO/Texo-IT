package com.texoit.movies.entities;

import com.texoit.movies.utils.Commons;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity(name = "Movie")
@Table(name = "movie")
public class Movie implements Serializable {
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Movie_Studio", joinColumns = {@JoinColumn(name = "movieId")}, inverseJoinColumns = {@JoinColumn(name = "studioId")})
    protected Set<Studio> studios = new HashSet<>();
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "Movie_Producer", joinColumns = {@JoinColumn(name = "movieId")}, inverseJoinColumns = {@JoinColumn(name = "producerId")})
    protected Set<Producer> producers = new HashSet<>();
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int year;
    @Column(length = 100, nullable = false)
    private String title;
    private boolean isWinner;

    public Movie() {
        super();
    }

    public Movie(int year, String title, String winner) {
        super();
        this.year = year;
        this.title = title;
        this.isWinner = Commons.parseBoolean(winner);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public void setWinner(String winner) {
        this.isWinner = Commons.parseBoolean(winner);
    }

    public Set<Studio> getStudios() {
        return studios;
    }

    public void setStudios(Set<Studio> studios) {
        this.studios = studios;
    }

    public Set<Producer> getProducers() {
        return producers;
    }

    public void setProducers(Set<Producer> producers) {
        this.producers = producers;
    }
}
