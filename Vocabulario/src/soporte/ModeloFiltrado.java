package soporte;

import java.util.ArrayList;
import javax.swing.table.AbstractTableModel;
import negocio.Palabra;


public class ModeloFiltrado extends AbstractTableModel
    {

        ArrayList<Palabra> filtrado;

        public ModeloFiltrado(ArrayList<Palabra> filtrado)
        {
            this.filtrado = filtrado;
        }

        @Override
        public int getRowCount()
        {
            return filtrado.size();
        }

        @Override
        public int getColumnCount()
        {
            return 3;
        }

        @Override
        public Object getValueAt(int rowIndex, int columnIndex)
        {
            if (filtrado.get(rowIndex) != null)
            {
                switch (columnIndex)
                {
                    case 0:

                        return filtrado.get(rowIndex).getPalabra();

                    case 1:

                        return filtrado.get(rowIndex).getFrecuencia();

                    case 2:

                        return filtrado.get(rowIndex).getDocumentos().toString();

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