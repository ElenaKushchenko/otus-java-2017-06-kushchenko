package ru.otus.kushchenko.hibernate.db_service.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.kushchenko.hibernate.model.UserDataSet;

@AllArgsConstructor

public class HibernateUserDAO implements UserDAO {
    private final Session session;


    @Override
    public long save(UserDataSet user) {
        return (long) session.save(user);
    }

    @Override
    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }
}
