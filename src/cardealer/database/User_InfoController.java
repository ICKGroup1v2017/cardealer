/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import static cardealer.Main.db;
import java.io.Serializable;
import javax.transaction.TransactionRequiredException;
import cardealer.database.exceptions.*;
import cardealer.models.User_info;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
/**
 *
 * @author ahmet
 */
public class User_InfoController {
    
    public boolean create (User_info entity) throws EntityExistsException,
            IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
         
        String create = "INSERT INTO `car`(  `make`, `model`, "
                + "`manufacturingYear`) "
                + "VALUES ( ?,?,?);";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

//                stmt.setString(1, entity.getMake());
//                stmt.setString(2, entity.getModel());
//                stmt.setInt(3, entity.getManufacturingYear());

                res = stmt.executeUpdate()>0;
                db.conn.commit();
            } catch (SQLException ex) {
                if (db.conn != null) {
                    System.err.println(ex.getMessage());
                    System.err.print("Transaction is being rolled back");
                    db.conn.rollback();

                }
            } finally {
                if (stmt != null) {
                    stmt.close();
                }

                db.conn.setAutoCommit(true);
            }

        }
        
        return res;
    }
}
