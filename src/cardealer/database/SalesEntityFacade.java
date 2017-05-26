/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import static cardealer.CarForm.db;
import cardealer.models.Sale;
import java.io.Serializable;
import javax.transaction.TransactionRequiredException;
import cardealer.database.exceptions.*;
import cardealer.models.Car;
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
public class SalesEntityFacade {
    
       public boolean create(Sale entity) throws EntityExistsException,
            IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `cardealer`.`sales`\n" +
"(`firstName`,\n" +
"`lastName`,\n" +
"`price`,\n" +
"`car_id`,\n"+
"`paid_date`) "
                + "VALUES ( ?,?,?,?,?,?);";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setString(1, entity.getFirstName());
                stmt.setString(2, entity.getLastName());
                stmt.setInt(3, (int) entity.getPrice());
                stmt.setLong(4, entity.getCar_id());
                stmt.setDate(5,entity.getPaid_date());
              

                res = stmt.execute(create);
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
       public Sale read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Sale> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Sale> mSales = new ArrayList<>();

        String loads = "SELECT `sales`.`idSale`,\n" +
"    `sales`.`firstName`,\n" +
"    `sales`.`lastName`,\n" +
"    `sales`.`price`,\n" +
"    `sales`.`car_id`,\n" +
"    `sales`.`sale_date`,\n" +
"    `sales`.`paid_date`\n" +
"FROM `cardealer`.`sales` ;";

        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {

                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    Sale c = new Sale(rs.getLong("idSale"), rs.getString("firstName"),
                            rs.getString("lastName"), rs.getFloat("price"), rs.getLong("car_id"),
                    rs.getDate("sale_date"),rs.getDate("paid_date"));

                    mSales.add(c);

                }
            }
        }
        return mSales;
    }

    public boolean update(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public boolean deleteO(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
