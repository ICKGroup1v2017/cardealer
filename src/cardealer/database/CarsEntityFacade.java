/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import cardealer.models.Car;
import java.io.Serializable;
import javax.transaction.TransactionRequiredException;
import cardealer.database.exceptions.*;
import static cardealer.Main.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class serves as Entity Facade for the Car entity
 *
 * @author ahmet
 */
public class CarsEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entity Car
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     * @throws java.sql.SQLException
     */
    public boolean create(Car entity) throws EntityExistsException,
            IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `car`"
                + "      (  `make`, `model`, `manufacturingYear`,`price`, `status` ) "
                + "VALUES ( ?, ?, ?, ?, ?);";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setString(1, entity.getMake());
                stmt.setString(2, entity.getModel());
                stmt.setInt(3, entity.getManufacturingYear());
                stmt.setDouble(4, entity.getPrice());
                stmt.setBoolean(5, entity.isStatus());

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

    public Car read(Long primaryKey) throws IllegalStateException, IllegalArgumentException, SQLException {
        Car mc = new Car(primaryKey);
        String loads = "SELECT c.* FROM car c WHERE c.idCar = ? ;";

        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (PreparedStatement stmt = connection.prepareStatement(loads)) {
                stmt.setLong(1, primaryKey);
                ResultSet rs = stmt.executeQuery();
                if (rs.next()) {

                    mc = new Car(rs.getLong("idCar"), 
                            rs.getString("make"),
                            rs.getString("model"),
                            rs.getInt("manufacturingYear"), 
                            rs.getDouble("price"), 
                            rs.getBoolean("status"));
                }
            }
        }
        return mc;
    }

    public ArrayList<Car> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Car> mCars = new ArrayList<>();

        String loads = "SELECT c.* FROM car c ;";

        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {

                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    Car c = new Car(rs.getLong("idCar"), rs.getString("make"),
                            rs.getString("model"), rs.getInt("manufacturingYear"),rs.getDouble("price"), rs.getBoolean("status"));

                    mCars.add(c);

                }
            }
        }
        return mCars;
    }

    public boolean update(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `cardealer`.`car`\n"
                + "SET\n"
                + "`make` = ?,\n"
                + "`model` = ?,\n"
                + "`manufacturingYear` = ?,\n"
                + "`price` = ?,"
                + "`status` = ?"
                + " WHERE `id` = ?;\n";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {

                stmt.setString(1, entity.getMake());
                stmt.setString(2, entity.getModel());
                stmt.setInt(3, entity.getManufacturingYear());
                stmt.setDouble(4, entity.getPrice());
                stmt.setBoolean(5, entity.isStatus());
                stmt.setLong(6, entity.getIdCar());
                res = stmt.executeUpdate() > 0;
                db.conn.commit();
            } catch (SQLException ex) {
                if (db.conn != null) {
                    System.err.println(ex.getMessage());
                    System.err.print("Transaction is being rolled back");
                    db.conn.rollback();
                }
            }
        }
        return res;
    }

    public boolean deleteO(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public Car readByMakeAndModel(String vetura) {
        Car c = new Car();
         
        return c;
    }

}
