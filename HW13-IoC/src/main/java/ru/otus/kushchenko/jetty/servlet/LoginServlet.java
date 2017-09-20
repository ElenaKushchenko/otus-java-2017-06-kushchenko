package ru.otus.kushchenko.jetty.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.otus.kushchenko.jetty.model.UserDataSet;
import ru.otus.kushchenko.jetty.persistence.DBService;
import ru.otus.kushchenko.jetty.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configurable
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_ATTRIBUTE = "user";
    private DBService dbService;


    @Autowired
    public LoginServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public LoginServlet() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        UserDataSet user = (UserDataSet) ServletUtil.getFromSession(request, LOGIN_ATTRIBUTE);

        if (user != null) {
            ServletUtil.setBody(response, user);
        } else {
            ServletUtil.setStatus(response, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        UUID uuid = UUID.nameUUIDFromBytes(password.getBytes());
        UserDataSet user = dbService.getByName(username);

        if (user != null && uuid.equals(user.getPassword())) {
            ServletUtil.saveToSession(request, LOGIN_ATTRIBUTE, user);
            ServletUtil.setBody(response, user);
        } else {
            ServletUtil.setStatus(response, HttpServletResponse.SC_UNAUTHORIZED);
        }
    }

    @Override
    public void doDelete(HttpServletRequest request, HttpServletResponse response) {
        ServletUtil.removeFromSession(request, LOGIN_ATTRIBUTE);
        ServletUtil.setStatus(response, HttpServletResponse.SC_OK);
    }
}