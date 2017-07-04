/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer;

import cardealer.database.Database;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.SQLException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ahmet
 */
public class Main {

    private static Properties props;
    public static Database db;

    public static void main(String[] args) {
        /* Create and display the form */

        try {
            Path workingDirectory = Paths.get("").toAbsolutePath();
            String path = workingDirectory + "\\build\\classes\\cardealer\\config\\config.ini";
            final String dir = System.getProperty("user.dir");

            File f = new File(path);
            if (f.exists()) {
                System.out.println("current dir = " + dir);
                props = new Properties();
                props.load(new FileInputStream(path));
                // TODO code application logic here
                System.out.println("Trying to connect");
                Main.db = new Database(props);
                java.awt.EventQueue.invokeLater(SalesForm::new);

            }
        } catch (IOException ex) {

        } catch (SQLException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
