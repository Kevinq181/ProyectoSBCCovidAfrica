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
public class Deaths {
    String id;
    Date date;
    int deathsCantidad;
    int deathsCantidadTotal;
    Country country;
    Dataset dataset;

    public Deaths(String id, Date date, int deathsCantidad, int deathsCantidadTotal, Country country, Dataset dataset) {
        this.id = id;
        this.date = date;
        this.deathsCantidad = deathsCantidad;
        this.deathsCantidadTotal = deathsCantidadTotal;
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

    public int getDeathsCantidad() {
        return deathsCantidad;
    }

    public void setDeathsCantidad(int deathsCantidad) {
        this.deathsCantidad = deathsCantidad;
    }

    public int getDeathsCantidadTotal() {
        return deathsCantidadTotal;
    }

    public void setDeathsCantidadTotal(int deathsCantidadTotal) {
        this.deathsCantidadTotal = deathsCantidadTotal;
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
