package soporte;

import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import negocio.Vocabulario;

public class WorkerHashing extends SwingWorker<Boolean, Integer>
{

    private final JLabel jlblResultado;
    private final JLabel jLlbCantidad;
    private final JProgressBar jPbrProgreso;
    private final JTable jTblGrillaPalabras;
    private int incremento;
    private final Vocabulario voc;
    private final LinkedList<File> colaTareas;

    public WorkerHashing(JLabel jlblResultado, JLabel jLlbCantidad, JProgressBar jPbrProgreso, JTable jTblGrillaPalabras, int cantidad, Vocabulario voc, LinkedList<File> cola)
    {
        this.jlblResultado = jlblResultado;
        this.jLlbCantidad = jLlbCantidad;
        this.jPbrProgreso = jPbrProgreso;
        this.jTblGrillaPalabras = jTblGrillaPalabras;
        this.voc = voc;
        this.colaTareas = cola;
        incremento = 100 / cantidad;
    }

    @Override
    protected Boolean doInBackground() throws Exception
    {
        int aux=incremento;
        
        for (File tarea : colaTareas)
        {
            voc.leerArchivo(tarea);

            publish(aux);

            aux += incremento;

        }

        return true;
    }

    @Override
    public void done()
    {
        jlblResultado.setVisible(true);
        jlblResultado.setText("Carga Finalizada!");
        jLlbCantidad.setText("Cantidad de Elementos: " + voc.getSizeHash());
        jTblGrillaPalabras.updateUI();

    }

    @Override
    public void process(List<Integer> incremento)
    {
        jPbrProgreso.setValue(incremento.get(0));
        jlblResultado.setText("Cargando...!");

    }

}
