/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import static cardealer.Main.db;
import cardealer.database.exceptions.EntityExistsException;
import cardealer.database.exceptions.PersistenceException;
import cardealer.models.Gender;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.transaction.TransactionRequiredException;

/**
 *
 * @author ahmet
 */
/**
 * This class serves as Entity Facade for the Genders entity
 *
 * @author Ahmet Murati
 */
public class GendersEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entity
     * @param entityGender
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     * @throws java.sql.SQLException
     */
    public boolean create(Gender entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `gender` (`title`,`description`,`status`) VALUES (?, ?, ?);";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {
              stmt.setString(1, entity.getTitle());
              stmt.setString(2, entity.getDescription());
              stmt.setBoolean(3, entity.isStatus());
                res = stmt.executeUpdate() > 0;
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

    public Gender read(int primaryKey) throws IllegalStateException, IllegalArgumentException, SQLException {
        String load = "SELECT c.* FROM gender c WHERE c.idGender = ?;";
        Gender g= new Gender();
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (PreparedStatement stmt = connection.prepareStatement(load)) {
                
                stmt.setInt(1, primaryKey);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    g = new Gender(
                            rs.getInt("idGender"), 
                            rs.getString("title"), 
                            rs.getString("description"), 
                            rs.getBoolean("status"));
                      
                }
            }
        }
        return g;
    }

    public ArrayList<Gender> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Gender> mGenders = new ArrayList<>();
        String loads = "SELECT c.* FROM gender c ;";
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    Gender inst = new Gender(
                            rs.getInt("idGender"), rs.getString("title"), rs.getString("description"), rs.getBoolean("status"));
                    mGenders.add(inst);
                }
            }
        }
        return mGenders;
    }

    public boolean update(Gender entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `gender`"
 + "SET"
 + "`title` = ?"
 + "`description` = ?"
 + "`status` = ?"
+ " WHERE `idGender`= ?;";
      Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {
                
                stmt.setString(1, entity.getTitle());
                stmt.setString(2, entity.getDescription());
                stmt.setBoolean(3, entity.isStatus());
                stmt.setInt(4, entity.getIdGender());
                res = stmt.executeUpdate() > 0;
                db.conn.commit();
            } catch (SQLException ex) {
                if (db.conn != null) {
                    System.err.println(ex.getMessage());
                    System.err.print("Transaction is being rolled back.");
                    db.conn.rollback();
                }
            }
        }
        return res;
    }

    public boolean deleteO(Gender entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Gender readByTitle(String title) throws SQLException {
        Gender g = new Gender();
        String load = "SELECT c.* FROM gender c WHERE c.title = ?;";
         
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (PreparedStatement stmt = connection.prepareStatement(load)) {
                
                stmt.setString(1, title);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    g = new Gender(
                            rs.getInt("idGender"), 
                            rs.getString("title"), 
                            rs.getString("description"), 
                            rs.getBoolean("status"));
                      
                }
            }
        }
        return g;
            
    }
}
