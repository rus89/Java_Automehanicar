/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package komunikacija;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 *
 * @author Rus
 */
public class Util {

    private static Util instance;
    private Properties properties;

    private Util() throws IOException {
        properties = new Properties();
        properties.load(new FileInputStream("db.properties"));
    }

    public static Util vratiUtilObjekat() throws IOException {
        if (instance == null) {
            instance = new Util();
        }
        return instance;
    }

    public String vratiUrl() {
        String currentDb = properties.getProperty("Current_db");
        return properties.getProperty(currentDb + "_url");
    }

    public String vratiDriver() {
        String currentDb = properties.getProperty("Current_db");
        return properties.getProperty(currentDb + "_driver");
    }

    public String vratiUserName() {
        String currentDb = properties.getProperty("Current_db");
        return properties.getProperty(currentDb + "_user");
    }

    public String vratiPassword() {
        String currentDb = properties.getProperty("Current_db");
        return properties.getProperty(currentDb + "_password");
    }
}
