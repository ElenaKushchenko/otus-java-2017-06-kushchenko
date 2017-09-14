package ru.otus.kushchenko.cache.db_service;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.kushchenko.cache.connection.ConnectionHelper;
import ru.otus.kushchenko.cache.model.UserDataSet;
import ru.otus.kushchenko.cache.model.AddressDataSet;
import ru.otus.kushchenko.cache.model.PhoneDataSet;
import ru.otus.kushchenko.cache.db_service.dao.HibernateUserDAO;

import java.util.Properties;
import java.util.function.Function;

public class HibernateDBService implements DBService {
    private final SessionFactory sessionFactory;


    public HibernateDBService() {
        Configuration configuration = new Configuration();
        Properties connectionProps = ConnectionHelper.getProperties();

        configuration.setProperty("hibernate.dialect", connectionProps.getProperty("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", connectionProps.getProperty("driverName"));
        configuration.setProperty("hibernate.connection.url", connectionProps.getProperty("url"));
        configuration.setProperty("hibernate.connection.username", connectionProps.getProperty("username"));
        configuration.setProperty("hibernate.connection.password", connectionProps.getProperty("password"));

        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        sessionFactory = createSessionFactory(configuration);
    }

    public HibernateDBService(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }


    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }

    @Override
    public long save(UserDataSet user) {
        return runInSession(session -> {
            HibernateUserDAO dao = new HibernateUserDAO(session);
            return dao.save(user);
        });
    }

    @Override
    public UserDataSet load(long id) {
        return runInSession(session -> {
            HibernateUserDAO dao = new HibernateUserDAO(session);
            return dao.load(id);
        });
    }

    @SuppressWarnings("Duplicates")
    private <R> R runInSession(Function<Session, R> function) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            R result = function.apply(session);
            transaction.commit();
            return result;
        }
    }

    @Override
    public void close() throws Exception {
        sessionFactory.close();
    }
}
