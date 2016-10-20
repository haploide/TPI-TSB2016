package soporte;

import java.awt.Color;
import java.io.File;
import java.util.LinkedList;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import negocio.Vocabulario;

public class WorkerHashing extends SwingWorker<Boolean, Integer>
{

    private final JLabel jlblResultado;
    private final JProgressBar jPbrProgreso;
    private int incremento;
    private final Vocabulario voc;
    private final LinkedList<File> colaTareas;

    public WorkerHashing(JLabel jlblResultado, JProgressBar jPbrProgreso, int cantidad, Vocabulario voc, LinkedList<File> cola)
    {
        this.jlblResultado = jlblResultado;
        this.jPbrProgreso = jPbrProgreso;
        this.voc = voc;
        this.colaTareas = cola;
        incremento = 100 / cantidad;
    }

    @Override
    protected Boolean doInBackground() throws Exception
    {
        for (File tarea : colaTareas)
        {
            voc.leerArchivo(tarea);

            publish(incremento);

            incremento += incremento;

        }

        return true;
    }

    @Override
    public void done()
    {   
        jlblResultado.setVisible(true);
        jlblResultado.setText("Carga Finalizada!");
       // jlblResultado.setForeground(Color.BLUE);
        

    }
    
    @Override
    public void process(List<Integer> incremento)
    {
        jPbrProgreso.setValue(incremento.get(0));
    }

}
