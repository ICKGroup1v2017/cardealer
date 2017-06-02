/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import java.sql.Connection;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;
 
import cardealer.CarForm;

/**
 *
 * @author ahmet
 */
public class Database {

    public Connection conn;

    public Database(Properties _props) throws FileNotFoundException, IOException, SQLException {
        boolean connected = false;
        Properties props = _props;

        try {

            String server = props.getProperty("server");
            String database = props.getProperty("database");
            String user = props.getProperty("user");
            String password = props.getProperty("password");
           
            try {
                // The newInstance() call is a work around for some
                // broken Java implementations

                Class.forName("com.mysql.jdbc.Driver").newInstance();
                try {
                    Class.forName("com.mysql.jdbc.Driver");
                    
                    conn = DriverManager.getConnection("jdbc:mysql://"+server+"/"+database, user, password);
                    
                    

                } catch (ClassNotFoundException | SQLException e) {
                    System.out.println(e.getMessage());
                }
                connected = true;
            } catch (ClassNotFoundException | IllegalAccessException | InstantiationException ex) {
                // handle the error
            }
        } finally {
            if (!connected) {
                System.exit(-23);
            }
        }
    }
}
