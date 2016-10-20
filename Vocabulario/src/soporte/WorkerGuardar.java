
package soporte;

import java.util.List;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.SwingWorker;
import negocio.Palabra;
import negocio.Vocabulario;

public class WorkerGuardar extends SwingWorker<Boolean, Double>
{
    private final JLabel jlblResultado;
    private final JProgressBar jPbrProgreso;
    private double incremento;
    private final Vocabulario voc;
    private final Persistencia persistencia;
    
    public WorkerGuardar(JLabel jlblResultado, JProgressBar jPbrProgreso, Vocabulario voc)
    {
        this.jlblResultado=jlblResultado;
        this.jPbrProgreso=jPbrProgreso;
        this.voc=voc;
        
        Double porcen=100.0;
        this.incremento=porcen/voc.getSizeHash();
        persistencia= new Persistencia();
    }

    @Override
    protected Boolean doInBackground() throws Exception
    {
        Double aux=incremento;
        Palabra tabla[]=voc.getTabla();
        
        
        for(int i=0;i<tabla.length;i++){
            
            
            persistencia.guardarEnBD(tabla[i]);
            
            publish(aux);

            aux += incremento;
        }
        
        
        return true;
    }
    @Override
    public void done()
    {
        jlblResultado.setVisible(true);
        jlblResultado.setText("Guardado Finalizando!");
        
    }

    @Override
    public void process(List<Double> incremento)
    {
        String str=incremento.get(0).toString();
        String st[]=str.split("\\p{Punct}");
        
        
        jPbrProgreso.setValue(Integer.parseInt(st[0]));
        jlblResultado.setText("Guardando...");

    }
}
