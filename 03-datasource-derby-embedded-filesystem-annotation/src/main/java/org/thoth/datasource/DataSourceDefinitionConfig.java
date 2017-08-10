package org.thoth.datasource;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.naming.InitialContext;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */

@DataSourceDefinition(
    description = "Reference: https://webapps4newbies.blogspot.com/2015/04/creating-datasource-definition-using.html",
    name = "java:global/ferris/ds/embedded-filesystem-annotation",
    className = "org.apache.derby.jdbc.EmbeddedDataSource",
    user="sa",
    password="sa",
    databaseName="C:/Temp/EmbeddedFileSystemAnnotionDB",
    properties = {"connectionAttributes=;create=true"}
)
@Startup
@Singleton
public class DataSourceDefinitionConfig {

    private static final Logger log = Logger.getLogger(DataSourceDefinitionConfig.class);
        
    @PostConstruct
    protected void iThinkTheDataSourceWasCreated() {
        log.info("ENTER #iThinkTheDataSourceWasCreated() - Maybe?");
        try {
            InitialContext c = new InitialContext();
            Object o = 
                c.lookup("java:global/ferris/ds/embedded-inmemory-annotation");
            log.info("Object lookup is: " + String.valueOf(o));
            DataSource ds = (DataSource)o;
            Connection conn = ds.getConnection();
            DatabaseMetaData md = conn.getMetaData();
            log.info("Product name: " + md.getDatabaseProductName());
            
        } catch (Exception e) {
            log.info("oops", e);
        }
    }
}
