package com.texoit.movies.services;

import com.texoit.movies.entities.DataError;
import com.texoit.movies.entities.Movie;
import com.texoit.movies.entities.Producer;
import com.texoit.movies.entities.Studio;
import com.texoit.movies.utils.Consts;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.junit.platform.commons.util.StringUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Objects;
import java.util.Scanner;
import java.util.Set;

@Service
public class DbServiceImpl implements DbService {

    private final SessionFactory sessionFactory;

    public DbServiceImpl(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
        InitializeDB();
    }

    private void InitializeDB() {

        Session session = getCurrentSession();

        try {
            String[] strsTmp;
            Set<Studio> studiosTmp;
            Set<Producer> producersTmp;
            Movie movieTmp;

            Query queryTmp;

            String scannerRow;
            String[] scannerRowArray;

            Scanner scanner = new Scanner(
                    Objects.requireNonNull(
                            ClassLoader.getSystemClassLoader().getResourceAsStream(Consts.CfgDataBaseCsvFileName)
                    )
            );
            scanner.useDelimiter(Consts.CfgCsvDelimiter);
            scanner.nextLine();

            while (scanner.hasNext()) {
                try {

                    session.getTransaction().begin();

                    scannerRow = scanner.nextLine();
                    scannerRowArray = scannerRow.split(";");

                    movieTmp = new Movie();

                    movieTmp.setYear(Integer.parseInt(scannerRowArray[0]));
                    movieTmp.setTitle(scannerRowArray[1]);
                    if (scannerRowArray.length >= 5) {
                        movieTmp.setWinner(scannerRowArray[4]);
                    }

                    session.persist(movieTmp);

                    studiosTmp = new HashSet<>();
                    strsTmp = scannerRowArray[2].split(",");
                    for (String str : strsTmp) {
                        if (StringUtils.isNotBlank(str = str.trim())) {
                            queryTmp = session.createQuery("from Studio where name = :name");
                            queryTmp.setParameter("name", str);
                            studiosTmp.add(queryTmp.stream().count() > 0
                                    ? (Studio) queryTmp.list().get(0) : new Studio(str));
                        }
                    }
                    movieTmp.setStudios(studiosTmp);

                    producersTmp = new HashSet<>();
                    strsTmp = scannerRowArray[3]
                            .replace(" and ", ",")// Gambiarra mas parece ser isso o requisito...
                            .split(",");
                    for (String str : strsTmp) {
                        if (StringUtils.isNotBlank(str = str.trim())) {
                            queryTmp = session.createQuery("from Producer where name = :name");
                            queryTmp.setParameter("name", str);
                            producersTmp.add(queryTmp.stream().count() > 0
                                    ? (Producer) queryTmp.list().get(0) : new Producer(str));
                        }
                    }
                    movieTmp.setProducers(producersTmp);

                    session.getTransaction().commit();

                } catch (Exception scannerRowException) {
                    session.getTransaction().rollback();
                    session.save(new DataError(scannerRowException.getMessage()));
                }
            }

            scanner.close();

        } catch (Exception exception) {
            session.save(new DataError(exception.getMessage()));
        }

    }

    public Session getCurrentSession() {
        try {
            return sessionFactory.getCurrentSession();
        } catch (HibernateException e) {
            return sessionFactory.openSession();
        }
    }

}
