package cardealer.tableModels;

import cardealer.models.User_info;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmet Murati
 */
public class User_infoTableModel extends DefaultTableModel {

    public User_infoTableModel() {
        super();
    }
    private String[] columnNames = {"ID", "Punëtori", "Email", "Password", "Status"};

    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public User_info getSelectedRow(int row) {
        Vector v = (Vector) dataVector.get(row);
        User_info u = new User_info();
        Long idUserInfo = (Long) v.get(0);
        Integer employeeID = (Integer) v.get(1);
        String email = (String) v.get(2);
        String password = (String) v.get(3);
        boolean status = (boolean) v.get(4);
        u.setIdUserInfo(idUserInfo);
        u.setEmployeeID(employeeID);
        u.setEmail(email);
        u.setPassword(password);
        u.setStatus(status);
        return u;
    }

    /*
         * JTable uses this method to determine the default renderer/ editor for
         * each cell. If we didn't implement this method, then the last column
         * would contain text ("true"/"false"), rather than a check box.
     */
    public Class getColumnClass(int c) {
        return getValueAt(0, c).getClass();
    }

    /*
         * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
