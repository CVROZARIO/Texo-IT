package com.texoit.movies.services;

import com.texoit.movies.entities.MinMaxComparison;

import java.util.List;

public interface MovieService {
    
    List getDataErrors();

    MinMaxComparison getAwardsBestAndWorst();

    List getMovies();

    long getMoviesCount();

    List getMoviesByYear(int year);

    List getMovieWinners();

    long getMovieWinnersCount();

    List getMovieWinnersByYear(int year);

    List getStudios();

    long getStudiosCount();

    List getProducers();

    long getProducersCount();
}
