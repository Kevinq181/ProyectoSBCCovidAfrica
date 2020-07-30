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
public class Recovered {
    String id;
    Date date;
    int recoveredCantidad;
    int recoveredCantidadTotal;
    Country country;
    Dataset dataset;

    public Recovered(String id, Date date, int recoveredCantidad, int recoveredCantidadTotal, Country country, Dataset dataset) {
        this.id = id;
        this.date = date;
        this.recoveredCantidad = recoveredCantidad;
        this.recoveredCantidadTotal = recoveredCantidadTotal;
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

    public int getRecoveredCantidad() {
        return recoveredCantidad;
    }

    public void setRecoveredCantidad(int recoveredCantidad) {
        this.recoveredCantidad = recoveredCantidad;
    }

    public int getRecoveredCantidadTotal() {
        return recoveredCantidadTotal;
    }

    public void setRecoveredCantidadTotal(int recoveredCantidadTotal) {
        this.recoveredCantidadTotal = recoveredCantidadTotal;
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
