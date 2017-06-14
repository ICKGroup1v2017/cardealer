package cardealer.database;

import static cardealer.PositionForm.db;
import cardealer.database.exceptions.EntityExistsException;
import cardealer.database.exceptions.PersistenceException;
import cardealer.models.Position;
import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.transaction.TransactionRequiredException;

/**
 * This class serves as Entity Facade for the Positions entity
 *
 * @author Ahmet Murati
 */
public class PositionsEntityFacade {

    /**
     * This method serves to create a new record based on the passed entity
     *
     * @param entity
     * @param entityPosition
     * @return boolean
     * @throws EntityExistsException It is thrown if the entity already exists
     * @throws IllegalStateException
     * @throws IllegalArgumentException
     * @throws TransactionRequiredException
     * @throws java.sql.SQLException
     */
    public boolean create(Position entity) throws EntityExistsException, IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;
        String create = "INSERT INTO `position` (`title`,`description`,`status`,`startDate`,`endDate`) VALUES (?, ?, ?, ?, ?);";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {
                stmt.setString(1, entity.getTitle());
                stmt.setString(2, entity.getDescription());
                stmt.setBoolean(3, entity.isStatus());
                stmt.setDate(4, entity.getStartDate());
                stmt.setDate(5, entity.getEndDate());
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

    public Position read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public ArrayList<Position> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<Position> mPositions = new ArrayList<>();
        String loads = "SELECT c.* FROM position c;";
        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {
                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    Position inst = new Position(
                            rs.getLong("idPosition"),
                            rs.getString("title"),
                            rs.getString("description"),
                            rs.getBoolean("status"), 
                            rs.getDate("startDate"),
                            rs.getDate("endDate"));
                    mPositions.add(inst);
                }
            }
        }
        return mPositions;
    }

    public boolean update(Position entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `position`"
                + "SET"
                
                + "`title` = ?,"
                + "`description` = ?,"
                + "`status` = ?,"
                + "`startDate` = ?,"
                + "`endDate` = ?"
                + " WHERE `idPosition`= ?;";
        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {
                
                stmt.setString(1, entity.getTitle());
                stmt.setString(2, entity.getDescription());
                stmt.setBoolean(3, entity.isStatus());
                stmt.setDate(4, entity.getStartDate());
                stmt.setDate(5, entity.getEndDate());
                stmt.setLong(6, entity.getIdPosition());
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

    public boolean deleteO(Position entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
