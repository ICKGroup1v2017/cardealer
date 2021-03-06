/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.tableModels;
import cardealer.models.SaleForecast;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class SaleForecastTableModel extends DefaultTableModel{
    
    public SaleForecastTableModel(){
        super();
    }
    public String[]columnNames={"idSaleForecast","Muaji","Viti","Shitja e Planifikuar","Realizimi","Data e Përfudimit"};


 public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }
    public SaleForecast getSelectedRow(int row){
        Vector v =(Vector) dataVector.get(row);
        
        SaleForecast sf = new SaleForecast();
         
        Long idSaleForecast = (Long)v.get(0);
        int selected_month = (int)v.get(1);
        int selected_year = (int)v.get(2);;
        int forecastedSale = (int)v.get(3);;
        int realization = (int)v.get(4);
        
       
        sf.setIdSaleForecast(idSaleForecast);
        sf.setSelected_month(selected_month);
        sf.setSelected_year(selected_year);
        sf.setForecastedSale(forecastedSale);
        sf.setRealization(realization);
        
        return sf;
    }
    @Override
    public Class getColumnClass(int s){
        return getValueAt(0,s).getClass();
        
    }
    @Override
    public boolean isCellEditable(int row,int col){
        return false;
    }
}