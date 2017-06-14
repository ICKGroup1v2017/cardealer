package cardealer.tableModels;
import cardealer.models.Gender;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;
/**
*
* @author Ahmet Murati
*/
public class 
GenderTableModel extends DefaultTableModel    {
        public GenderTableModel()
        {
              super();
        }
        private String[] columnNames ={"IdGender", "Title", "Description", "Status"};
        public int getColumnCount()
        {
            return columnNames.length;
        }
        
        public int getRowCount()
        {
            return dataVector.size();
        }
        
        public String getColumnName(int col)
        {
           return columnNames[col];
        }
        
        public Gender getSelectedRow(int row)
        {
            Vector v = (Vector)dataVector.get(row);
            Gender g = new Gender();
            Integer idGender = (Integer) v.get(0);
            String title = (String) v.get(1);
            String description = (String) v.get(2);
            boolean status = (boolean) v.get(3);
            g.setIdGender(idGender);
            g.setTitle(title);
            g.setDescription(description);
            g.setStatus(status);
            return g;
        }
         /*
         * JTable uses this method to determine the default renderer/ editor for
         * each cell. If we didn't implement this method, then the last column
         * would contain text ("true"/"false"), rather than a check box.
        */
        public Class getColumnClass(int c)
        {
             return getValueAt(0, c).getClass();
        }
        
        /*
         * Don't need to implement this method unless your table's editable.
         */
        public boolean isCellEditable(int row, int col)
        {
            return false;
         }
        }
