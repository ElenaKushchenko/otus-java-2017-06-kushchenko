package ru.otus;

import ru.otus.model.AddressDataSet;
import ru.otus.model.PhoneDataSet;
import ru.otus.model.UserDataSet;
import ru.otus.service.DBService;
import ru.otus.service.DBServiceHibernateImpl;
import ru.otus.service.DBServiceImpl;

import java.util.Arrays;

public class Main {

    public static void main(String... args) {
//        try (DBService dbService = new DBServiceImpl()) {
        try (DBService dbService = new DBServiceHibernateImpl()) {
            AddressDataSet address = new AddressDataSet("Street");
            PhoneDataSet phone1 = new PhoneDataSet("111");
            PhoneDataSet phone2 = new PhoneDataSet("222");

            UserDataSet user = new UserDataSet("Test", 20, address, Arrays.asList(phone1, phone2));
            dbService.save(user);

            System.out.println(dbService.load(1L));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
