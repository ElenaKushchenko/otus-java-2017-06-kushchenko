package ru.otus.kushchenko.jetty.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.otus.kushchenko.jetty.persistence.DBService;
import ru.otus.kushchenko.jetty.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configurable
public class UserServlet extends HttpServlet {
    private DBService dbService;


    @Autowired
    public UserServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public UserServlet() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        ServletUtil.setBody(response, dbService.get(Long.valueOf(id)));
    }
}