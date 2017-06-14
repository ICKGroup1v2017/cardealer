/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Consumer;

/**
 *
 * @author ahmet
 */
public class Employee {

    private Long idEmployee;
    private String firstName;
    private String middleName;
    private String lastName;
    private Gender genderID;
    private java.sql.Date hiringDate;
    private ArrayList<Position> positions;

    public Employee() {
        this.idEmployee = 0L;
        this.firstName = "";
        this.middleName = "";
        this.lastName = "";
        this.genderID = new Gender();
        this.hiringDate = new java.sql.Date(new java.util.Date().getTime());
        this.positions = new ArrayList<>();
            
    }

    public Employee(Long idEmployee, String firstName, String middleName, String lastName, Gender genderID, Date hiringDate) {
        this.idEmployee = idEmployee;
        this.firstName = firstName;
        this.middleName = middleName;
        this.lastName = lastName;
        this.genderID = genderID;
        this.hiringDate = hiringDate;
    }

    public Long getIdEmployee() {
        return idEmployee;
    }

    public void setIdEmployee(Long idEmployee) {
        this.idEmployee = idEmployee;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Gender getGenderID() {
        return genderID;
    }

    public void setGenderID(Gender genderID) {
        this.genderID = genderID;
    }

    public Date getHiringDate() {
        return hiringDate;
    }

    public void setHiringDate(Date hiringDate) {
        this.hiringDate = hiringDate;
    }

    public boolean addAPosition(Position pos) {
        
        positions.stream().filter((position) -> {
            return position.getEndDate()!=null;
        }).map((Position position) -> {
            position.setEndDate(new java.sql.Date(new java.util.Date().getTime()));
            return position;
        }).forEachOrdered(new Consumer<Position>() {
            @Override
            public void accept(Position position) {
                position.setStatus(false);
            }
        }); 
        return this.positions.add(pos);
    }

    public void setPositions(ArrayList<Position> positions) {
        this.positions = positions;
    }
    
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.idEmployee);
        hash = 37 * hash + Objects.hashCode(this.firstName);
        hash = 37 * hash + Objects.hashCode(this.middleName);
        hash = 37 * hash + Objects.hashCode(this.lastName);
        hash = 37 * hash + Objects.hashCode(this.genderID);
        hash = 37 * hash + Objects.hashCode(this.hiringDate);
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
        final Employee other = (Employee) obj;
        if (!Objects.equals(this.firstName, other.firstName)) {
            return false;
        }
        if (!Objects.equals(this.middleName, other.middleName)) {
            return false;
        }
        if (!Objects.equals(this.lastName, other.lastName)) {
            return false;
        }
        if (!Objects.equals(this.idEmployee, other.idEmployee)) {
            return false;
        }
        if (!Objects.equals(this.genderID, other.genderID)) {
            return false;
        }
        return Objects.equals(this.hiringDate, other.hiringDate);
    }

    @Override
    public String toString() {
        return   idEmployee + " | " + firstName + 
                (middleName.length()>0?middleName + " ":"") + lastName + "\nData e punësimi: " + hiringDate + '}';
    }
    
    
    
    
    
}
