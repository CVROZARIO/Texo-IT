package com.texoit.movies.services;

import org.hibernate.Session;

public interface DbService {
    Session getCurrentSession();
}
