package org.thoth.datasource;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import org.apache.log4j.Logger;

@WebListener
public class ServletContextListenerConfig implements ServletContextListener {

    private static final Logger log = Logger.getLogger(ServletContextListenerConfig.class);

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed ");
    }
}
