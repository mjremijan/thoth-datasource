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
        log.info("ENTER #iThinkTheDataSourceWasCreated()");
    }
}
