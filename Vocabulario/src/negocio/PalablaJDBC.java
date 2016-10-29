package negocio;

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

public class PalablaJDBC
{

    public PalablaJDBC()
    {
    }

    public static LinkedList<Palabra> getAll(Connection connection)
    {
        Palabra p;
        LinkedList<Palabra> lista = new LinkedList<>();
        try
        {
            String sql = "select id_palabra,  palabra, frecuencia from  Palabra ";
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);

            ResultSet result = statement.executeQuery();

            while (result.next())
            {
                p = new Palabra(result.getString(2), result.getInt(3), DocumentoJDBC.getDocumentos(result.getInt(1), connection));
                lista.add(p);

            }
            result.close();
            statement.close();

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

        try
        {

            Connection connection = abrirConexion();
            String sql = "select id_palabra,  palabra, frecuencia from  Palabra WHERE  palabra like ? ";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, palabra + "%");

            ResultSet result = statement.executeQuery();
            while (result.next())
            {

                p = new Palabra(result.getString(2), result.getInt(3), DocumentoJDBC.getDocumentos(result.getInt(1), connection));
                lista.add(p);
            }
            result.close();
            statement.close();
            connection.close();

        } catch (IOException | ClassNotFoundException | SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return lista;
    }

    public static int Insert(Palabra p, Connection connection)
    {
        ArrayList<Integer> idDoc = new ArrayList<>();

        int id = 0;

        try
        {

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

                String sql = "INSERT INTO Palabra (palabra, frecuencia )  VALUES(?,?)";
                PreparedStatement preparedStmt = connection.prepareStatement(sql);

                preparedStmt.setString(1, p.getPalabra());
                preparedStmt.setInt(2, p.getFrecuencia());
                int cantidad = preparedStmt.executeUpdate();

                if (cantidad > 0)
                {
                    preparedStmt.getGeneratedKeys().next();
                    id = (int) preparedStmt.getGeneratedKeys().getLong(1);
                }

                for (Integer integer : idDoc)
                {
                    PalabraXDocumentoJDBC.Insert(id, integer, connection);
                }

                preparedStmt.close();


            } else
            {

                upDate(p, connection);

            }


        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    public static void upDate(Palabra p, Connection connection)
    {
        try
        {
            String sql = "UPDATE Palabra set frecuencia = ? WHERE palabra = ?";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);

            preparedStmt.setString(2, p.getPalabra());
            preparedStmt.setInt(1, p.getFrecuencia());
            preparedStmt.executeUpdate();
            preparedStmt.close();


            for (String pal : p.getDocumentos())
            {
                if (DocumentoJDBC.getIdDocumento(pal, connection) == 0)
                {
                    PalabraXDocumentoJDBC.Insert(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.Insert(pal, connection), connection);

                } else if (!PalabraXDocumentoJDBC.existeRelacion(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.getIdDocumento(pal, connection), connection))
                {
                    PalabraXDocumentoJDBC.Insert(PalablaJDBC.getIdPalabra(p.getPalabra(), connection), DocumentoJDBC.getIdDocumento(pal, connection), connection);
                }
            }


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

        } 
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
