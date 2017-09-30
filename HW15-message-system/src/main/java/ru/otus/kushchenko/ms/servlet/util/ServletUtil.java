package ru.otus.kushchenko.ms.servlet.util;

import com.google.gson.Gson;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {

    public static void saveToSession(HttpServletRequest request, String attribute, Object object) {
        request.getSession().setAttribute(attribute, object);
    }

    public static Object getFromSession(HttpServletRequest request, String attribute) {
        return request.getSession().getAttribute(attribute);
    }

    public static void removeFromSession(HttpServletRequest request, String attribute) {
        request.getSession().removeAttribute(attribute);
    }

    public static void setStatus(HttpServletResponse response, int sc) {
        response.setContentType("text/html;charset=utf-8");
        response.setStatus(sc);
    }

    public static void setBody(HttpServletResponse response, Object object) throws IOException {
        String json = new Gson().toJson(object);

        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(json);
    }
}
