package ru.otus.kushchenko.cache.persistence.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.kushchenko.cache.model.UserDataSet;

@AllArgsConstructor

public class HibernateUserDAO implements UserDAO {
    private final Session session;


    @Override
    public long save(UserDataSet user) {
        return (long) session.save(user);
    }

    @Override
    public UserDataSet get(long id) {
        return session.get(UserDataSet.class, id);
    }
}
