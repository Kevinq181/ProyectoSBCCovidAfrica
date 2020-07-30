/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author Freddy
 */
public class Dataset {
    String name;
    String description;
    String url;
    Date submited;
    Date modificaction;
    Catalog catalog;

    public Dataset(String name, String description, String url, Date submited, Date modificaction, Catalog catalog) {
        this.name = name;
        this.description = description;
        this.url = url;
        this.submited = submited;
        this.modificaction = modificaction;
        this.catalog = catalog;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Date getSubmited() {
        return submited;
    }

    public void setSubmited(Date submited) {
        this.submited = submited;
    }

    public Date getModificaction() {
        return modificaction;
    }

    public void setModificaction(Date modificaction) {
        this.modificaction = modificaction;
    }

    public Catalog getCatalog() {
        return catalog;
    }

    public void setCatalog(Catalog catalog) {
        this.catalog = catalog;
    }  
    
}
