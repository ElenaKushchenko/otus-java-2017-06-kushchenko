package ru.otus.kushchenko.ms.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.otus.kushchenko.ms.messageSystem.addressee.AddressedFrontendService;
import ru.otus.kushchenko.ms.servlet.util.ServletUtil;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configurable
public class UserServlet extends HttpServlet {

    @Autowired
    private AddressedFrontendService frontendService;


    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String id = request.getParameter("id");

        ServletUtil.setBody(response, frontendService.getUserById(Long.valueOf(id)));
    }
}