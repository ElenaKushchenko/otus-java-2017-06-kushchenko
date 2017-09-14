package ru.otus.kushchenko.hibernate;

import ru.otus.kushchenko.hibernate.db_service.DBServiceImpl;
import ru.otus.kushchenko.hibernate.model.AddressDataSet;
import ru.otus.kushchenko.hibernate.model.PhoneDataSet;
import ru.otus.kushchenko.hibernate.model.UserDataSet;
import ru.otus.kushchenko.hibernate.db_service.DBService;
import ru.otus.kushchenko.hibernate.db_service.HibernateDBService;

import java.util.Arrays;

public class Main {

    public static void main(String... args) {
//        try (DBService dbService = new DBServiceImpl()) {
        try (DBService dbService = new HibernateDBService()) {
            AddressDataSet address = new AddressDataSet("Street");
            PhoneDataSet phone1 = new PhoneDataSet("111");
            PhoneDataSet phone2 = new PhoneDataSet("222");

            UserDataSet user = new UserDataSet("Username", 40, address, Arrays.asList(phone1, phone2));
            long id = dbService.save(user);

            System.out.println(dbService.load(id));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
