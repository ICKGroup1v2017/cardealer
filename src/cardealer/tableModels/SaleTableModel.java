/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.tableModels;
 
import cardealer.models.Sale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class SaleTableModel extends DefaultTableModel{
    
    public SaleTableModel(){
        super();
    }
    private String [] columnNames = {"idSale","Emri","Mbiemri","Cmimi","Vetura","Data e shitjes","Data e pageses" };
  
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    public Sale getSelectedRow(int row){
        Vector v = (Vector) dataVector.get(row);
        Sale s = new Sale();
        
        Long idSale = (Long) v.get(0);
        String emri = ((String) v.get(1));
        String mbiemri = ((String) v.get(2));
        Double cmimi = ((Double)v.get(3));
        String vetura = ((String) v.get(4));
        java.sql.Date dataEShitjes = ((java.sql.Date)v.get(5));
        java.sql.Date dataEPageses = ((java.sql.Date)v.get(6));
        
        s.setId(idSale);
        s.setFirstName(emri);
        s.setLastName(mbiemri);
        s.setPrice(cmimi);
        s.setSale_date(dataEShitjes);
        s.setPaid_date(dataEPageses);
        
        return s;
                
        
        
        
    }
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
