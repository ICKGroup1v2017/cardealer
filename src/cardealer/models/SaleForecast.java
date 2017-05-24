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
public class SaleForecast {
    private Long idSaleForecast;
    private int selected_month;
    private int selected_year;
    private int forecastedSale;
    private int realization;
    private java.sql.Date done_date;

    public SaleForecast() {
        this.idSaleForecast = 0l;
        this.selected_month = 0;
        this.selected_year = 0;
        this.forecastedSale = 0;
        this.realization = 0;
        this.done_date = new java.sql.Date(new java.util.Date().toInstant().toEpochMilli());
    }

    
    public SaleForecast(Long idSaleForecast, int selected_month, int selected_year, int forecastedSale, int realization, Date done_date) {
        this.idSaleForecast = idSaleForecast;
        this.selected_month = selected_month;
        this.selected_year = selected_year;
        this.forecastedSale = forecastedSale;
        this.realization = realization;
        this.done_date = done_date;
    }

    public Long getIdSaleForecast() {
        return idSaleForecast;
    }

    public void setIdSaleForecast(Long idSaleForecast) {
        this.idSaleForecast = idSaleForecast;
    }

    public int getSelected_month() {
        return selected_month;
    }

    public void setSelected_month(int selected_month) {
        this.selected_month = selected_month;
    }

    public int getSelected_year() {
        return selected_year;
    }

    public void setSelected_year(int selected_year) {
        this.selected_year = selected_year;
    }

    public int getForecastedSale() {
        return forecastedSale;
    }

    public void setForecastedSale(int forecastedSale) {
        this.forecastedSale = forecastedSale;
    }

    public int getRealization() {
        return realization;
    }

    public void setRealization(int realization) {
        this.realization = realization;
    }

    public Date getDone_date() {
        return done_date;
    }

    public void setDone_date(Date done_date) {
        this.done_date = done_date;
    }
    
    
    
}
