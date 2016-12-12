package edu.jhu.idies.webservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.DefaultServlet;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.servlet.DispatcherServlet;

public class JettyServer {
    private static final Logger LOG = LogManager.getLogger(JettyServer.class);

    private final int port;
    private Server server;

    public JettyServer(int port) {
        this.port = port;
    }
    
    public void start() throws Exception {
        server = new Server(port);
        
        ServletHolder springDispatcher = new ServletHolder(new DispatcherServlet());
        
        springDispatcher.setInitParameter("contextConfigLocation", "classpath*:**/applicationContext.xml");
        
        ServletContextHandler context = new ServletContextHandler();
        context.addServlet(new ServletHolder("default", new DefaultServlet()), "/");
        context.addServlet(springDispatcher, "/*");
        server.setHandler(context);
        server.start();
    }
    
    public void join() throws InterruptedException {
        server.join();
    }
}
