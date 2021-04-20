package com.texoit.movies.controller;

import com.texoit.movies.entities.MinMaxComparison;
import com.texoit.movies.entities.SimpleKeyValue;
import com.texoit.movies.services.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
public class MovieController {

    @Autowired
    private MovieService movieService;

    @RequestMapping("/")
    public List getIndex() {
        List<SimpleKeyValue> pairs = new ArrayList<>();
        pairs.add(new SimpleKeyValue("/Awards-Best-and-Worst", "REQ: Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais rápido."));
        pairs.add(new SimpleKeyValue("/data-errors", "LISTA DE ERROS DE INTERPRETAÇÃO DE DADOS CSV (DB)"));
        pairs.add(new SimpleKeyValue("/movies", "LISTA ENTIDADES MOVIE"));
        pairs.add(new SimpleKeyValue("/movies/count", "TOTAL ENTIDADES MOVIE"));
        pairs.add(new SimpleKeyValue("/movies/year-{year}", "PESQUISA PROP YEAR ENTIDADES MOVIE"));
        pairs.add(new SimpleKeyValue("/movies/winners", "LISTA ENTIDADES MOVIE QUANDO PROP WINNER = TRUE"));
        pairs.add(new SimpleKeyValue("/movies/winners/count", "TOTAL ENTIDADES MOVIE WINNER = TRUE"));
        pairs.add(new SimpleKeyValue("/movies/winners/year-{year}", "PESQUISA PROP YEAR ENTIDADES MOVIE WINNER = TRUE"));
        pairs.add(new SimpleKeyValue("/studios", "LISTA ENTIDADES STUDIO"));
        pairs.add(new SimpleKeyValue("/studios/count", "TOTAL ENTIDADES STUDIO"));
        pairs.add(new SimpleKeyValue("/producers", "LISTA ENTIDADES PRODUCERS"));
        pairs.add(new SimpleKeyValue("/producers/count", "TOTAL ENTIDADES PRODUCERS"));
        return pairs;
    }

    @GetMapping("/Awards-Best-and-Worst")
    public MinMaxComparison getAwardsBestAndWorst() {
        return movieService.getAwardsBestAndWorst();
    }

    @GetMapping("/data-errors")
    public List getDataErrors() {
        return movieService.getDataErrors();
    }

    @GetMapping("/movies")
    public List getMovies() {
        return movieService.getMovies();
    }

    @GetMapping("/movies/count")
    public long getMoviesCount() {
        return movieService.getMoviesCount();
    }

    @GetMapping("/movies/year-{year}")
    public List getMoviesByYear(@PathVariable String year) {
        return movieService.getMoviesByYear(Integer.parseInt(year));
    }

    @GetMapping("/movies/winners")
    public List getMovieWinners() {
        return movieService.getMovieWinners();
    }

    @GetMapping("/movies/winners/count")
    public long getMovieWinnersCount() {
        return movieService.getMovieWinnersCount();
    }

    @GetMapping("/movies/winners/year-{year}")
    public List getMovieWinnersByYear(@PathVariable String year) {
        return movieService.getMovieWinnersByYear(Integer.parseInt(year));
    }

    @GetMapping("/studios")
    public List getStudios() {
        return movieService.getStudios();
    }

    @GetMapping("/studios/count")
    public long getStudiosCount() {
        return movieService.getStudiosCount();
    }

    @GetMapping("/producers")
    public List getProducers() {
        return movieService.getProducers();
    }

    @GetMapping("/producers/count")
    public long getProducersCount() {
        return movieService.getProducersCount();
    }
}
