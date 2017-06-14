/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.database;

import static cardealer.SalesForecastForm.db;
import cardealer.models.SaleForecast;
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
public class SalesForecastsEntityFacade {

    public boolean create(SaleForecast entity) throws EntityExistsException,
            IllegalStateException, IllegalArgumentException,
            TransactionRequiredException, SQLException {
        boolean res = false;

        String create = "INSERT INTO `salesforecast` (`selected_month`, `selected_year`, `forecastedSale`, `realization`) VALUES (?,?,?,?);";

        System.out.println(create);

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(create);
            try {

                stmt.setInt(1, entity.getSelected_month());
                stmt.setInt(2, entity.getSelected_year());
                stmt.setInt(3, entity.getForecastedSale());
                stmt.setInt(4, entity.getRealization());

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

    public SaleForecast read(Serializable primaryKey) throws IllegalStateException, IllegalArgumentException {
        SaleForecast sf = new SaleForecast();
        return sf;
    }

    public ArrayList<SaleForecast> reads() throws EntityExistsException, IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        ArrayList<SaleForecast> mSaleForecasts = new ArrayList<>();

        String loads = "SELECT s.* FROM salesforecast s ;";

        Connection connection = db.conn;
        if (!connection.isClosed()) {

            try (Statement stmt = connection.createStatement()) {

                ResultSet rs = stmt.executeQuery(loads);
                while (rs.next()) {

                    SaleForecast s = new SaleForecast(rs.getLong("idSaleForecast"), rs.getInt("selected_month"),
                            rs.getInt("selected_year"), rs.getInt("forecastedSale"), rs.getInt("realization"),
                            rs.getDate("done_date"));

                    mSaleForecasts.add(s);

                }
            }
        }
        return mSaleForecasts;
    }

    public boolean update(SaleForecast entity) throws IllegalStateException,
            IllegalArgumentException, TransactionRequiredException, SQLException {
        boolean res = false;
        String update = "UPDATE `cardealer`.`salesforecast`\n "
                + "SET\n "
                + "`selected_month` = ?,\n "
                + "`selected_year` = ?,\n "
                + "`forecastedSale` = ?,\n "
                + "`realization` = ?\n "
                + "WHERE `idSaleForecast` = ?;";

        Connection connection = db.conn;
        connection.setAutoCommit(false);
        if (!connection.isClosed()) {
            PreparedStatement stmt = connection.prepareStatement(update);
            try {

                stmt.setInt(1, entity.getSelected_month());
                stmt.setInt(2, entity.getSelected_year());
                stmt.setInt(3, entity.getForecastedSale());
                stmt.setInt(4, entity.getRealization());
                stmt.setLong(5, entity.getIdSaleForecast());
                
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

    public boolean deleteO(SaleForecast entity) throws IllegalStateException, IllegalArgumentException, TransactionRequiredException, PersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
