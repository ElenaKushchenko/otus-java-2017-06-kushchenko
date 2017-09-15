package ru.otus.kushchenko.jetty.persistence.dao;

import lombok.AllArgsConstructor;
import org.hibernate.Session;
import ru.otus.kushchenko.jetty.model.UserDataSet;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

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

    @Override
    public UserDataSet loadByName(String name) {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        Root<UserDataSet> from = criteria.from(UserDataSet.class);
        criteria.where(builder.equal(from.get("name"), name));

        return session.createQuery(criteria).uniqueResult();
    }

    @Override
    public List<UserDataSet> loadAll() {
        CriteriaBuilder builder = session.getCriteriaBuilder();
        CriteriaQuery<UserDataSet> criteria = builder.createQuery(UserDataSet.class);
        criteria.from(UserDataSet.class);

        return session.createQuery(criteria).list();
    }
}
