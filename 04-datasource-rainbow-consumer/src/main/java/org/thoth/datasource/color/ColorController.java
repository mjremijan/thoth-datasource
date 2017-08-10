package org.thoth.datasource.color;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import javax.annotation.Resource;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import javax.sql.DataSource;
import org.apache.log4j.Logger;

/**
 *
 * @author Michael Remijan mjremijan@yahoo.com @mjremijan
 */
@Named(value = "colorController")
@ApplicationScoped
public class ColorController {
    
    private static final Logger log = Logger.getLogger(ColorController.class);
    
    @Resource(lookup="java:module/env/WillThisWork")
    protected DataSource ds;
    
    
    public int getAllCount() {
        log.info("ENTER!");
        
        int count = -1;
        
        try (
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select count(id) from color ");
        ) {
            if (rs.next()) {
               count = rs.getInt(1);
            }
        } catch (Exception e) {
            log.info("Opps!", e);
        }
        
        return count;
    }
    
    public List<Color> getAll() {
        log.info("ENTER!");
        
        List<Color> colors
            = new LinkedList<>();
        
        try (
            Connection conn = ds.getConnection();
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("select * from color order by id asc");
        ) {
            while (rs.next()) {
                colors.add(
                    new Color(
                          rs.getInt("id")
                        , rs.getString("code")
                        , rs.getString("name")
                        , rs.getTimestamp("created_on")
                    )
                );
            }
        } catch (Exception e) {
            log.info("Opps!", e);
        }
        
        return colors;
    }
}
