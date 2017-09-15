package ru.otus.kushchenko.jetty;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import ru.otus.kushchenko.jetty.servlet.CacheInfoServlet;
import ru.otus.kushchenko.jetty.servlet.LoginServlet;
import ru.otus.kushchenko.jetty.servlet.UserServlet;

public class Main {
    private final static int PORT = 8080;
    private final static String HTML_ROOT = "public_html";


    public static void main(String... args) throws Exception {
        ResourceHandler resourceHandler = new ResourceHandler();
        resourceHandler.setResourceBase(HTML_ROOT);

        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.addServlet(LoginServlet.class, "/login");
        context.addServlet(CacheInfoServlet.class, "/monitoring");
        context.addServlet(UserServlet.class, "/users");

        Server server = new Server(PORT);
        server.setHandler(new HandlerList(resourceHandler, context));
        server.start();
        server.join();
    }
}
