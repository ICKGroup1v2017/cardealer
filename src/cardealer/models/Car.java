/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

import java.util.Objects;

/**
 *
 * @author ahmet
 */
public class Car implements Comparable<Car> {

    private final String nl = System.lineSeparator();
    private Long idCar;
    private String make;
    private String model;
    private Integer manufacturingYear;
    private Double price;
    private boolean status;

    public Car() {
        this.idCar = 0L;
        this.make = "";
        this.model = "";
        this.manufacturingYear = 2017;
        this.price = 0d;
        this.status = false;
    }

    public Car(Long id) {
        this();
        this.idCar = id;
    }

    public Car(Long id, String make) {
        this();
        this.idCar = id;
        this.make = make;
    }

    public Car(Long id, String make, String model) {
        this();
        this.idCar = id;
        this.make = make;
        this.model = model;
    }

    public Car(Long id, String make, String model, Integer manufacturingYear) {
        this();
        this.idCar = id;
        this.make = make;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
    }

    public Car(Long idCar, String make, String model, Integer manufacturingYear, double price) {
        this();
        this.idCar = idCar;
        this.make = make;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
    }

    public Car(Long idCar, String make, String model, Integer manufacturingYear, Double price, boolean status) {
        this.idCar = idCar;
        this.make = make;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
        this.price = price;
        this.status = status;
    }

    public Long getIdCar() {
        return idCar;
    }

    public void setIdCar(Long idCar) {
        this.idCar = idCar;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getManufacturingYear() {
        return manufacturingYear;
    }

    public void setManufacturingYear(Integer manufacturingYear) {
        this.manufacturingYear = manufacturingYear;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

     

    @Override
    public String toString() {
        return make + " | " + model + " | " + manufacturingYear;
    }

    @Override
    public int compareTo(Car t) {
        return this.idCar.compareTo(t.getIdCar());
    }
}
