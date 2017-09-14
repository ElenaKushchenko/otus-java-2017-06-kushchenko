package ru.otus.model.dao.hibernate;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.model.UserDataSet;
import ru.otus.model.dao.UserDAO;

@AllArgsConstructor

public class HibernateUserDAO implements UserDAO {
    private final Session session;


    @Override
    public void save(UserDataSet user) {
        session.save(user);
    }

    @Override
    public UserDataSet load(long id) {
        return session.load(UserDataSet.class, id);
    }
}
