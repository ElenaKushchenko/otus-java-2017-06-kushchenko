package ru.otus.kushchenko.cache;

import ru.otus.kushchenko.cache.persistence.CachedDBService;
import ru.otus.kushchenko.cache.persistence.HibernateDBService;
import ru.otus.kushchenko.cache.model.AddressDataSet;
import ru.otus.kushchenko.cache.model.PhoneDataSet;
import ru.otus.kushchenko.cache.model.UserDataSet;
import ru.otus.kushchenko.cache.persistence.DBService;
import ru.otus.kushchenko.cache.persistence.JdbcDBService;

import java.util.Arrays;
import java.util.List;

public class Main {

    public static void main(String... args) {
//        try (DBService dbService = new CachedDBService(new JdbcDBService())) {
        try (DBService dbService = new CachedDBService(new HibernateDBService())) {
            List<Long> ids = Arrays.asList(1L, 20L, 20L, 34L, 20L, 1L, 17L);
            ids.forEach(dbService::get);

            AddressDataSet address = new AddressDataSet("Test");
            PhoneDataSet phone1 = new PhoneDataSet("111");
            PhoneDataSet phone2 = new PhoneDataSet("222");

            UserDataSet user = new UserDataSet("Cached User", 40, address, Arrays.asList(phone1, phone2));
            long id = dbService.save(user);
            System.out.println(dbService.get(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
