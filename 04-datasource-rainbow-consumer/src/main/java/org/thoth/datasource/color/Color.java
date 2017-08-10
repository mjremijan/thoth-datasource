package org.thoth.datasource.color;

import java.sql.Timestamp;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
public class Color {
    protected int id;
    protected String code;
    protected String name;
    protected Timestamp createdOn;

    public Color(int id, String code, String name, Timestamp createdOn) {
        this.id = id;
        this.code = code;
        this.name = name;
        this.createdOn = createdOn;
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public Timestamp getCreatedOn() {
        return createdOn;
    }
    
    
}
