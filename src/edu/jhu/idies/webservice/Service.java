package edu.jhu.idies.webservice;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Service {
    private static final Logger LOG = LogManager.getLogger(Service.class);
    
    public static void main(String[] args) throws Exception {
        JettyServer server = new JettyServer(Integer.parseInt(args[0]));
        server.start();     
        server.join(); 
    }
}
