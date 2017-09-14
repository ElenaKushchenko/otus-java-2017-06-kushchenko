package ru.otus.kushchenko.cache;

import ru.otus.kushchenko.cache.db_service.CachedDBService;
import ru.otus.kushchenko.cache.db_service.HibernateDBService;
import ru.otus.kushchenko.cache.model.AddressDataSet;
import ru.otus.kushchenko.cache.model.PhoneDataSet;
import ru.otus.kushchenko.cache.model.UserDataSet;
import ru.otus.kushchenko.cache.db_service.DBService;
import ru.otus.kushchenko.cache.db_service.JdbcDBService;

import java.util.Arrays;

public class Main {

    public static void main(String... args) {
        try (DBService dbService = new CachedDBService(new HibernateDBService())) {
//        try (DBService dbService = new HibernateDBService()) {
            AddressDataSet address = new AddressDataSet("Street");
            PhoneDataSet phone1 = new PhoneDataSet("111");
            PhoneDataSet phone2 = new PhoneDataSet("222");

            UserDataSet user = new UserDataSet("Username", 12, address, Arrays.asList(phone1, phone2));
            long id = dbService.save(user);

            System.out.println(dbService.load(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
