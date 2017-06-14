/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.tableModels;

import cardealer.database.GendersEntityFacade;
import cardealer.models.Employee;
import cardealer.models.Gender;
import java.sql.SQLException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class EmployeesTableModel extends DefaultTableModel {

    public EmployeesTableModel() {
        super();
    }

    private String[] columnNames = {"ID", "firstName", "middleName", "lastName", "genderID", "hiringDate"};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    @Override
    public int getRowCount() {
        return dataVector.size();
    }

    @Override
    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Employee getSelectedRow(int row) {
        Vector v = (Vector) dataVector.get(row);

        Employee e = new Employee();

        Long idEmployee = (Long) v.get(0);
        String firstName = (String) v.get(1);
        String middleName = (String) v.get(2);
        String lastName = (String) v.get(2);
        String genderID = (String) v.get(4);
        java.sql.Date hiringDate = (java.sql.Date) v.get(5);
        Gender g = new Gender();
        GendersEntityFacade gef = new GendersEntityFacade();
        try {
            g = gef.readByTitle(genderID);
        } catch (IllegalStateException | IllegalArgumentException | SQLException ex) {
            Logger.getLogger(EmployeesTableModel.class.getName()).log(Level.SEVERE, null, ex);
        }
        e.setIdEmployee(idEmployee);
        e.setFirstName(firstName);
        e.setMiddleName(middleName);
        e.setLastName(lastName);
        e.setGenderID(g.getIdGender() > 0 ? g :null);
        e.setHiringDate(hiringDate);

        return e;
    }

    /*
     * JTable uses this method to determine the default renderer/ editor for
     * each cell. If we didn't implement this method, then the last column
     * would contain text ("true"/"false"), rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {

        return false;

    }
 
}
