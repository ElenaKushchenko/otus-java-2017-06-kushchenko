package ru.otus.kushchenko.jetty.servlet;

import ru.otus.kushchenko.jetty.persistence.DBService;
import ru.otus.kushchenko.jetty.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserServlet extends HttpServlet {
    private final DBService dbService;


    public UserServlet(DBService dbService) {
        this.dbService = dbService;
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        ServletUtil.setBody(response, dbService.get(Long.valueOf(id)));
    }
}