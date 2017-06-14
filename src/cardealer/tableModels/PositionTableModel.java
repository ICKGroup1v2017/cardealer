package cardealer.tableModels;

import cardealer.models.Position;
import cardealer.utils.Convert;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import static java.time.temporal.ChronoField.NANO_OF_SECOND;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Ahmet Murati
 */
public class PositionTableModel extends DefaultTableModel {

    public PositionTableModel() {
        super();
    }
    private String[] columnNames = {"IdPosition", "Title", "Description", "Status", "Data e fillimit", "Data e mbarimit"};

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

    public Position getSelectedRow(int row) {
        Position p = new Position();
        
            Vector v = (Vector) dataVector.get(row);

            Long idPosition = (Long) v.get(0);
            String title = (String) v.get(1);
            String description = (String) v.get(2);
            boolean status = (boolean) v.get(3);
          
            
            java.sql.Date startDate = java.sql.Date.valueOf(Convert.toMysql((String) v.get(4)).toString());
            java.sql.Date endDate = null;
            try {
                endDate = (java.sql.Date) java.sql.Date.valueOf((String) v.get(5));

            } catch (Exception ex) {
             //   System.out.println(ex.getMessage());
            }
            p.setIdPosition(idPosition);
            p.setTitle(title);
            p.setDescription(description);
            p.setStatus(status);
            p.setStartDate(startDate);
            p.setEndDate(endDate);

      
        return p;
    }

    /*
         * JTable uses this method to determine the default renderer/ editor for
         * each cell. If we didn't implement this method, then the last column
         * would contain text ("true"/"false"), rather than a check box.
     */
    @Override
    public Class getColumnClass(int c) {
        if (c == 3) {
            return Boolean.class;
        }
        return String.class;
    }

    /*
         * Don't need to implement this method unless your table's editable.
     */
    public boolean isCellEditable(int row, int col) {
        return false;
    }
}
