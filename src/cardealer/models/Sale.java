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
    private Car car_id;
    private Double price;
    private Double payment;
    private Double debt;
    private java.sql.Date sale_date;
    private java.sql.Date paid_date;
    private boolean status;

    public Sale() {
        this.idSale = 0l;
        this.firstName = "";
        this.lastName = "";
        this.price = new Double(0l);
        this.payment = new Double(01);
        this.debt = new Double(0l);
        this.car_id = new Car(0l);
        this.sale_date = new Date(new java.util.Date().toInstant().toEpochMilli());
        this.paid_date = null;
        this.status = false;
    }

    public Sale(Long idSale, String firstName, String lastName, Car car_id, Double price, Double payment, Double debt, Date sale_date, Date paid_date, boolean status) {
        this.idSale = idSale;
        this.firstName = firstName;
        this.lastName = lastName;
        this.car_id = car_id;
        this.price = price;
        this.payment = payment;
        this.debt = debt;
        this.sale_date = sale_date;
        this.paid_date = paid_date;
        this.status = status;
    }

    public Long getIdSale() {
        return idSale;
    }

    public void setIdSale(Long idSale) {
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

    public Car getCar_id() {
        return car_id;
    }

    public void setCar_id(Car car_id) {
        this.car_id = car_id;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Double getPayment() {
        return payment;
    }

    public void setPayment(Double payment) {
        this.payment = payment;
    }

    public Double getDebt() {
        return debt;
    }

    public void setDebt(Double debt) {
        this.debt = debt;
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

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }
  
     

    @Override
    public int compareTo(Sale o) {
        return this.idSale.compareTo(o.idSale);
    }
}
