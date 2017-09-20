package ru.otus.kushchenko.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import ru.otus.kushchenko.jetty.persistence.CachedDBService;
import ru.otus.kushchenko.jetty.persistence.HibernateDBService;
import ru.otus.kushchenko.jetty.servlet.AdminFilter;
import ru.otus.kushchenko.jetty.servlet.LoginServlet;
import ru.otus.kushchenko.jetty.servlet.CacheServlet;
import ru.otus.kushchenko.jetty.servlet.UserServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;

public class Main {
    private final static int PORT = 8080;
    private final static String HTML_ROOT = "public_html";


    public static void main(String... args) throws Exception {
        CachedDBService dbService = new CachedDBService(new HibernateDBService());

        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(HTML_ROOT);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(new ServletHolder(new LoginServlet(dbService)), "/login");
        context.addServlet(new ServletHolder(new CacheServlet(dbService)), "/cache");
        context.addServlet(new ServletHolder(new UserServlet(dbService)), "/user");
        context.addFilter(AdminFilter.class, "/cache", EnumSet.of(DispatcherType.REQUEST));
        context.addFilter(AdminFilter.class, "/user", EnumSet.of(DispatcherType.REQUEST));

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }
}
