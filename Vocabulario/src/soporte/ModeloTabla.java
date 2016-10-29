package soporte;

import javax.swing.table.AbstractTableModel;
import negocio.Palabra;
import negocio.Vocabulario;

public class ModeloTabla extends AbstractTableModel
{
    private Vocabulario voc;

    public ModeloTabla(Vocabulario voc)
    {
        this.voc=voc;
        
    }

    @Override
    public int getRowCount()
    {
        return voc.getSizeHash();

    }

    @Override
    public int getColumnCount()
    {
        return 3;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex)
    {
        Palabra tabla[] = voc.getTabla();

        if (tabla[rowIndex] != null)
        {
            switch (columnIndex)
            {
                case 0:

                    return tabla[rowIndex].getPalabra();

                case 1:

                    return tabla[rowIndex].getFrecuencia();

                case 2:

                    return tabla[rowIndex].getDocumentos().toString();

            }
        }

        return "-------";

    }

    @Override
    public String getColumnName(int column)
    {
        String nombres[] =
        {
            "Palabra", "Frecuencia", "Documentos"
        };

        return nombres[column];
    }

}
