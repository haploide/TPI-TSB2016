package interfaz;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.AbstractTableModel;
import negocio.Palabra;
import negocio.Vocabulario;
import soporte.WorkerHashing;

public class VocabularioFrame extends javax.swing.JFrame
{

    private Vocabulario voc;
    private LinkedList<File> colaTareas;

    /**
     * Creates new form Vocabulario
     */
    public VocabularioFrame()
    {
        voc = new Vocabulario();
        colaTareas = new LinkedList<>();
        initComponents();

    }

    public Image obtenerImagen()
    {
        try
        {
            return ImageIO.read(new File(".\\resource\\letras16.png"));
        } catch (IOException ex)
        {
            Logger.getLogger(VocabularioFrame.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents()
    {

        jPnlPalabras = new javax.swing.JPanel();
        jScllPalabras = new javax.swing.JScrollPane();
        jTblGrillaPalabras = new javax.swing.JTable();
        jPnlOpciones = new javax.swing.JPanel();
        jLblFiltro = new javax.swing.JLabel();
        jTflFiltro = new javax.swing.JTextField();
        jBtnFiltrar = new javax.swing.JButton();
        jBtnCargarDocumentos = new javax.swing.JButton();
        jPbrCargando = new javax.swing.JProgressBar();
        jBtnGuardar = new javax.swing.JButton();
        jPnlStatusBar = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jLlbResultado = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Vocabulario");
        setIconImage(obtenerImagen()
        );

        jPnlPalabras.setBorder(javax.swing.BorderFactory.createTitledBorder("Palabras"));

        jTblGrillaPalabras.setModel(new ModeloTabla()
        );
        jScllPalabras.setViewportView(jTblGrillaPalabras);

        javax.swing.GroupLayout jPnlPalabrasLayout = new javax.swing.GroupLayout(jPnlPalabras);
        jPnlPalabras.setLayout(jPnlPalabrasLayout);
        jPnlPalabrasLayout.setHorizontalGroup(
            jPnlPalabrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPalabrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScllPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, 369, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPnlPalabrasLayout.setVerticalGroup(
            jPnlPalabrasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlPalabrasLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScllPalabras, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addContainerGap())
        );

        jPnlOpciones.setBorder(javax.swing.BorderFactory.createTitledBorder("Opciones"));

        jLblFiltro.setText("Filtro");

        jBtnFiltrar.setIcon(new javax.swing.ImageIcon(".\\resource\\embudo24.png"));
        jBtnFiltrar.setText("Filtar");

        jBtnCargarDocumentos.setIcon(new javax.swing.ImageIcon(".\\resource\\agregar24.png"));
        jBtnCargarDocumentos.setText("Cargar mas Documentos");
        jBtnCargarDocumentos.addMouseListener(new java.awt.event.MouseAdapter()
        {
            public void mouseClicked(java.awt.event.MouseEvent evt)
            {
                jBtnCargarDocumentosMouseClicked(evt);
            }
        });

        jPbrCargando.setBorder(null);

        jBtnGuardar.setText("Guardar Vocabulario");
        jBtnGuardar.addActionListener(new java.awt.event.ActionListener()
        {
            public void actionPerformed(java.awt.event.ActionEvent evt)
            {
                jBtnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPnlOpcionesLayout = new javax.swing.GroupLayout(jPnlOpciones);
        jPnlOpciones.setLayout(jPnlOpcionesLayout);
        jPnlOpcionesLayout.setHorizontalGroup(
            jPnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlOpcionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPbrCargando, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPnlOpcionesLayout.createSequentialGroup()
                        .addGroup(jPnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPnlOpcionesLayout.createSequentialGroup()
                                .addComponent(jLblFiltro)
                                .addGap(18, 18, 18)
                                .addComponent(jTflFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jBtnFiltrar)
                            .addComponent(jBtnCargarDocumentos)
                            .addComponent(jBtnGuardar))
                        .addGap(0, 24, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPnlOpcionesLayout.setVerticalGroup(
            jPnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlOpcionesLayout.createSequentialGroup()
                .addGap(30, 30, 30)
                .addGroup(jPnlOpcionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLblFiltro)
                    .addComponent(jTflFiltro, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jBtnFiltrar)
                .addGap(28, 28, 28)
                .addComponent(jBtnCargarDocumentos)
                .addGap(18, 18, 18)
                .addComponent(jPbrCargando, javax.swing.GroupLayout.PREFERRED_SIZE, 15, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 138, Short.MAX_VALUE)
                .addComponent(jBtnGuardar)
                .addContainerGap())
        );

        jPbrCargando.setStringPainted(true);
        jPbrCargando.setVisible(false);

        jPnlStatusBar.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.LOWERED));

        jSeparator1.setOrientation(javax.swing.SwingConstants.VERTICAL);
        jSeparator1.setBorder(javax.swing.BorderFactory.createEtchedBorder(javax.swing.border.EtchedBorder.RAISED));

        jLlbResultado.setText("Listo");

        javax.swing.GroupLayout jPnlStatusBarLayout = new javax.swing.GroupLayout(jPnlStatusBar);
        jPnlStatusBar.setLayout(jPnlStatusBarLayout);
        jPnlStatusBarLayout.setHorizontalGroup(
            jPnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPnlStatusBarLayout.createSequentialGroup()
                .addComponent(jLlbResultado, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPnlStatusBarLayout.setVerticalGroup(
            jPnlStatusBarLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jSeparator1)
            .addComponent(jLlbResultado, javax.swing.GroupLayout.DEFAULT_SIZE, 22, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPnlOpciones, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPnlPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(15, 15, 15))
            .addComponent(jPnlStatusBar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jPnlOpciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPnlPalabras, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPnlStatusBar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jBtnCargarDocumentosMouseClicked(java.awt.event.MouseEvent evt)//GEN-FIRST:event_jBtnCargarDocumentosMouseClicked
    {//GEN-HEADEREND:event_jBtnCargarDocumentosMouseClicked
        JFileChooser fc = new JFileChooser("../");
        fc.setMultiSelectionEnabled(true);

        FileNameExtensionFilter filtros = new FileNameExtensionFilter("Documentos de Texto (*.txt)", "txt");
        fc.setFileFilter(filtros);

        int result = fc.showOpenDialog(this);

        switch (result)
        {
            case JFileChooser.APPROVE_OPTION:

                for (File tarea : fc.getSelectedFiles())
                {
                    colaTareas.add(tarea);
                }
                break;

            case JFileChooser.CANCEL_OPTION:

                break;

        }

        jPbrCargando.setVisible(true);

        WorkerHashing worker = new WorkerHashing(jLlbResultado, jPbrCargando, colaTareas.size(), voc, colaTareas);

        worker.execute();
        
        jTblGrillaPalabras.updateUI();

    }//GEN-LAST:event_jBtnCargarDocumentosMouseClicked

    private void jBtnGuardarActionPerformed(java.awt.event.ActionEvent evt)//GEN-FIRST:event_jBtnGuardarActionPerformed
    {//GEN-HEADEREND:event_jBtnGuardarActionPerformed
        boolean guardo = voc.guardarEnBD();

        if (guardo)
        {

        } else
        {

        }

    }//GEN-LAST:event_jBtnGuardarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[])
    {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try
        {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels())
            {
                if ("Nimbus".equals(info.getName()))
                {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex)
        {
            java.util.logging.Logger.getLogger(VocabularioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex)
        {
            java.util.logging.Logger.getLogger(VocabularioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex)
        {
            java.util.logging.Logger.getLogger(VocabularioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex)
        {
            java.util.logging.Logger.getLogger(VocabularioFrame.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable()
        {
            public void run()
            {
                new VocabularioFrame().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jBtnCargarDocumentos;
    private javax.swing.JButton jBtnFiltrar;
    private javax.swing.JButton jBtnGuardar;
    private javax.swing.JLabel jLblFiltro;
    private javax.swing.JLabel jLlbResultado;
    private javax.swing.JProgressBar jPbrCargando;
    private javax.swing.JPanel jPnlOpciones;
    private javax.swing.JPanel jPnlPalabras;
    private javax.swing.JPanel jPnlStatusBar;
    private javax.swing.JScrollPane jScllPalabras;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable jTblGrillaPalabras;
    private javax.swing.JTextField jTflFiltro;
    // End of variables declaration//GEN-END:variables

    class ModeloTabla extends AbstractTableModel
    {

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

            if (tabla[rowIndex]!=null)
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
                "Palabra", "Frecuencia", "Cant Documentos"
            };

            return nombres[column];
        }

    }

}
