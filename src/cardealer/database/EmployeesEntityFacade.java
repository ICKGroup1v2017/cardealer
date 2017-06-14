package cardealer.database;

import static cardealer.EmployeesForm.db;
import cardealer.database.GendersEntityFacade;
import cardealer.database.exceptions.EntityExistsException;
import cardealer.database.exceptions.PersistenceException;
import cardealer.models.Employee;
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
 * This class serves as Entity Facade for the Employees entity
 *
 * @author Ahmet Murati
 */
public class EmployeesEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entityEmployee
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     */
    public boolean create(Employee entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `employee` (`firstName`,`middleName`,`lastName`,`genderID`) VALUES (?, ?, ?, ?);";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {
                stmt.setString(1, entity.getFirstName());
                stmt.setString(2, entity.getMiddleName());
                stmt.setString(3, entity.getLastName());
                stmt.setInt(4, entity.getGenderID().getIdGender());
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

    public Employee read(Long primaryKey) throws IllegalStateException, IllegalArgumentException, SQLException {
        String load = "SELECT e.* FROM Employee e WHERE e.idEmployee = ?;";
        Employee e = new Employee();
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (PreparedStatement stmt = connection.prepareStatement(load)) {
                stmt.setLong(1, primaryKey);
                ResultSet rs = stmt.executeQuery(load);
                if (rs.next()) {
                    Gender g = new Gender();

                    GendersEntityFacade gef = new GendersEntityFacade();
                    g = gef.read(rs.getInt("genderID"));
                    e = new Employee(rs.getLong("idEmployee"),
                            rs.getString("firstName"), 
                            rs.getString("middleName"), 
                            rs.getString("lastName"),
                            g, 
                            rs.getDate("hiringDate"));
                }
            }
        }
        return e;
    }

    public ArrayList<Employee> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Employee> mEmployees = new ArrayList<>();
        String loads = "SELECT c.* FROM employee c;";
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {
                    Gender g = new Gender(rs.getInt("genderID"));
                    GendersEntityFacade gef = new GendersEntityFacade();
                    g = gef.read(rs.getInt("genderID"));
                    Employee inst = new Employee(
                            rs.getLong("idEmployee"),
                            rs.getString("firstName"),
                            rs.getString("middleName"),
                            rs.getString("lastName"),
                            g,
                            rs.getDate("hiringDate"));
                    mEmployees.add(inst);
                }
            }
        }
        return mEmployees;
    }

    public boolean update(Employee entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `employee`"
                + "SET"
                + "`idEmployee` = ?"
                + "`firstName` = ?"
                + "`middleName` = ?"
                + "`lastName` = ?"
                + "`genderID` = ?"
                + "`hiringDate` = ?"
                + " WHERE `idEmployee`= ?;";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {
                stmt.setLong(1, entity.getIdEmployee());
                stmt.setString(2, entity.getFirstName());
                stmt.setString(3, entity.getMiddleName());
                stmt.setString(4, entity.getLastName());

                stmt.setLong(5, entity.getGenderID().getIdGender());
                stmt.setDate(6, entity.getHiringDate());
                stmt.setLong(7, entity.getIdEmployee());
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

    public boolean deleteO(Employee entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
