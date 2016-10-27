package soporte;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import negocio.Palabra;

public class Persistencia
{

    public void guardarEnBD(Palabra p, Connection connection)
    {

        try
        {
            PalablaJDBC.Insert(p, connection);

        } catch (Exception e)
        {
            Logger.getLogger(Persistencia.class.getName()).log(Level.SEVERE, null, e);

        }
    }

    public static LinkedList<Palabra> getAllPalabras(Connection connection)
    {
        return PalablaJDBC.getAll(connection);
    }

    public static ArrayList<Palabra> getByFilterPalabras(String palabra)
    {
        return PalablaJDBC.getByFilter(palabra);
    }

    public Connection abrirConexion() throws IOException, ClassNotFoundException, SQLException
    {

        Class.forName("org.sqlite.JDBC");
        Connection connection = DriverManager.getConnection("jdbc:sqlite:Vocabulario.sqlite");
        connection.setAutoCommit(false);
        return connection;
    }
}
