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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Palabra;

/**
 *
 * @author pablo
 */
public class PalabraXDocumentoJDBC
{
    public static void Insert(int p, int d, Connection connection )
    {

        try
        {
           // Connection connection = abrirConexion();
            String sql = "INSERT INTO documentoXpalabra (id_documento, id_palabra) VALUES(?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
           
            preparedStmt.setInt(1, d);
            preparedStmt.setInt(2, p);
            preparedStmt.executeUpdate();
//            connection.commit();
            preparedStmt.close();
//            connection.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }
    public static boolean existeRelacion(int p, int d, Connection connection)
    {
       
         try
        {

//            Connection connection = abrirConexion();
            String sql = "SELECT * FROM documentoXpalabra WHERE id_palabra = ? and id_documento = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, p);
            statement.setInt(2, d);

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                return true;

            }
            result.close();
            statement.close();
//            connection.close();

        } //        catch (IOException ex)
        //        {
        //            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        //        catch (ClassNotFoundException ex)
        //        {
        //            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        //        }
        catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
        
    }
    private static Connection abrirConexion() throws IOException, ClassNotFoundException, SQLException
    {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:Vocabulario.sqlite");
        connection.setAutoCommit(false);
        return connection;
    }
}
