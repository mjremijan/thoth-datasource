package org.thoth.datasource.drop;

import java.sql.DriverManager;
import javax.annotation.Resource;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@WebListener
public class DropDatabaseOnDestroy implements ServletContextListener {

    private static final Logger log = Logger.getLogger(DropDatabaseOnDestroy.class);
    
    @Resource(lookup="java:global/ferris/jdbc/RainbowDS")
    protected DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized ");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed ");
        
        try {
            log.info("Dropping database RainbowDB");
            DriverManager.getConnection("jdbc:derby:memory:RainbowDB;drop=true");

            log.info("DONE");
        } catch (Throwable t) {
            if ( ! t.getMessage().contains("'memory:RainbowDB' dropped")) {
                log.info("oops!",t);
            }
        }
    }
}
