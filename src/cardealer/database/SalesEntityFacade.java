/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import static cardealer.Main.db;
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
        String create = "INSERT INTO `cardealer`.`sale`\n" +
"(`firstName`,\n" +
"`lastName`,\n" +
"`car_id`,\n" +
"`price`,\n" +
"`payment`,\n" +
"`debt`,\n" +
"`paid_date`,\n" +
"`status`)\n" +
"VALUES\n" +
"(?,\n" +
"?,\n" +
"?,\n" +
"?,\n" +
"?,\n" +
"?,\n" +
"?,\n" +
"?)";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setString(1, entity.getFirstName());
                stmt.setString(2, entity.getLastName());
                stmt.setLong(3, entity.getCar_id().getIdCar());
                stmt.setDouble(4, entity.getPrice());
                stmt.setDouble(5, entity.getPayment());
                stmt.setDouble(6, entity.getDebt());
                stmt.setDate(7, entity.getPaid_date());
                stmt.setBoolean(8, entity.isStatus());

                res = stmt.execute();
                db.conn.commit();
                res = true;
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

        String loads = "SELECT `sale`.`idSale`,\n"
                + "    `sale`.`firstName`,\n"
                + "    `sale`.`lastName`,\n"
                + "    `sale`.`car_id`,\n"
                + "    `sale`.`price`,\n"
                + "    `sale`.`payment`,\n"
                + "    `sale`.`sale_date`,\n"
                + "    `sale`.`paid_date`,\n"
                + "    `sale`.`debt`,\n"
                + "    `sale`.`status` FROM sale;";

        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {

                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {
                    Car c = new Car(rs.getLong("car_id"));
                    CarsEntityFacade cef = new CarsEntityFacade();
                    c = cef.read(c.getIdCar());
                    Sale s = new Sale(rs.getLong("idSale"),
                            rs.getString("firstName"),
                            rs.getString("lastName"),
                            c,
                            rs.getDouble("price"),
                            rs.getDouble("payment"),
                            rs.getDouble("debt"),
                            rs.getDate("sale_date"),
                            rs.getDate("paid_date"),
                            rs.getBoolean("status"));

                    mSales.add(s);

                }
            }
        }
        return mSales;
    }

    public boolean update(Sale entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "UPDATE `cardealer`.`sale`\n"
                + "SET\n"
                + "`firstName` = ?,\n"
                + "`lastName` = ?,\n"
                + "`car_id` = ?,\n"
                + "`price` = ?,\n"
                + "`payment` = ?,\n"
                + "`debt` = ?,\n"
                + "`paid_date` = ?,\n"
                + "`status` = ?\n"
                + "WHERE `idSale` = ?;";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setString(1, entity.getFirstName());
                stmt.setString(2, entity.getLastName());
                stmt.setLong(3, entity.getCar_id().getIdCar());
                stmt.setDouble(4, entity.getPrice());
                stmt.setDouble(5, entity.getPayment());
                stmt.setDouble(6, entity.getDebt());
                stmt.setDate(7, entity.getPaid_date());
                stmt.setBoolean(8, entity.isStatus());
                stmt.setLong(9, entity.getIdSale());
                res = stmt.execute();
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

    public boolean deleteO(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
