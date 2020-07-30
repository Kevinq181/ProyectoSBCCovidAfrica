/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

import java.sql.Date;

/**
 *
 * @author Freddy
 */
public class MedicalInformation {
    String id;
    String dateFirstSymptom;
    String travelHistoryDate;
    String travelHistoryLocation;
    String deathDate;
    String currentStatus;

    public MedicalInformation(String id, String dateFirstSymptom, String travelHistoryDate, String travelHistoryLocation, String deathDate, String currentStatus) {
        this.id = id;
        this.dateFirstSymptom = dateFirstSymptom;
        this.travelHistoryDate = travelHistoryDate;
        this.travelHistoryLocation = travelHistoryLocation;
        this.deathDate = deathDate;
        this.currentStatus = currentStatus;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDateFirstSymptom() {
        return dateFirstSymptom;
    }

    public void setDateFirstSymptom(String dateFirstSymptom) {
        this.dateFirstSymptom = dateFirstSymptom;
    }

    public String getTravelHistoryDate() {
        return travelHistoryDate;
    }

    public void setTravelHistoryDate(String travelHistoryDate) {
        this.travelHistoryDate = travelHistoryDate;
    }

    public String getTravelHistoryLocation() {
        return travelHistoryLocation;
    }

    public void setTravelHistoryLocation(String travelHistoryLocation) {
        this.travelHistoryLocation = travelHistoryLocation;
    }

    public String getDeathDate() {
        return deathDate;
    }

    public void setDeathDate(String deathDate) {
        this.deathDate = deathDate;
    }

    public String getCurrentStatus() {
        return currentStatus;
    }

    public void setCurrentStatus(String currentStatus) {
        this.currentStatus = currentStatus;
    }

    
}
