package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DocumentoJDBC
{

    public static int Insert(String doc, Connection connection)
    {
        int id = 0;
        
        try
        {
            String sql = "INSERT INTO Documento (documento)  VALUES(?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);

            preparedStmt.setString(1, doc);
            int cantidad = preparedStmt.executeUpdate();

            if (cantidad > 0)
            {
                preparedStmt.getGeneratedKeys().next();
                id= (int)preparedStmt.getGeneratedKeys().getLong(1);
            }
            preparedStmt.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }

        return id;

    }

    public static LinkedList<String> getDocumentos(int idP, Connection connection)
    {
        LinkedList<String> d = new LinkedList<>();
        try
        {
            String sql = "select d.documento from  Documento d join DocumentoXPalabra dp on d.id_documento = dp.id_documento where dp.id_palabra = ? ";
            PreparedStatement statement = connection.prepareStatement(sql, ResultSet.TYPE_FORWARD_ONLY, ResultSet.CONCUR_READ_ONLY);
            statement.setInt(1, idP);

            ResultSet result = statement.executeQuery();
            while (result.next())
            {
                d.add(result.getString(1));
            }
            result.close();
            statement.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(DocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return d;

    }

    public static int getIdDocumento(String doc, Connection connection)
    {
        int id = 0;
        try
        {

            String sql = "SELECT id_documento FROM Documento WHERE documento = ?";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, doc);

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

      

}
