package BL;

import Clases.*;
import java.sql.SQLException;
import DAT.DATAdministracion;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;

public class BLAdministracion {

    DATAdministracion mp = new DATAdministracion();

    public ArrayList<Case> consultarCaso() throws SQLException, ClassNotFoundException {
        ArrayList<Case> lstcases = new ArrayList<Case>();

        ResultSet rs = mp.ConsultarCaso();
        while (rs.next()) {

            //////Continent///////////
            String nameContinet = rs.getString("nameContinent");
            Continent continent = new Continent(nameContinet);

            //////Country/////////////
            String nameCountry = rs.getString("nameCountry");
            String codeCountry = rs.getString("codeCountry");
            double latitudeCountry = rs.getDouble("latitudeCountry");
            double longitudeCountry = rs.getDouble("longitudeCountry");
            int populationCountry = rs.getInt("populationCountry");
            double pibCountry = rs.getDouble("pibCountry");
            Country country = new Country(nameCountry, codeCountry, latitudeCountry, longitudeCountry, populationCountry, pibCountry, continent);

            //////Province///////////////
            String nameProvince = rs.getString("nameProvince");
            Province province = new Province(nameProvince, country);

            ///////Place//////////////////////
            String namePlace = rs.getString("namePlace");
            String codePlace = rs.getString("codePlace");
            double latitudePlace = rs.getDouble("latitudePlace");
            double longitudePlace = rs.getDouble("longitudePlace");
            int populationPlace = rs.getInt("populationPlace");
            double pibPlace = rs.getDouble("pibPlace");
            Place place = new Place(namePlace, codePlace, latitudePlace, longitudePlace, populationPlace, pibPlace, province);

            //////////Organization//////
            String nameOrganization = rs.getString("nameOrganization");
            Organization organization = new Organization(nameOrganization);

            //////////Catalogo////////////
            String nameCatalog = rs.getString("nameCatalog");
            Catalog catalog = new Catalog(nameCatalog, organization);

            ///////Dataset//////
            String nameDataset = rs.getString("nameDataset");
            String descriptionDataset = rs.getString("descriptionDataset");
            String url = rs.getString("urlDataset");
            Date submited = rs.getDate("dateSubmitedDataset");
            Date modification = rs.getDate("dateModificationDataset");
            Dataset dataset = new Dataset(nameDataset, descriptionDataset, url, submited, modification, catalog);

            ///////MedicalInformation////////////////
            String idMedical = rs.getString("codeMedicalInformation");
            String symptom = String.valueOf(rs.getDate("dateSymptom"));
            String traveldate = String.valueOf(rs.getDate("travelDate"));
            String travellocation = rs.getString("travelLocation");
            String deathdate = String.valueOf(rs.getDate("deathDate"));
            String status = rs.getString("status");
            MedicalInformation medicalInformation = new MedicalInformation(idMedical, symptom, traveldate, travellocation, deathdate, status);

            /////////////Patient///////////////
            String idPatient = rs.getString("patientCode");
            int age = rs.getInt("age");
            String gender = rs.getString("gender");
            Patient patient = new Patient(idPatient, age, gender, medicalInformation);

            //////////////Case////////////
            String idCase = rs.getString("caseCode");
            Date confirmed = rs.getDate("confirmedDate");
            Case case2 = new Case(idCase, confirmed, place, patient, dataset);

            lstcases.add(case2);
        }
        return (lstcases);
    }

    public ArrayList<Confirmed> consultarConfirmados() throws SQLException, ClassNotFoundException {
        ArrayList<Confirmed> lstconfirmados = new ArrayList<Confirmed>();

        ResultSet rs = mp.ConsultarConfirmados();
        while (rs.next()) {

            //////Continent///////////
            String nameContinet = rs.getString("nameContinent");
            Continent continent = new Continent(nameContinet);

            //////Country/////////////
            String nameCountry = rs.getString("nameCountry");
            String codeCountry = rs.getString("codeCountry");
            double latitudeCountry = rs.getDouble("latitudeCountry");
            double longitudeCountry = rs.getDouble("longitudeCountry");
            int populationCountry = rs.getInt("populationCountry");
            double pibCountry = rs.getDouble("pibCountry");
            Country country = new Country(nameCountry, codeCountry, latitudeCountry, longitudeCountry, populationCountry, pibCountry, continent);

            //////////Organization//////
            String nameOrganization = rs.getString("nameOrganization");
            Organization organization = new Organization(nameOrganization);

            //////////Catalogo////////////
            String nameCatalog = rs.getString("nameCatalog");
            Catalog catalog = new Catalog(nameCatalog, organization);

            ///////Dataset//////
            String nameDataset = rs.getString("nameDataset");
            String descriptionDataset = rs.getString("descriptionDataset");
            String url = rs.getString("urlDataset");
            Date submited = rs.getDate("dateSubmitedDataset");
            Date modification = rs.getDate("dateModificationDataset");
            Dataset dataset = new Dataset(nameDataset, descriptionDataset, url, submited, modification, catalog);
            
            ///////Confirmed//////
            String id = rs.getString("confirmedCode");
            Date date= rs.getDate("date");
            int confirmedQuantity = rs.getInt("confirmedQuantity");
            int confirmedTotalQuantity = rs.getInt("confirmedTotalQuantity");
            Confirmed confirmed = new Confirmed(id, date, confirmedQuantity, confirmedTotalQuantity, country, dataset);
            
            lstconfirmados.add(confirmed);
        }

        return lstconfirmados;
    }
    
    public ArrayList<Recovered> consultarRecuperados() throws SQLException, ClassNotFoundException {
        ArrayList<Recovered> lstrecuperados = new ArrayList<Recovered>();

        ResultSet rs = mp.ConsultarRecuperados();
        while (rs.next()) {

            //////Continent///////////
            String nameContinet = rs.getString("nameContinent");
            Continent continent = new Continent(nameContinet);

            //////Country/////////////
            String nameCountry = rs.getString("nameCountry");
            String codeCountry = rs.getString("codeCountry");
            double latitudeCountry = rs.getDouble("latitudeCountry");
            double longitudeCountry = rs.getDouble("longitudeCountry");
            int populationCountry = rs.getInt("populationCountry");
            double pibCountry = rs.getDouble("pibCountry");
            Country country = new Country(nameCountry, codeCountry, latitudeCountry, longitudeCountry, populationCountry, pibCountry, continent);

            //////////Organization//////
            String nameOrganization = rs.getString("nameOrganization");
            Organization organization = new Organization(nameOrganization);

            //////////Catalogo////////////
            String nameCatalog = rs.getString("nameCatalog");
            Catalog catalog = new Catalog(nameCatalog, organization);

            ///////Dataset//////
            String nameDataset = rs.getString("nameDataset");
            String descriptionDataset = rs.getString("descriptionDataset");
            String url = rs.getString("urlDataset");
            Date submited = rs.getDate("dateSubmitedDataset");
            Date modification = rs.getDate("dateModificationDataset");
            Dataset dataset = new Dataset(nameDataset, descriptionDataset, url, submited, modification, catalog);
            
            ///////Recovered//////
            String id = rs.getString("recoveredCode");
            Date date= rs.getDate("date");
            int recoveredQuantity = rs.getInt("recoveredQuantity");
            int recoveredTotalQuantity = rs.getInt("recoveredTotalQuantity");
            Recovered recovered = new Recovered(id, date, recoveredQuantity, recoveredTotalQuantity, country, dataset);
            lstrecuperados.add(recovered);

        }

        return lstrecuperados;
    }
    
    public ArrayList<Deaths> consultarFallecidos() throws SQLException, ClassNotFoundException {
        ArrayList<Deaths> lstfallecidos = new ArrayList<Deaths>();

        ResultSet rs = mp.ConsultarFallecidos();
        while (rs.next()) {

            //////Continent///////////
            String nameContinet = rs.getString("nameContinent");
            Continent continent = new Continent(nameContinet);

            //////Country/////////////
            String nameCountry = rs.getString("nameCountry");
            String codeCountry = rs.getString("codeCountry");
            double latitudeCountry = rs.getDouble("latitudeCountry");
            double longitudeCountry = rs.getDouble("longitudeCountry");
            int populationCountry = rs.getInt("populationCountry");
            double pibCountry = rs.getDouble("pibCountry");
            Country country = new Country(nameCountry, codeCountry, latitudeCountry, longitudeCountry, populationCountry, pibCountry, continent);

            //////////Organization//////
            String nameOrganization = rs.getString("nameOrganization");
            Organization organization = new Organization(nameOrganization);

            //////////Catalogo////////////
            String nameCatalog = rs.getString("nameCatalog");
            Catalog catalog = new Catalog(nameCatalog, organization);

            ///////Dataset//////
            String nameDataset = rs.getString("nameDataset");
            String descriptionDataset = rs.getString("descriptionDataset");
            String url = rs.getString("urlDataset");
            Date submited = rs.getDate("dateSubmitedDataset");
            Date modification = rs.getDate("dateModificationDataset");
            Dataset dataset = new Dataset(nameDataset, descriptionDataset, url, submited, modification, catalog);
            
            ///////Deths//////
            String id = rs.getString("deathsCode");
            Date date= rs.getDate("date");
            int deathsQuantity = rs.getInt("deathsQuantity");
            int deathsTotalQuantity = rs.getInt("deathsTotalQuantity");
            Deaths deaths = new Deaths(id, date, deathsQuantity, deathsTotalQuantity, country, dataset);
            lstfallecidos.add(deaths);

        }

        return lstfallecidos;
    }

}
