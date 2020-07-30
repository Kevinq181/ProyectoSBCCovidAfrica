package DAT;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
public class DATAdministracion {
   
    DATConexion c = new DATConexion();

    public ResultSet ConsultarCaso() throws ClassNotFoundException, SQLException{
        PreparedStatement pst = c.AbrirConexion().prepareStatement("SELECT * FROM ba3nhvdbwms6idduqhyf.case cas, continent con, country cou, province pro, place pla, patient pat, medicalinformation med, organization org, catalog cat, dataset dat WHERE 1=1 AND cou.nameContinent = con.nameContinent AND pro.nameCountry = cou.nameCountry AND pla.nameProvince = pro.nameProvince AND pat.codeMedicalInformation = med.codeMedicalInformation AND cat.nameOrganization = org.nameOrganization AND dat.nameCatalog = cat.nameCatalog and cas.namePlace = pla.namePlace and cas.patientCode = pat.patientCode and cas.nameDataset = dat.nameDataset;");//crea un a varible y ponermos la cadena para recuperar todo desde la BDD
        ResultSet rs = pst.executeQuery();//recuper un un ResultSet y envio la varible a executeQuery
        return rs;
    }
    
    public ResultSet ConsultarConfirmados() throws ClassNotFoundException, SQLException{
        PreparedStatement pst = c.AbrirConexion().prepareStatement("SELECT * FROM confirmed con, country cou, continent cont, dataset dat, catalog cat, organization org WHERE 1=1 AND con.nameCountry = cou.nameCountry AND cou.nameContinent = cont.nameContinent AND con.nameDataset = dat.nameDataset AND dat.nameCatalog = cat.nameCatalog AND cat.nameOrganization = org.nameOrganization;");
        ResultSet rs = pst.executeQuery();//recuper un un ResultSet y envio la varible a executeQuery
        return rs;
    }
    
    public ResultSet ConsultarRecuperados() throws ClassNotFoundException, SQLException{
        PreparedStatement pst = c.AbrirConexion().prepareStatement("SELECT * FROM recovered rec, country cou, continent cont, dataset dat, catalog cat, organization org WHERE 1=1 AND rec.nameCountry = cou.nameCountry AND cou.nameContinent = cont.nameContinent AND rec.nameDataset = dat.nameDataset AND dat.nameCatalog = cat.nameCatalog AND cat.nameOrganization = org.nameOrganization; ");
        ResultSet rs = pst.executeQuery();//recuper un un ResultSet y envio la varible a executeQuery
        return rs;
    }
    
    public ResultSet ConsultarFallecidos() throws ClassNotFoundException, SQLException{
        PreparedStatement pst = c.AbrirConexion().prepareStatement("SELECT * FROM deaths det, country cou, continent cont, dataset dat, catalog cat, organization org WHERE 1=1 AND det.nameCountry = cou.nameCountry AND cou.nameContinent = cont.nameContinent AND det.nameDataset = dat.nameDataset AND dat.nameCatalog = cat.nameCatalog AND cat.nameOrganization = org.nameOrganization; ");
        ResultSet rs = pst.executeQuery();//recuper un un ResultSet y envio la varible a executeQuery
        return rs;
    }
}

