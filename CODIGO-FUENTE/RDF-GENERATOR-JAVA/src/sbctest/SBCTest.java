package sbctest;

import BL.BLAdministracion;
import Clases.Case;
import Clases.Confirmed;
import Clases.Deaths;
import java.io.FileNotFoundException;
import java.sql.SQLException;
import java.util.ArrayList;
import Clases.Recovered;
import java.io.File;
import java.io.FileOutputStream;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.rdf.model.Property;
import org.apache.jena.rdf.model.RDFNode;
import org.apache.jena.rdf.model.Resource;
import org.apache.jena.rdf.model.Statement;
import org.apache.jena.rdf.model.StmtIterator;
import org.apache.jena.vocabulary.RDF;
import org.apache.jena.sparql.vocabulary.FOAF;
import org.apache.jena.rdf.model.RDFWriter;
import org.apache.jena.vocabulary.RDFS;

public class SBCTest {

    static String GenFilePath = "C:\\Users\\kevin\\Documents\\NetBeansProjects\\covid1.rdf";

    public static void main(String[] args) throws ClassNotFoundException, SQLException, FileNotFoundException {

        BLAdministracion manejador = new BLAdministracion();
        ArrayList<Case> lstcases = new ArrayList<>();
        lstcases = manejador.consultarCaso();
        ArrayList<Confirmed> lstconfirmed = new ArrayList<>();
        lstconfirmed = manejador.consultarConfirmados();
        ArrayList<Recovered> lstrecovered = new ArrayList<>();
        lstrecovered = manejador.consultarRecuperados();
        ArrayList<Deaths> lstdeaths = new ArrayList<>();
        lstdeaths = manejador.consultarFallecidos();

        Model model = ModelFactory.createDefaultModel();
        File f = new File(GenFilePath); //File to save the results of RDF Generation
        FileOutputStream os = new FileOutputStream(f);

        //Set prefix for the URI base (data)
        String dataPrefix = "http://utpl.edu.ec/lod/dataCOVID/";
        model.setNsPrefix("data", dataPrefix);

        //Vocab and models present in JENA
        //SCHEMA
        String schema = "http://schema.org/";
        model.setNsPrefix("schema", schema);
        Model schemaModel = ModelFactory.createDefaultModel();
        //Dbpedia Ontology- DBO
        String dbo = "http://dbpedia.org/ontology/";
        model.setNsPrefix("dbo", dbo);
        Model dboModel = ModelFactory.createDefaultModel();
        //Geonames - gn
        String gn = "http://www.geonames.org/ontology#";
        model.setNsPrefix("gn", gn);
        Model gnModel = ModelFactory.createDefaultModel();
        //Dublincore - DBR
        String dc = "http://purl.org/dc/elements/1.1/";
        model.setNsPrefix("dc", dc);
        Model dcModel = ModelFactory.createDefaultModel();
        //DCat - dcat
        String dcat = "http://www.w3.org/ns/dcat#";
        model.setNsPrefix("dcat", dcat);
        Model dcatModel = ModelFactory.createDefaultModel();
        //OpenVocabulary - ov
        String ov = "http://open.vocab.org/terms/";
        model.setNsPrefix("ov", ov);
        Model ovModel = ModelFactory.createDefaultModel();
        //Prov - prov
        String prov = "http://www.w3.org/ns/prov#";
        model.setNsPrefix("prov", prov);
        Model provModel = ModelFactory.createDefaultModel();
        //SIO - sio
        String sio = "http://semanticscience.org/resource/";
        model.setNsPrefix("sio", sio);
        Model sioModel = ModelFactory.createDefaultModel();
        //newOnto - newOnto
        String newOnto = "http://utpl.edu.ec/lod/dataCOVID/ontology/";
        model.setNsPrefix("newOnto", newOnto);
        Model newOntoModel = ModelFactory.createDefaultModel();

        for (Case ls : lstcases) {
            /*Informacion médica*/
            Resource rmi = model.createResource(dataPrefix + ls.getPatient().getMedicalInformation().getId())
                    .addProperty(RDF.type, newOntoModel.getProperty(newOnto, "Medical_Information"));
            if (ls.getPatient().getMedicalInformation().getDateFirstSymptom() != "null") {
                rmi.addProperty(newOntoModel.getProperty(newOnto, "date_first_symptom"), ls.getPatient().getMedicalInformation().getDateFirstSymptom());
            }
            if (ls.getPatient().getMedicalInformation().getTravelHistoryDate() != "null") {
                rmi.addProperty(newOntoModel.getProperty(newOnto, "travel_history_date"), ls.getPatient().getMedicalInformation().getTravelHistoryDate());
            }
            if (!ls.getPatient().getMedicalInformation().getTravelHistoryLocation().isEmpty() ) {
                rmi.addProperty(newOntoModel.getProperty(newOnto, "travel_history_location"), ls.getPatient().getMedicalInformation().getTravelHistoryLocation());
            }
            if (ls.getPatient().getMedicalInformation().getDeathDate() != "null") {
                rmi.addProperty(dboModel.getProperty(dbo, "deathDate"), ls.getPatient().getMedicalInformation().getDeathDate());
            }
            if (!ls.getPatient().getMedicalInformation().getCurrentStatus().isEmpty()) {
                rmi.addProperty(dboModel.getProperty(dbo, "currentStatus"), ls.getPatient().getMedicalInformation().getCurrentStatus());

            }
            /*Paciente*/
            Resource pat = model.createResource(dataPrefix + ls.getPatient().getId())
                    .addProperty(RDF.type, sioModel.getProperty(sio, "Patient"));
            if (ls.getPatient().getAge() != 0) {
                pat.addProperty(FOAF.age, String.valueOf(ls.getPatient().getAge()));
            }
            if (!ls.getPatient().getGender().isEmpty()) {
                pat.addProperty(FOAF.gender, String.valueOf(ls.getPatient().getGender()));

            }
            pat.addProperty(newOntoModel.getProperty(newOnto, "hasData"), rmi);

            /*Continente*/
            Resource con = model.createResource(dataPrefix + ls.getPlace().getProvince().getCountry().getContinent().getNombre())
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Continent"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getPlace().getProvince().getCountry().getContinent().getNombre());

            /*Country*/
            String country = ls.getPlace().getProvince().getCountry().getName().replaceAll(" ", "_");
            Resource cou = model.createResource(dataPrefix + country)
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Country"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getPlace().getProvince().getCountry().getName())
                    .addProperty(gnModel.getProperty(gn, "countryCode"), ls.getPlace().getProvince().getCountry().getCode())
                    .addProperty(schemaModel.getProperty(schema, "latitude"), String.valueOf(ls.getPlace().getProvince().getCountry().getLatitude()))
                    .addProperty(schemaModel.getProperty(schema, "longitude"), String.valueOf(ls.getPlace().getProvince().getCountry().getLongitude()))
                    .addProperty(dboModel.getProperty(dbo, "populationTotal"), String.valueOf(ls.getPlace().getProvince().getCountry().getPopulation()))
                    .addProperty(dboModel.getProperty(dbo, "grossDomesticProductNominalPerCapita"), String.valueOf(ls.getPlace().getProvince().getCountry().getPib()));
            con.addProperty(dboModel.getProperty(dbo, "country"), cou);

            /*Province*/
            String province = ls.getPlace().getProvince().getName().replaceAll(" ", "_");
            Resource pro = model.createResource(dataPrefix + province)
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Province"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getPlace().getProvince().getName());
            cou.addProperty(dboModel.getProperty(dbo, "province"), pro);

            /*Place*/
            String place = ls.getPlace().getName().replaceAll(" ", "_");
            Resource pla = model.createResource(dataPrefix + place + "_city")
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Place"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getPlace().getName());
            /*.addProperty(gnModel.getProperty(gn, "countryCode"), ls.getPlace().getCode())
                    .addProperty(schemaModel.getProperty(schema, "latitude"), String.valueOf(ls.getPlace().getLatitude()))
                    .addProperty(schemaModel.getProperty(schema, "longitude"), String.valueOf(ls.getPlace().getLongitude()))
                    .addProperty(dboModel.getProperty(dbo, "populationTotal"), String.valueOf(ls.getPlace().getPopulation()))
                    .addProperty(dboModel.getProperty(dbo, "grossDomesticProductNominalPerCapita"), String.valueOf(ls.getPlace().getPib()));*/
            pro.addProperty(dboModel.getProperty(dbo, "place"), pla);

            /*Organizacion*/
            String organizacion = ls.getDataset().getCatalog().getOrganization().getNombre().replaceAll(" ", "_");
            Resource org = model.createResource(dataPrefix + organizacion)
                    .addProperty(RDF.type, FOAF.Organization)
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getDataset().getCatalog().getOrganization().getNombre());

            /*Catalogo*/
            String catalogo = ls.getDataset().getCatalog().getName().replaceAll(" ", "_");
            Resource cat = model.createResource(dataPrefix + catalogo)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Catalog"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getDataset().getCatalog().getName());
            cat.addProperty(dcatModel.getProperty(dcat, "publisher"), org);

            /*Dataset*/
            String dataset = ls.getDataset().getName().replaceAll(" ", "_");
            Resource dat = model.createResource(dataPrefix + dataset)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Dataset"))
                    .addProperty(dboModel.getProperty(dbo, "name"), ls.getDataset().getName())
                    .addProperty(dboModel.getProperty(dbo, "description"), ls.getDataset().getDescription())
                    .addProperty(dcatModel.getProperty(dcat, "downloadURL"), ls.getDataset().getUrl())
                    .addProperty(dcModel.getProperty(dc, "dateSubmited"), String.valueOf(ls.getDataset().getSubmited()))
                    .addProperty(dcatModel.getProperty(dcat, "dateModification"), String.valueOf(ls.getDataset().getModificaction()));
            cat.addProperty(dcatModel.getProperty(dcat, "dataset"), dat);

            Resource cas = model.createResource(dataPrefix + ls.getId())
                    .addProperty(RDF.type, newOntoModel.getProperty(newOnto, "CaseCovid"));
            if (ls.getConfirmed() != null) {
                cas.addProperty(newOntoModel.getProperty(newOnto, "confirmationDate"), String.valueOf(ls.getConfirmed()));

            }
            cas.addProperty(newOntoModel.getProperty(newOnto, "hasData"), pat);
            cas.addProperty(provModel.getProperty(prov, "wasDerivedFrom"), dat);
            cas.addProperty(gnModel.getProperty(gn, "locatedIn"), pla);
        }

        
        

        for (Confirmed lstcon : lstconfirmed) {
            /*Organizacion*/
            String organizacion2 = lstcon.getDataset().getCatalog().getOrganization().getNombre().replaceAll(" ", "_");
            Resource org2 = model.createResource(dataPrefix + organizacion2)
                    .addProperty(RDF.type, FOAF.Organization)
                    .addProperty(dboModel.getProperty(dbo, "name"), lstcon.getDataset().getCatalog().getOrganization().getNombre());

            /*Catalogo*/
            String catalogo2 = lstcon.getDataset().getCatalog().getName().replaceAll(" ", "_");
            Resource cat2 = model.createResource(dataPrefix + catalogo2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Catalog"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstcon.getDataset().getCatalog().getName());
            cat2.addProperty(dcatModel.getProperty(dcat, "publisher"), org2);

            /*Dataset*/
            String dataset2 = lstcon.getDataset().getName().replaceAll(" ", "_");
            Resource dat2 = model.createResource(dataPrefix + dataset2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Dataset"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstcon.getDataset().getName())
                    .addProperty(dboModel.getProperty(dbo, "description"), lstcon.getDataset().getDescription())
                    .addProperty(dcatModel.getProperty(dcat, "downloadURL"), lstcon.getDataset().getUrl())
                    .addProperty(dcModel.getProperty(dc, "dateSubmited"), String.valueOf(lstcon.getDataset().getSubmited()))
                    .addProperty(dcatModel.getProperty(dcat, "dateModification"), String.valueOf(lstcon.getDataset().getModificaction()));
            cat2.addProperty(dcatModel.getProperty(dcat, "dataset"), dat2);

            /*Continente*/
            Resource con2 = model.createResource(dataPrefix + lstcon.getCountry().getContinent().getNombre())
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Continent"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstcon.getCountry().getContinent().getNombre());

            /*Country*/
            String country2 = lstcon.getCountry().getName().replaceAll(" ", "_");
            Resource cou2 = model.createResource(dataPrefix + country2)
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Country"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstcon.getCountry().getName())
                    .addProperty(gnModel.getProperty(gn, "countryCode"), lstcon.getCountry().getCode())
                    .addProperty(schemaModel.getProperty(schema, "latitude"), String.valueOf(lstcon.getCountry().getLatitude()))
                    .addProperty(schemaModel.getProperty(schema, "longitude"), String.valueOf(lstcon.getCountry().getLongitude()))
                    .addProperty(dboModel.getProperty(dbo, "populationTotal"), String.valueOf(lstcon.getCountry().getPopulation()))
                    .addProperty(dboModel.getProperty(dbo, "grossDomesticProductNominalPerCapita"), String.valueOf(lstcon.getCountry().getPib()));
            con2.addProperty(dboModel.getProperty(dbo, "country"), cou2);

            /*Confirmed*/
            Resource conf = model.createResource(dataPrefix + lstcon.getId())
                    .addProperty(RDF.type, newOntoModel.getProperty(newOnto, "Confirmed_Cases"))
                    .addProperty(schemaModel.getProperty(schema, "observationDate"), String.valueOf(lstcon.getDate()))
                    .addProperty(newOntoModel.getProperty(newOnto, "quantity"), String.valueOf(lstcon.getConfirmedCantidad()))
                    .addProperty(newOntoModel.getProperty(newOnto, "totalQuantity"), String.valueOf(lstcon.getConfirmedCantidadTotal()));
            conf.addProperty(gnModel.getProperty(gn, "locatedIn"), cou2);
            conf.addProperty(provModel.getProperty(prov, "wasDerivedFrom"), dat2);
            

        }

        for (Recovered lstrec : lstrecovered) {
            /*Organizacion*/
            String organizacion2 = lstrec.getDataset().getCatalog().getOrganization().getNombre().replaceAll(" ", "_");
            Resource org2 = model.createResource(dataPrefix + organizacion2)
                    .addProperty(RDF.type, FOAF.Organization)
                    .addProperty(dboModel.getProperty(dbo, "name"), lstrec.getDataset().getCatalog().getOrganization().getNombre());

            /*Catalogo*/
            String catalogo2 = lstrec.getDataset().getCatalog().getName().replaceAll(" ", "_");
            Resource cat2 = model.createResource(dataPrefix + catalogo2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Catalog"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstrec.getDataset().getCatalog().getName());
            cat2.addProperty(dcatModel.getProperty(dcat, "publisher"), org2);

            /*Dataset*/
            String dataset2 = lstrec.getDataset().getName().replaceAll(" ", "_");
            Resource dat2 = model.createResource(dataPrefix + dataset2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Dataset"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstrec.getDataset().getName())
                    .addProperty(dboModel.getProperty(dbo, "description"), lstrec.getDataset().getDescription())
                    .addProperty(dcatModel.getProperty(dcat, "downloadURL"), lstrec.getDataset().getUrl())
                    .addProperty(dcModel.getProperty(dc, "dateSubmited"), String.valueOf(lstrec.getDataset().getSubmited()))
                    .addProperty(dcatModel.getProperty(dcat, "dateModification"), String.valueOf(lstrec.getDataset().getModificaction()));
            cat2.addProperty(dcatModel.getProperty(dcat, "dataset"), dat2);

            /*Continente*/
            Resource con2 = model.createResource(dataPrefix + lstrec.getCountry().getContinent().getNombre())
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Continent"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstrec.getCountry().getContinent().getNombre());

            /*Country*/
            String country2 = lstrec.getCountry().getName().replaceAll(" ", "_");
            Resource cou2 = model.createResource(dataPrefix + country2)
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Country"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstrec.getCountry().getName())
                    .addProperty(gnModel.getProperty(gn, "countryCode"), lstrec.getCountry().getCode())
                    .addProperty(schemaModel.getProperty(schema, "latitude"), String.valueOf(lstrec.getCountry().getLatitude()))
                    .addProperty(schemaModel.getProperty(schema, "longitude"), String.valueOf(lstrec.getCountry().getLongitude()))
                    .addProperty(dboModel.getProperty(dbo, "populationTotal"), String.valueOf(lstrec.getCountry().getPopulation()))
                    .addProperty(dboModel.getProperty(dbo, "grossDomesticProductNominalPerCapita"), String.valueOf(lstrec.getCountry().getPib()));
            con2.addProperty(dboModel.getProperty(dbo, "country"), cou2);

            /*Recovered*/
            Resource conf = model.createResource(dataPrefix + lstrec.getId())
                    .addProperty(RDF.type, newOntoModel.getProperty(newOnto, "Recovered_Cases"))
                    .addProperty(schemaModel.getProperty(schema, "observationDate"), String.valueOf(lstrec.getDate()))
                    .addProperty(newOntoModel.getProperty(newOnto, "quantity"), String.valueOf(lstrec.getRecoveredCantidad()))
                    .addProperty(newOntoModel.getProperty(newOnto, "totalQuantity"), String.valueOf(lstrec.getRecoveredCantidadTotal()));
            conf.addProperty(gnModel.getProperty(gn, "locatedIn"), cou2);
            conf.addProperty(provModel.getProperty(prov, "wasDerivedFrom"), dat2);
            

        }

        for (Deaths lstdet : lstdeaths) {
            /*Organizacion*/
            String organizacion2 = lstdet.getDataset().getCatalog().getOrganization().getNombre().replaceAll(" ", "_");
            Resource org2 = model.createResource(dataPrefix + organizacion2)
                    .addProperty(RDF.type, FOAF.Organization)
                    .addProperty(dboModel.getProperty(dbo, "name"), lstdet.getDataset().getCatalog().getOrganization().getNombre());

            /*Catalogo*/
            String catalogo2 = lstdet.getDataset().getCatalog().getName().replaceAll(" ", "_");
            Resource cat2 = model.createResource(dataPrefix + catalogo2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Catalog"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstdet.getDataset().getCatalog().getName());
            cat2.addProperty(dcatModel.getProperty(dcat, "publisher"), org2);

            /*Dataset*/
            String dataset2 = lstdet.getDataset().getName().replaceAll(" ", "_");
            Resource dat2 = model.createResource(dataPrefix + dataset2)
                    .addProperty(RDF.type, dcatModel.getProperty(dcat, "Dataset"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstdet.getDataset().getName())
                    .addProperty(dboModel.getProperty(dbo, "description"), lstdet.getDataset().getDescription())
                    .addProperty(dcatModel.getProperty(dcat, "downloadURL"), lstdet.getDataset().getUrl())
                    .addProperty(dcModel.getProperty(dc, "dateSubmited"), String.valueOf(lstdet.getDataset().getSubmited()))
                    .addProperty(dcatModel.getProperty(dcat, "dateModification"), String.valueOf(lstdet.getDataset().getModificaction()));
            cat2.addProperty(dcatModel.getProperty(dcat, "dataset"), dat2);

            /*Continente*/
            Resource con2 = model.createResource(dataPrefix + lstdet.getCountry().getContinent().getNombre())
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Continent"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstdet.getCountry().getContinent().getNombre());

            /*Country*/
            String country2 = lstdet.getCountry().getName().replaceAll(" ", "_");
            Resource cou2 = model.createResource(dataPrefix + country2)
                    .addProperty(RDF.type, dboModel.getProperty(dbo, "Country"))
                    .addProperty(dboModel.getProperty(dbo, "name"), lstdet.getCountry().getName())
                    .addProperty(gnModel.getProperty(gn, "countryCode"), lstdet.getCountry().getCode())
                    .addProperty(schemaModel.getProperty(schema, "latitude"), String.valueOf(lstdet.getCountry().getLatitude()))
                    .addProperty(schemaModel.getProperty(schema, "longitude"), String.valueOf(lstdet.getCountry().getLongitude()))
                    .addProperty(dboModel.getProperty(dbo, "populationTotal"), String.valueOf(lstdet.getCountry().getPopulation()))
                    .addProperty(dboModel.getProperty(dbo, "grossDomesticProductNominalPerCapita"), String.valueOf(lstdet.getCountry().getPib()));
            con2.addProperty(dboModel.getProperty(dbo, "country"), cou2);

            /*Deaths*/
            Resource conf = model.createResource(dataPrefix + lstdet.getId())
                    .addProperty(RDF.type, newOntoModel.getProperty(newOnto, "Deaths_Cases"))
                    .addProperty(schemaModel.getProperty(schema, "observationDate"), String.valueOf(lstdet.getDate()))
                    .addProperty(newOntoModel.getProperty(newOnto, "quantity"), String.valueOf(lstdet.getDeathsCantidad()))
                    .addProperty(newOntoModel.getProperty(newOnto, "totalQuantity"), String.valueOf(lstdet.getDeathsCantidadTotal()));
            conf.addProperty(gnModel.getProperty(gn, "locatedIn"), cou2);
            conf.addProperty(provModel.getProperty(prov, "wasDerivedFrom"), dat2);
            ;

        }

        /**
         * Reading the Generated data in Triples Format and RDF
         */
        StmtIterator iter = model.listStatements();
        System.out.println("TRIPLES");
        while (iter.hasNext()) {
            Statement stmt = iter.nextStatement();  // get next statement
            Resource subject = stmt.getSubject();     // get the subject
            Property predicate = stmt.getPredicate();   // get the predicate
            RDFNode object = stmt.getObject();      // get the object

            System.out.print(subject.toString());
            System.out.print(" " + predicate.toString() + " ");
            if (object instanceof Resource) {
                System.out.print(object.toString());
            } else {
                // object is a literal
                System.out.print(" \"" + object.toString() + "\"");
            }

            System.out.println(" .");
        }
        // now write the model in XML form to a file
        System.out.println("MODELO RDF------");
        model.write(System.out, "RDF/XML-ABBREV");

        // Save to a file
        RDFWriter writer = model.getWriter("RDF/XML");
        writer.write(model, os, "");

        //Close models
        dboModel.close();
        model.close();

    }

}
