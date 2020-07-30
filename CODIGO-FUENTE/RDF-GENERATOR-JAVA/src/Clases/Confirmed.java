/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.Date;

/**
 *
 * @author Freddy
 */
public class Confirmed {
    String id;
    Date date;
    int confirmedCantidad;
    int confirmedCantidadTotal;
    Country country;
    Dataset dataset;

    public Confirmed(String id, Date date, int confirmedCantidad, int confirmedCantidadTotal, Country country, Dataset dataset) {
        this.id = id;
        this.date = date;
        this.confirmedCantidad = confirmedCantidad;
        this.confirmedCantidadTotal = confirmedCantidadTotal;
        this.country = country;
        this.dataset = dataset;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public int getConfirmedCantidad() {
        return confirmedCantidad;
    }

    public void setConfirmedCantidad(int confirmedCantidad) {
        this.confirmedCantidad = confirmedCantidad;
    }

    public int getConfirmedCantidadTotal() {
        return confirmedCantidadTotal;
    }

    public void setConfirmedCantidadTotal(int confirmedCantidadTotal) {
        this.confirmedCantidadTotal = confirmedCantidadTotal;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Dataset getDataset() {
        return dataset;
    }

    public void setDataset(Dataset dataset) {
        this.dataset = dataset;
    }
    
    
}
