package com.texoit.movies.services;

import com.texoit.movies.entities.*;

import java.util.List;

public interface MovieService {
    
    List<DataError> getDataErrors();

    MinMaxComparison getAwardsBestAndWorst();

    List<Movie> getMovies();

    long getMoviesCount();

    List<Movie> getMoviesByYear(int year);

    List<Movie> getMovieWinners();

    long getMovieWinnersCount();

    List<Movie> getMovieWinnersByYear(int year);

    List<Studio> getStudios();

    long getStudiosCount();

    List<Producer> getProducers();

    long getProducersCount();
}
