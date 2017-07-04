/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.tableModels;

import cardealer.database.CarsEntityFacade;
import cardealer.models.Car;
import cardealer.models.Sale;
import java.util.Vector;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author ahmet
 */
public class SaleTableModel extends DefaultTableModel {

    public SaleTableModel() {
        super();
    }
    private final String[] columnNames = {"Nr:", "Emri", "Mbiemri", "Vetura", "Çmimi", "Pagesa", "Borxhi", "Data e shitjes", "Data e pagesës", "statusi"};

    @Override
    public int getColumnCount() {
        return columnNames.length;
    }

    public int getRowCount() {
        return dataVector.size();
    }

    public String getColumnName(int col) {
        return columnNames[col];
    }

    public Sale getSelectedRow(int row) {
        Vector v = (Vector) dataVector.get(row);
        Sale s = new Sale();

        Long idSale = (Long) v.get(0);
        String emri = ((String) v.get(1));
        String mbiemri = ((String) v.get(2));
        String vetura = ((String) v.get(3));
        Double cmimi = ((Double) v.get(4));
        Double pagesa = ((Double) v.get(5));
        Double borxhi = ((Double) v.get(6));

        java.sql.Date dataEShitjes = ((java.sql.Date) v.get(7));
        java.sql.Date dataEPageses = ((java.sql.Date) v.get(8));
        boolean status = ((boolean) v.get(9));

        s.setIdSale(idSale);
        s.setFirstName(emri);
        s.setLastName(mbiemri);
        CarsEntityFacade cef = new CarsEntityFacade();
        Car c = cef.readByMakeAndModel(vetura);
        s.setCar_id(c);
        s.setPrice(cmimi);
        s.setPayment(pagesa);
        s.setDebt(borxhi);
        s.setSale_date(dataEShitjes);
        s.setPaid_date(dataEPageses);
        s.setStatus(status);
        return s;

    }

    @Override
    public Class getColumnClass(int c) {
        Class aClass;
        switch (c) {
           
            default: {
                aClass = String.class;
            }
            break;
        }
        return aClass;
    }

    /*
     * Don't need to implement this method unless your table's editable.
     */
    @Override
    public boolean isCellEditable(int row, int col) {

        return false;

    }
}
