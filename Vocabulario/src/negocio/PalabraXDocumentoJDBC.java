
package negocio;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class PalabraXDocumentoJDBC
{
    public static void Insert(int p, int d, Connection connection )
    {
        try
        {
            String sql = "INSERT INTO documentoXpalabra (id_documento, id_palabra) VALUES(?,?)";
            PreparedStatement preparedStmt = connection.prepareStatement(sql);
           
            preparedStmt.setInt(1, d);
            preparedStmt.setInt(2, p);
            preparedStmt.executeUpdate();

            preparedStmt.close();

        } catch (SQLException ex)
        {
            Logger.getLogger(PalabraXDocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public static boolean existeRelacion(int p, int d, Connection connection)
    {
         try
        {
            String sql = "SELECT id_palabra, id_documento FROM documentoXpalabra WHERE id_palabra = ? and id_documento = ?";
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
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(PalabraXDocumentoJDBC.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
}
