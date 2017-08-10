package org.thoth.datasource;

import java.sql.Connection;
import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.DependsOn;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

@Startup
@Singleton
@DependsOn("DataSourceDefinitionConfig")
public class DataSourceDefinitionStartup {
     
    private static final Logger log = Logger.getLogger(DataSourceDefinitionStartup.class);
    
    @Resource(lookup = "java:global/ferris/ds/embedded-filesystem-annotation")
    private DataSource dataSource;
 
    @PostConstruct
    protected void doesTheDataSourceExist() {
        log.info("ENTER #doesTheDataSourceExist()");
        try (Connection connection = dataSource.getConnection()) {
            log.info(
                  "Product name: " + connection.getMetaData().getDatabaseProductName() 
                + ", Product version: " + connection.getMetaData().getDatabaseProductVersion()
                + ", Catalog term: " + connection.getMetaData().getCatalogTerm()
            );
        } catch (Throwable e) {
            log.fatal("oops!", e);
        }
    }
     
}

