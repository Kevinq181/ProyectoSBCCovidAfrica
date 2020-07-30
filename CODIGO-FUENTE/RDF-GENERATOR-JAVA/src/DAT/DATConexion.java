package DAT;

import java.sql.*;

public class DATConexion 
{
    //Conectarse a la BDD
    public static Connection con;//obj tipo Conecction
    
    public Connection getConnection () throws ClassNotFoundException, SQLException{
        String driver="com.mysql.jdbc.Driver";
        String user="utryrvfltogk6nxf";
        String password="GiuNPbVgQ1DiYrg0oSb8";
        String url="jdbc:mysql://utryrvfltogk6nxf:GiuNPbVgQ1DiYrg0oSb8@ba3nhvdbwms6idduqhyf-mysql.services.clever-cloud.com:20013/ba3nhvdbwms6idduqhyf?zeroDateTimeBehavior=convertToNull";
        Class.forName(driver) ;//Diver jdbc para trabajar con access
        con =DriverManager.getConnection(url,user,password);
        return con;//retorna la cioneccion url+ruta bd//retorna la cioneccion url+ruta bd
    }
    //Objeto tipo Connection para majenar la conecion
    public Connection AbrirConexion() throws ClassNotFoundException, SQLException
    {
        con = getConnection();
        return con;
    }
    //cerrar la coneccion 
    public void CerrarConexion() throws SQLException
    {
       con.close();
    }
}

