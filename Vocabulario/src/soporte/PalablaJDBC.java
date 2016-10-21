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
import java.util.LinkedList;
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

    public static ArrayList<Palabra> getAll()
    {
        Palabra p;
        ArrayList<Palabra> lista = new ArrayList<>();
        LinkedList<String> doc = new LinkedList<>();
        try
        {

            Connection connection = abrirConexion();
            String sql = "select id_palabra,  palabra, frecuencia from  Palabra ";
            PreparedStatement statement = connection.prepareStatement(sql);
            

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
               
                p =new Palabra(result.getString(2), result.getInt(3), DocumentoJDBC.getDocumentos(result.getInt(1),connection));
                lista.add(p);

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

        return lista;
    }
    public static ArrayList<Palabra> getByFilter(String palabra)
    {
     
         Palabra p;
        ArrayList<Palabra> lista = new ArrayList<>();
        LinkedList<String> doc = new LinkedList<>();
        try
        {

            Connection connection = abrirConexion();
            String sql = "select id_palabra,  palabra, frecuencia from  Palabra WHERE  palabra like ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, palabra+"%");

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
               
              p =new Palabra(result.getString(2), result.getInt(3), DocumentoJDBC.getDocumentos(result.getInt(1),connection));
                lista.add(p);
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

        return lista;
    }

    public static int Insert(Palabra p)
    {
        ArrayList<Integer> idDoc = new ArrayList<>();

        int id = 0;

        try
        {
            Connection connection = abrirConexion();
            if (getIdPalabra(p.getPalabra(), connection) == 0)
            {
                for (String pal : p.getDocumentos())
                {
                    if (DocumentoJDBC.getIdDocumento(pal, connection) == 0)
                    {
                        idDoc.add(DocumentoJDBC.Insert(pal, connection));

                    } else
                    {
                        idDoc.add(DocumentoJDBC.getIdDocumento(pal, connection));
                    }
                }

//                Connection connection = abrirConexion();
                String sql = "INSERT INTO Palabra (palabra, frecuencia )  VALUES(?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);

                preparedStmt.setString(1, p.getPalabra());
                preparedStmt.setInt(2, p.getFrecuencia());
                preparedStmt.executeUpdate();
                id = getIdPalabra(p.getPalabra(), connection);
                for (Integer integer : idDoc)
                {
                    PalabraXDocumentoJDBC.Insert(id, integer, connection);
                }
//                connection.commit();
                preparedStmt.close();
//                connection.close();

            } else
            {
//                Connection connection = abrirConexion();
                upDate(p, connection);

            }
            connection.commit();
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

    public static void upDate(Palabra p, Connection connection)
    {
        ArrayList<Integer> idDoc = new ArrayList<>();
        try
        {
            //Connection connection = abrirConexion();
            String sql = "UPDATE Palabra set frecuencia = ? WHERE palabra = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);

            preparedStmt.setString(2, p.getPalabra());
            preparedStmt.setInt(1, p.getFrecuencia());
            preparedStmt.executeUpdate();
            preparedStmt.close();

//            connection.commit();
            for (String pal : p.getDocumentos())
            {
                if (DocumentoJDBC.getIdDocumento(pal, connection) == 0)
                {

                    PalabraXDocumentoJDBC.Insert(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.Insert(pal, connection), connection);

                }
                else
                {
                    
                    if (!PalabraXDocumentoJDBC.existeRelacion(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.getIdDocumento(pal, connection), connection))
                    {
                        PalabraXDocumentoJDBC.Insert(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.getIdDocumento(pal, connection), connection);                        
                    }
                }
            }
            
            
//            connection.close();
        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public static int getIdPalabra(String palabra, Connection connection)
    {
        int id = 0;
        try
        {

//            Connection connection = abrirConexion();
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
