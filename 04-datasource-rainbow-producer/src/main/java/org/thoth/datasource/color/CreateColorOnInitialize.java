package org.thoth.datasource.color;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
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
public class CreateColorOnInitialize implements ServletContextListener {

    private static final Logger log = Logger.getLogger(CreateColorOnInitialize.class);

    @Resource(lookup = "java:global/ferris/jdbc/RainbowDS")
    protected DataSource ds;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        log.info("contextInitialized ");
        try (Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();) {

            log.info("Creating COLOR table");
            stmt.executeUpdate(
                " create table color "
                + " ( "
                + "   id integer not null "
                + "  ,code varchar(1) not null "
                + "  ,name varchar(10) not null "
                + "  ,created_on timestamp not null "
                + " ) "
            );

            PreparedStatement insert = conn.prepareStatement(
                " insert into color "
                + " ( "
                + "   id "
                + "  ,code "
                + "  ,name "
                + "  ,created_on "
                + " ) "
                + " values "
                + " ( "
                + "  ? "
                + "  ,? "
                + "  ,? "
                + "  ,? "
                + " ) "
            );

            String[] codes = {"r", "o", "y", "g", "b", "i", "v"};
            String[] names = {"red", "orage", "yellow", "green", "blue", "indigo", "violet"};

            for (int i = 0; i < codes.length; i++) {
                insert.setInt(1, i + 1);
                insert.setString(2, codes[i]);
                insert.setString(3, names[i]);
                insert.setTimestamp(4, new Timestamp(System.currentTimeMillis()));
                insert.addBatch();
            }

            log.info("Loading COLOR data");
            insert.executeBatch();
            insert.close();
            insert = null;

            log.info("DONE");
        } catch (Throwable t) {
            log.info("oops!", t);
        }
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        log.info("contextDestroyed ");
    }
}
