package oogheelkunde;

import java.io.PrintWriter;
import static java.lang.System.out;
import java.sql.Connection;
import static java.sql.JDBCType.NULL;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/** Database Object voor Oogheelkunde database
 * author*/

public class Oogheelkunde
{			   
    private DataSource dataSource;
    private Patient patient;
    private Tokens token;

    
    	
    public Oogheelkunde(DataSource dataSource)
    {        
        this.dataSource = dataSource;

    }

    /** Voegt nieuwe patient aan database toe. 
     * @param patientnr
     * @param voornaam
     * @param achternaam
     * @param geslacht
     * @param geboortedatum
     * @param emailadres
     * @param telefoonnummer
     * @param operatie
     * @return true als de patient is toegevoegd, anders false
     * @throws java.sql.SQLException */
    public boolean voegPatientToe(int identificatienummer, int patientnr, String voornaam, String achternaam, String geslacht,
            String geboortedatum, String emailadres, String telefoonnummer, String operatie)
            throws SQLException
    {  
        
        try (Connection connection = dataSource.getConnection(); 
             PreparedStatement insertPatientStmt = connection.prepareStatement(
             "INSERT INTO Patient(identificatienummer, patientnr, voornaam, achternaam, geslacht, geboortedatum, emailadres, telefoonnummer, operatie) VALUES (?, ?, ?, ?, ?, ?, ?, ?)")) 
        {            
            connection.setAutoCommit(false);
            connection.setTransactionIsolation(Connection.TRANSACTION_SERIALIZABLE); 

            insertPatientStmt.setInt(1, identificatienummer);
            insertPatientStmt.setInt(2, patientnr);
            insertPatientStmt.setString(3, voornaam);
            insertPatientStmt.setString(4, achternaam);
            if(geslacht == null)
                insertPatientStmt.setInt(5, 1);
            else
            {
                if(geslacht.equals("man"))
                    insertPatientStmt.setInt(5, 1);
                else
                    insertPatientStmt.setInt(5, 2);
            }
            
            insertPatientStmt.setString(6, geboortedatum);
            insertPatientStmt.setString(7, emailadres);
            insertPatientStmt.setString(8, telefoonnummer);
            if(operatie == null)
                insertPatientStmt.setInt(9, 1);
            else
            {
                if(operatie.equals("pre"))
                    insertPatientStmt.setInt(9, 1);
                else
                    insertPatientStmt.setInt(9, 2);
            }
        
            insertPatientStmt.executeUpdate();
            
            connection.commit();
            connection.setAutoCommit(true);
        }

        return true;
    }

   

     /**
      * Geeft token terug
      * @param identificatienummer
      * @return token. Dit is een reeks met getallen en cijfers. 
      * @throws SQLException 
      */
     public Tokens getToken(int identificatienummer)throws SQLException
     { 
         try (Connection connection = dataSource.getConnection()){ 
            PreparedStatement getToken = connection.prepareStatement(
            "SELECT token FROM Tokens WHERE identificatienummer= "+identificatienummer);
                     
            connection.setAutoCommit(false);
            ResultSet result = getToken.executeQuery();
          result.next();
          token= new Tokens(result.getString("token"),result.getInt("identificatienummer"));
          return token;
        }
     }
    
   
}

  
 
           
