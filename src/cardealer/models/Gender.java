/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cardealer.models;

/**
 *
 * @author ahmet
 */
public class Gender {

    private int idGender;
    private String title;
    private String description;
    private boolean status;

    public Gender() {
        idGender = 0;
        title = "";
        description = "";
        status = false;
    }

    public Gender(int idGender) {
        this();
        this.idGender = idGender;
        title = "";
        description = "";
        status = false;
    }

    public Gender(int idGender, String title, String description, boolean status) {
        this();
        this.idGender = idGender;
        this.title = title;
        this.description = description;
        this.status = status;
    }

    public int getIdGender() {
        return idGender;
    }

    public void setIdGender(int idGender) {
        this.idGender = idGender;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return title ;
    }
    
    
}
