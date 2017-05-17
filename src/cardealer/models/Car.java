/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

import cardealer.models.abstracts.CarAbs;
import cardealer.enums.Direction;

import java.util.List;
import java.util.Objects;

/**
 *
 * @author ahmet
 */
public class Car extends CarAbs implements Comparable<Car> {

    private final String nl = System.lineSeparator();
    private Long id;
    private String make;
    private String model;
    private Integer manufacturingYear;
    

    

    public Car() {
        this.id = 0L;
        this.make = "";
        this.model = "";
        this.manufacturingYear = 2017;
       
    }

    public Car(Long id) {
        this();
        this.id = id;
    }

    public Car(Long id, String make) {
        this();
        this.id = id;
        this.make = make;
    }

    public Car(Long id, String make, String model) {
        this();
        this.id = id;
        this.make = make;
        this.model = model;
    }

    public Car(Long id, String make, String model, Integer manufacturingYear) {
        this.id = id;
        this.make = make;
        this.model = model;
        this.manufacturingYear = manufacturingYear;
    }
 
  

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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
 

    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    @Override
    public void move(float speed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public int compareTo(Car o) {
        return this.id.compareTo(o.getId());
    }

    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.id);
        hash = 53 * hash + Objects.hashCode(this.make);
        hash = 53 * hash + Objects.hashCode(this.model);
        hash = 53 * hash + Objects.hashCode(this.manufacturingYear);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.make, other.make)) {
            return false;
        }
        if (!Objects.equals(this.model, other.model)) {
            return false;
        }
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.manufacturingYear, other.manufacturingYear)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Car{" + "nl=" + nl + ", id=" + id + ", make=" + make + ", model=" + model + ", manufacturingYear=" + manufacturingYear + '}';
    }

     

}
