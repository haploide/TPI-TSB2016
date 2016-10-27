package soporte;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import negocio.Vocabulario;

/**
 *
 * @author pablo
 */
public class WorkerCargar extends SwingWorker<Boolean, Double>
{

    private final JLabel jlblGif;
    private final JLabel jlblResultado;
    private final Vocabulario voc;
    private final JLabel jLlbCantidad;
    private final JTable jTblGrillaPalabras;
    private final Persistencia persistencia;

    public WorkerCargar(JLabel jlblGif, JLabel jlblResultado, Vocabulario voc, JLabel jLlbCantidad, JTable jTblGrillaPalabras)
    {
        this.jlblGif = jlblGif;
        this.jlblResultado = jlblResultado;
        this.voc = voc;
        this.jLlbCantidad = jLlbCantidad;
        this.jTblGrillaPalabras = jTblGrillaPalabras;
        persistencia= new Persistencia();
    }

   

    @Override
    protected Boolean doInBackground() throws Exception
    {
        jlblGif.setVisible(true);
        jlblResultado.setText("Cargando Vocabulario...");
        
        try
        {
            Connection connection = persistencia.abrirConexion();
            
            voc.cargarHashDesdeBD(Persistencia.getAllPalabras(connection));
            
            connection.close();
            
        } catch (IOException | ClassNotFoundException | SQLException iOException)
        {
            Logger.getLogger(WorkerCargar.class.getName()).log(Level.SEVERE, null, iOException);
        }

        return true;
    }

    @Override
    public void done()
    {
        jlblGif.setVisible(false);
        jlblResultado.setText("Carga Finalizanda!");
        jLlbCantidad.setText("Cantidad de Elementos: " + voc.getSizeHash());
        jTblGrillaPalabras.updateUI();

    }

}
