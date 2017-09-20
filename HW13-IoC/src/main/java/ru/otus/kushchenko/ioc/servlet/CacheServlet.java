package ru.otus.kushchenko.ioc.servlet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import ru.otus.kushchenko.ioc.persistence.CachedDBService;
import ru.otus.kushchenko.ioc.servlet.util.ServletUtil;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Configurable
public class CacheServlet extends HttpServlet {
    private CachedDBService dbService;


    @Autowired
    public CacheServlet(CachedDBService dbService) {
        this.dbService = dbService;
    }

    public CacheServlet() {
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletUtil.setBody(response, dbService.getCacheInfo());
    }
}
