package Vistas;

import com.formdev.flatlaf.FlatClientProperties;
import java.io.File;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JLayeredPane;
import javax.swing.JList;

public class FormIndex extends javax.swing.JPanel implements java.beans.Customizer {
    private JList<String> listaDirectorios;
    private DefaultListModel<String> listModel;
    private Object bean;
    private List<File> directorios;

    
    public FormIndex() {
        this.directorios = directorios;
        initComponents();
        jLabel1.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
      
         // Inicializa la lista y el modelo
        listModel = new DefaultListModel<>();
        listaDirectorios = new JList<>(listModel);
      
    }
    
    
    
    public void updatePanelWithDirectories(List<File> directorios) {
        // Limpia el modelo antes de agregar los nuevos directorios
        listModel.clear();

        // Agrega los nombres de los directorios al modelo
        for (File directorio : directorios) {
            listModel.addElement(directorio.getName());
        }

        // Actualiza la vista de la lista
        listaDirectorios.updateUI();

        // ... Puedes agregar más lógica según tus necesidades ...
    }
    
    public void setObject(Object bean) {
        this.bean = bean;
    }
    
    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the FormEditor.
     */
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/icon/png/edwards (1).png"))); // NOI18N

        jLabel1.setForeground(new java.awt.Color(204, 0, 51));
        jLabel1.setText("Clipping Tool");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 491, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(153, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1)
                .addGap(275, 275, 275))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(15, 15, 15)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(53, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    // End of variables declaration//GEN-END:variables
}