package ru.otus.kushchenko.ms.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.model.UserDataSet;
import ru.otus.kushchenko.ms.servlet.util.ServletUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

@Configurable
public class LoginServlet extends HttpServlet {
    private static final String LOGIN_ATTRIBUTE = "user";

    @Autowired
    private AddressedFrontendService frontendService;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
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
        UserDataSet user = frontendService.getUserByName(username);

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