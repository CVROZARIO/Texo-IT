package com.texoit.movies.services;

import com.texoit.movies.entities.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private DbService dbService;

    public MovieServiceImpl() {
    }

    @Override
    public MinMaxComparison getAwardsBestAndWorst() {

        /*
        REQ: Obter o produtor com maior intervalo entre dois prêmios consecutivos, e o que obteve dois prêmios mais
        rápido.
        - PARA RETORNAR ARRAY PARA CONDIÇÃO 1 E 2, DEVESSE LEVANTAR CRITÉRIOS PARA REGRA DE NEGÓCIO
         */

        MinMaxComparison minMaxComparison = new MinMaxComparison();

        List<Producer> producersObj = this.dbService.getCurrentSession()
                .createQuery("SELECT p FROM Producer p " +
                        " GROUP BY p.id" +
                        " HAVING (SELECT count(p2.id) FROM Producer p2 INNER JOIN p2.movieWinners WHERE p2.id = p.id) > 1", Producer.class)
                .getResultList();

        /*
        LIMITAÇÃO DA BIBLIOTECA, PODERIA SER FEITO UNICAMENTE POR CONSULTA NO BANCO
        RECURSO APLICADO: INTERPRETAÇÃO DE DADOS FORA DO CONTEXTO DO BANCO
         */
        Producer producerTmp;
        MinMaxComparison.ComparisonItem comparisonItemTmp;
        for (Object obj : producersObj) {
            producerTmp = (Producer) obj;
            /*
            ASSUMIMOS QUE A ENTITIDADE ESTA COM SUAS DEVIDAS ANNOTATIONS
            GARANTIMOS QUE A PROPRIEDADE movieWinners ESTA ANOTADA COM ORDERBY ASC
             */
            for (int i = 0; i < producerTmp.getMovieWinners().size() - 1; i++) {
                comparisonItemTmp = new MinMaxComparison.ComparisonItem(
                        producerTmp.getName()
                        , producerTmp.getMovieWinners().get(i).getYear()
                        , producerTmp.getMovieWinners().get(i + 1).getYear());
                if (minMaxComparison.getMin() == null || minMaxComparison.getMin().getInterval() > comparisonItemTmp.getInterval()) {
                    minMaxComparison.setMin(comparisonItemTmp);
                }
                if (minMaxComparison.getMax() == null || minMaxComparison.getMax().getInterval() < comparisonItemTmp.getInterval()) {
                    minMaxComparison.setMax(comparisonItemTmp);
                }
            }
        }

        return minMaxComparison;
    }

    @Override
    public List<DataError> getDataErrors() {
        return doSimpleList("SELECT de FROM DataError de", DataError.class);
    }

    @Override
    public List<Movie> getMovies() {
        return doSimpleList("SELECT m FROM Movie m", Movie.class);
    }

    @Override
    public long getMoviesCount() {
        return doSimpleCount(Movie.class);
    }

    @Override
    public List<Movie> getMoviesByYear(int year) {
        Query<Movie> query = this.dbService.getCurrentSession().createQuery("SELECT m FROM Movie m WHERE m.year = :year", Movie.class);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public List<Movie> getMovieWinners() {
        return doSimpleList("SELECT m FROM Movie m WHERE isWinner = true", Movie.class);
    }

    @Override
    public long getMovieWinnersCount() {
        Session session = this.dbService.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<?> root = criteriaQuery.from(Movie.class);
        criteriaQuery.select(criteriaBuilder.count(root));
        criteriaQuery.where(criteriaBuilder.equal(root.get("isWinner"), true));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

    @Override
    public List<Movie> getMovieWinnersByYear(int year) {
        Query<Movie> query = this.dbService.getCurrentSession().createQuery("SELECT m FROM Movie m WHERE m.isWinner = :isWinner AND m.year = :year", Movie.class);
        query.setParameter("isWinner", true);
        query.setParameter("year", year);
        return query.getResultList();
    }

    @Override
    public List<Studio> getStudios() {
        return doSimpleList("SELECT s FROM Studio s", Studio.class);
    }

    @Override
    public long getStudiosCount() {
        return doSimpleCount(Studio.class);
    }

    @Override
    public List<Producer> getProducers() {
        return doSimpleList("SELECT p FROM Producer p", Producer.class);
    }

    @Override
    public long getProducersCount() {
        return doSimpleCount(Producer.class);
    }

    private <T> List<T> doSimpleList(String sql, Class<T> aClass) {
        return this.dbService.getCurrentSession().createQuery(sql, aClass).getResultList();
    }

    private long doSimpleCount(Class<?> aClass) {
        Session session = this.dbService.getCurrentSession();
        CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
        CriteriaQuery<Long> criteriaQuery = criteriaBuilder.createQuery(Long.class);
        Root<?> root = criteriaQuery.from(aClass);
        criteriaQuery.select(criteriaBuilder.count(root));
        return session.createQuery(criteriaQuery).getSingleResult();
    }

}
