package cardealer.database;

import cardealer.models.Employees_positions;
import java.io.Serializable;
import javax.transaction.TransactionRequiredException;
import cardealer.database.exceptions.*;
import static cardealer.EmployeesForm.db;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**
 * This class serves as Entity Facade for the Employees_positionss entity
 *
 * @author Ahmet Murati
 */
public class Employees_positionsEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entityEmployees_positions
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public boolean create(Employees_positions entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `employees_positions` (`employeeId`,`positionID`) VALUES (?, ?);";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

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

    public Employees_positions read(Integer primaryKey) throws IllegalStateException, IllegalArgumentException, SQLException {
        String load = "SELECT e.* FROM Employees_positions e WHERE e.id = ?;";
        Employees_positions e = new Employees_positions();
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (PreparedStatement stmt = connection.prepareStatement(load)) {
                stmt.setInt(1, primaryKey);
                ResultSet rs = stmt.executeQuery(load);
                if (rs.next()) {
                    e = new Employees_positions(rs.getInt("id"),
                            rs.getLong("employeeId"),
                            rs.getLong("positionID"));
                }
            }
        }
        return e;
    }

    public ArrayList<Employees_positions> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Employees_positions> mEmployees_positionss = new ArrayList<>();
        String loads = "SELECT c.* FROM employees_positions c;";
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    Employees_positions inst = new Employees_positions(
                            rs.getInt("id"), 
                            rs.getLong("employeeId"), 
                            rs.getLong("positionID"));
                    mEmployees_positionss.add(inst);
                }
            }
        }
        return mEmployees_positionss;
    }

    public boolean update(Employees_positions entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `employees_positions`"
 + "SET"

  + "`id` = ?"
 + "`employeeId` = ?"
 + "`positionID` = ?"
+ " WHERE `id`= ?;";
      Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {
                stmt.setInt(1, entity.getId());
                stmt.setLong(2, entity.getEmployeeId());
                stmt.setLong(3, entity.getPositionID());
                stmt.setInt(4, entity.getId());
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

    public boolean deleteO(Employees_positions entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
