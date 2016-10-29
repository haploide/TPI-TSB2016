package soporte;


import negocio.Persistencia;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JTable;
import javax.swing.SwingWorker;
import negocio.Palabra;

public class WorkerFiltrado extends SwingWorker<Boolean, Void>
{

    private final JLabel jlblGif;
    private final JLabel jlblResultado;
    private final JLabel jLlbCantidad;
    private final String filtro;
    private ArrayList<Palabra> byFilterPalabras;
    private final JTable jTblGrillaPalabras;

    public WorkerFiltrado(JLabel jlblGif, JLabel jlblResultado, ArrayList<Palabra> byFilterPalabras, JLabel jLlbCantidad, String filtro, JTable jTblGrillaPalabras)
    {
        this.jlblGif = jlblGif;
        this.jlblResultado = jlblResultado;
        this.jLlbCantidad = jLlbCantidad;
        this.filtro = filtro;
        this.jTblGrillaPalabras = jTblGrillaPalabras;
        this.byFilterPalabras=byFilterPalabras;
    }

    

    

    @Override
    protected Boolean doInBackground() throws Exception
    {
        jlblResultado.setText("Filtrando...");
        jlblGif.setVisible(true);
        byFilterPalabras= Persistencia.getByFilterPalabras(filtro);
              
        return true;

    }

    @Override
    public void done()
    {
        jlblGif.setVisible(false);
        jlblResultado.setText("Filtrado Finalizado!");
        jLlbCantidad.setText("Cantidad de Elementos Filtrados: " + byFilterPalabras.size());
        jTblGrillaPalabras.setModel(new ModeloFiltrado(byFilterPalabras));

        jTblGrillaPalabras.updateUI();
        
        

    }

}
