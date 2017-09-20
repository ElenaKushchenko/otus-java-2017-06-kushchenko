package ru.otus.kushchenko.cache.persistence;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import ru.otus.kushchenko.cache.persistence.connection.ConnectionHelper;
import ru.otus.kushchenko.cache.model.UserDataSet;
import ru.otus.kushchenko.cache.model.AddressDataSet;
import ru.otus.kushchenko.cache.model.PhoneDataSet;
import ru.otus.kushchenko.cache.persistence.dao.HibernateUserDAO;

import java.util.Properties;
import java.util.function.Function;

@SuppressWarnings("Duplicates")
public class HibernateDBService implements DBService {
    private final SessionFactory sessionFactory;


    public HibernateDBService() {
        Configuration configuration = createConfiguration();
        sessionFactory = createSessionFactory(configuration);
    }

    public HibernateDBService(Configuration configuration) {
        sessionFactory = createSessionFactory(configuration);
    }


    @Override
    public long save(UserDataSet user) {
        return runInSession(session -> {
            HibernateUserDAO dao = new HibernateUserDAO(session);
            return dao.save(user);
        });
    }

    @Override
    public UserDataSet get(long id) {
        return runInSession(session -> {
            HibernateUserDAO dao = new HibernateUserDAO(session);
            return dao.get(id);
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

    private Configuration createConfiguration() {
        Configuration configuration = new Configuration();

        Properties properties = ConnectionHelper.getConnectionProperties();

        configuration.setProperty("hibernate.dialect", properties.getProperty("dialect"));
        configuration.setProperty("hibernate.connection.driver_class", properties.getProperty("driverName"));
        configuration.setProperty("hibernate.connection.url", properties.getProperty("url"));
        configuration.setProperty("hibernate.connection.username", properties.getProperty("username"));
        configuration.setProperty("hibernate.connection.password", properties.getProperty("password"));

        configuration.setProperty("hibernate.show_sql", "false");
        configuration.setProperty("hibernate.hbm2ddl.auto", "validate");
        configuration.setProperty("hibernate.enable_lazy_load_no_trans", "true");

        configuration.addAnnotatedClass(UserDataSet.class);
        configuration.addAnnotatedClass(PhoneDataSet.class);
        configuration.addAnnotatedClass(AddressDataSet.class);

        return configuration;
    }

    private SessionFactory createSessionFactory(Configuration configuration) {
        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(configuration.getProperties());
        ServiceRegistry serviceRegistry = builder.build();

        return configuration.buildSessionFactory(serviceRegistry);
    }
}
