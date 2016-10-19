/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package soporte;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.*;
/**
 *
 * @author pablo
 */
public class PalablaJDBC
{

    public PalablaJDBC()
    {
    }
    
     public static void Insert(Palabra p)
    {

        try
        {
            Connection connection = abrirConexion();
            String sql = "INSERT INTO Palabra (palabra, frecuencia )  VALUES(?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
           
            preparedStmt.setString(1, p.getPalabra());
            preparedStmt.setInt(2, p.getFrecuencia());
            preparedStmt.executeUpdate();
            connection.commit();
            preparedStmt.close();
            connection.close();

        } catch (IOException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    private static Connection abrirConexion() throws IOException, ClassNotFoundException, SQLException
    {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:Vocabulario.sqlite");
        connection.setAutoCommit(false);
        return connection;
    }

}
