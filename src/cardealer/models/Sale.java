/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

import java.sql.Date;

/**
 *
 * @author ahmet
 */
public class Sale implements Comparable<Sale> {

    private Long idSale;

    private String firstName;
    private String lastName;
    private Double price;
    private Double payment;
    private Long car_id; 
    private java.sql.Date sale_date; 
    private java.sql.Date paid_date;

    public Sale() {
        this.idSale = 0l;
        this.firstName = "";
        this.lastName = "";
        this.price = new Double(0l);
        this.payment = new Double(01);
        this.car_id = 0l;
        this.sale_date = new Date(new java.util.Date().toInstant().toEpochMilli());
        this.paid_date = null;
    }

    public Sale(Long idSale, String firstName, String lastName, Double price, Double payment, Long car_id, Date sale_date, Date paid_date) {
        this.idSale = idSale;
        this.firstName = firstName;
        this.lastName = lastName;
        this.price = price;
        this.payment = payment;
        this.car_id = car_id;
        this.sale_date = sale_date;
        this.paid_date = paid_date;
    }

    public Long getId() {
        return idSale;
    }

    public void setId(Long idSale) {
        this.idSale = idSale;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Long getCar_id() {
        return car_id;
    }

    public void setCar_id(Long car_id) {
        this.car_id = car_id;
    }

    public Date getSale_date() {
        return sale_date;
    }

    public void setSale_date(Date sale_date) {
        this.sale_date = sale_date;
    }

    public Date getPaid_date() {
        return paid_date;
    }

    public void setPaid_date(Date paid_date) {
        this.paid_date = paid_date;
    }

    @Override
    public int compareTo(Sale o) {
        return this.idSale.compareTo(o.idSale);
    }
}
