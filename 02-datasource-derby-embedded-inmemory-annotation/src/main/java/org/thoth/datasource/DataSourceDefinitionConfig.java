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
// REFERENCES
//
// Dontha, D. (Apr 14, 2015). Creating a DataSource Definition Using Annotation.
//  Retrieved from: https://webapps4newbies.blogspot.com/2015/04/creating-datasource-definition-using.html
// 
// Creating an in-memory database. (n.d.). 
//  Retrieved from https://db.apache.org/derby/docs/10.8/devguide/cdevdvlpinmemdb.html
//
@DataSourceDefinition(
    name = "java:global/ferris/ds/embedded-inmemory-annotation",
    className = "org.apache.derby.jdbc.EmbeddedDataSource",
    user="sa",
    password="sa",
    databaseName="memory:FuzzyLogicDB",
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
