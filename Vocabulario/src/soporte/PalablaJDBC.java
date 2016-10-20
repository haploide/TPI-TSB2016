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
import java.util.ArrayList;
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

    public static int Insert(Palabra p)
    {
        ArrayList<Integer> idDoc = new ArrayList<>();
    

        int id = 0;

        try
        {
            if (getIdPalabra(p.getPalabra()) == 0)
            {
            for (String pal : p.getDocumentos())
            {
                if (DocumentoJDBC.getIdDocumento(pal) == 0)
                {
                    idDoc.add(DocumentoJDBC.Insert(pal));
                    

                } else
                {
                    idDoc.add(DocumentoJDBC.getIdDocumento(pal));
                }
            }
           
                Connection connection = abrirConexion();
                String sql = "INSERT INTO Palabra (palabra, frecuencia )  VALUES(?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);

                preparedStmt.setString(1, p.getPalabra());
                preparedStmt.setInt(2, p.getFrecuencia());
                preparedStmt.executeUpdate();
                id = getIdPalabra(p.getPalabra());
                for (Integer integer : idDoc)
                {
                    PalabraXDocumentoJDBC.Insert(id, integer);
                }
                connection.commit();
                preparedStmt.close();
                connection.close();

            } else
            {
                upDate(p);
                
            }

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

        return id;

    }

    public static void upDate(Palabra p)
    {
        try
        { 
        Connection connection = abrirConexion();
        String sql = "UPDATE Palabra set frecuencia = ? WHERE palabra = ?";
        PreparedStatement preparedStmt = connection.prepareStatement(sql);

        preparedStmt.setString(2, p.getPalabra());
        preparedStmt.setInt(1, p.getFrecuencia());
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

    public static int getIdPalabra(String palabra)
    {
        int id = 0;
        try
        {

            Connection connection = abrirConexion();
            String sql = "SELECT id_palabra FROM Palabra WHERE palabra = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, palabra);

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                id = result.getInt(1);

            }
            result.close();
            statement.close();
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
        return id;
    }

    public static int getFrecuencia(String palabra)
    {
        int fre = 0;
        try
        {

            Connection connection = abrirConexion();
            String sql = "SELECT frecuencia FROM Palabra WHERE palabra = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, palabra);

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                fre = result.getInt(1);

            }
            result.close();
            statement.close();
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
        return fre;
    }

    private static Connection abrirConexion() throws IOException, ClassNotFoundException, SQLException
    {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:Vocabulario.sqlite");
        connection.setAutoCommit(false);
        return connection;
    }

}
