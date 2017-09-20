package ru.otus.kushchenko.jetty.servlet;

import ru.otus.kushchenko.jetty.model.UserDataSet;
import ru.otus.kushchenko.jetty.model.enums.Role;
import ru.otus.kushchenko.jetty.servlet.util.ServletUtil;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AdminFilter implements Filter {
    private static final String LOGIN_ATTRIBUTE = "user";


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = ((HttpServletRequest) request);
        HttpServletResponse httpResponse = ((HttpServletResponse) response);

        UserDataSet user = (UserDataSet) ServletUtil.getFromSession(httpRequest, LOGIN_ATTRIBUTE);

        if (user == null) {
            ServletUtil.setStatus(httpResponse, HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        if (!Role.ADMIN.equals(user.getRole())) {
            ServletUtil.setStatus(httpResponse, HttpServletResponse.SC_FORBIDDEN);
            return;
        }

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
