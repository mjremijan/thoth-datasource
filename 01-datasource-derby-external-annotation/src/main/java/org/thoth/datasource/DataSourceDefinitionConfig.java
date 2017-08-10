package org.thoth.datasource;

import javax.annotation.PostConstruct;
import javax.annotation.sql.DataSourceDefinition;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@DataSourceDefinition(
    name = "java:global/ferris/ds/external-annotation",
    className = "org.apache.derby.jdbc.ClientDataSource",
    databaseName = "ExternalDB",
    password = "sa",
    portNumber = 1110,
    serverName = "localhost",
    user = "sa"
)
@Singleton
@Startup
public class DataSourceDefinitionConfig {

    private static final Logger log = Logger.getLogger(DataSourceDefinitionConfig.class);
        
    @PostConstruct
    protected void iThinkTheDataSourceWasCreated() {
        log.info("ENTER #iThinkTheDataSourceWasCreated()");
    }
}
