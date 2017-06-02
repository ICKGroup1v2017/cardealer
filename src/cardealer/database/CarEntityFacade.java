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
import static cardealer.CarForm.db;
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
public class CarEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entity Car
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public boolean create(Car entity) throws EntityExistsException,
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

                stmt.setString(1, entity.getMake());
                stmt.setString(2, entity.getModel());
                stmt.setInt(3, entity.getManufacturingYear());

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

    public Car read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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

                    Car c = new Car(rs.getLong("id"), rs.getString("make"),
                            rs.getString("model"), rs.getInt("manufacturingYear"));

                    mCars.add(c);

                }
            }
        }
        return mCars;
    }

    public boolean update(Car entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "UPDATE `cardealer`.`car`\n"
                + "SET\n" 
                + "`make` = ?,\n"
                + "`model` = ?,\n"
                + "`manufacturingYear` = ?>\n"
                + "WHERE `id` = ?;\n";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setString(1, entity.getMake());
                stmt.setString(2, entity.getModel());
                stmt.setInt(3, entity.getManufacturingYear());

                res = stmt.execute(create);
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

}
