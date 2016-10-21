package soporte;

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

    public WorkerCargar(JLabel jlblGif, JLabel jlblResultado, Vocabulario voc, JLabel jLlbCantidad, JTable jTblGrillaPalabras)
    {
        this.jlblGif = jlblGif;
        this.jlblResultado = jlblResultado;
        this.voc = voc;
        this.jLlbCantidad = jLlbCantidad;
        this.jTblGrillaPalabras = jTblGrillaPalabras;
    }

   

    @Override
    protected Boolean doInBackground() throws Exception
    {
        jlblGif.setVisible(true);
        voc.cargarHashDesdeBD(Persistencia.getAllPalabras());

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
