/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.tableModels;

import cardealer.models.Car;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class CarTableModel extends DefaultTableModel {
    
    public CarTableModel() {
        super();
    }
    
    private String[] columnNames = {"ID", "Bërja", "Modeli", "Viti i prodhimit","Çmimi", "Status"};
    
    public int getColumnCount() {
        return columnNames.length;
    }
    
    public int getRowCount() {
        return dataVector.size();
    }
    
    public String getColumnName(int col) {
        return columnNames[col];
    }
    
    public Car getSelectedRow(int row) {
        Vector v = (Vector) dataVector.get(row);
        System.out.println("v = " + v.get(0).getClass());
        Car c = new Car();
        Long id = (Long) v.get(0);
        String make = ((String) v.get(1));
        String model = ((String) v.get(2));
        Integer manufacturingYear = Integer.parseInt((String) v.get(3));
        Double price = Double.parseDouble((String)v.get(4));
        boolean status = Boolean.parseBoolean((String) v.get(5));
        
        c.setIdCar(id);
        c.setMake(make);
        c.setModel(model);
        c.setManufacturingYear(manufacturingYear);
        c.setPrice(price);
        c.setStatus(status);
        
        return c;
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
