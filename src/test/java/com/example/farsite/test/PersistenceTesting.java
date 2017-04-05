package com.example.farsite.test;

import java.util.HashMap;
import java.util.Map;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.eclipse.persistence.config.PersistenceUnitProperties;

/**
 * Persistence testing helper which creates an EMF providing testing overrides
 * to use direct JDBC instead of a data source
 */
public class PersistenceTesting {

    public static EntityManagerFactory createEMF(boolean replaceTables) {
        Map<String, Object> props = new HashMap<String, Object>();

        // Ensure the persistence.xml provided data source are ignored for Java
        // SE testing
        props.put(PersistenceUnitProperties.NON_JTA_DATASOURCE, "");
        props.put(PersistenceUnitProperties.JTA_DATASOURCE, "");
        props.put(PersistenceUnitProperties.TRANSACTION_TYPE, "RESOURCE_LOCAL");

        // Configure the use of embedded derby for the tests allowing system
        // properties of the same name to override
        if (true) {
            setProperty(props, PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
            setProperty(props, PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://127.0.0.1:26257/farsite");
            setProperty(props, PersistenceUnitProperties.JDBC_USER, "root");
            setProperty(props, PersistenceUnitProperties.JDBC_PASSWORD, "");
        } else {
            setProperty(props, PersistenceUnitProperties.JDBC_DRIVER, "org.postgresql.Driver");
            setProperty(props, PersistenceUnitProperties.JDBC_URL, "jdbc:postgresql://127.0.0.1:5432/farsite");
            setProperty(props, PersistenceUnitProperties.JDBC_USER, "postgres");
            setProperty(props, PersistenceUnitProperties.JDBC_PASSWORD, "postgres");
        }

        // Ensure weaving is used
        props.put(PersistenceUnitProperties.WEAVING, "false"); // TODO(gburd): true, -javaagent:toplink-essentials-agent.jar

        if (replaceTables) {
            props.put(PersistenceUnitProperties.DDL_GENERATION, PersistenceUnitProperties.DROP_AND_CREATE);
            props.put(PersistenceUnitProperties.DDL_GENERATION_MODE, PersistenceUnitProperties.DDL_DATABASE_GENERATION);
        }

        return Persistence.createEntityManagerFactory("employee", props);
    }

    /**
     * Add the system property value if it exists, otherwise use the default
     * value.
     */
    private static void setProperty(Map<String, Object> props, String key, String defaultValue) {
        String value = defaultValue;
        if (System.getProperties().containsKey(key)) {
            value = System.getProperty(key);
        }
        props.put(key, value);
    }

}
