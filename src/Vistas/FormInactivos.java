package Vistas;

import BL.BLTemplate;
import com.formdev.flatlaf.FlatClientProperties;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import raven.toast.Notifications;

public class FormInactivos extends javax.swing.JPanel {

    private javax.swing.JLabel clip;  // Declarar la variable JLabel
    private javax.swing.JTextField[] textFields; //Campo de entrada

    public FormInactivos() throws IOException {
        initComponents();
        lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        //scaleImage();
        
        LlenarCbxArea();
        FormInicio fi = new FormInicio();
                fi.VerificarCarpetas();


    }

    public void LlenarCbxArea(){ //mediante este metodo se llena el combobox de area, leyendo la carpeta y trayendo los directorios del mismo
 // Mediante este método se llena el combobox de área, leyendo la carpeta y trayendo los directorios del mismo
    String homeDir = System.getProperty("user.home");
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos";
    BL.BLArea bla = new BL.BLArea();
    List<String> datos = new ArrayList<>();
    bla.CargarCombo(ruta, datos);
    cbxArea.removeAllItems();
    for (int i = 1; i < datos.size(); i++) {
        String dato = datos.get(i);
        cbxArea.addItem(dato);
    }
    LlenarCbxTipoC();
    }
    
    public void LlenarCbxTipoC(){
        String homeDir = System.getProperty("user.home");
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos" + File.separator + cbxArea.getSelectedItem();
    BL.BLTipoTemplate bltt = new BL.BLTipoTemplate();
    List<String> datos = new ArrayList<>();
    bltt.CargarCombo(ruta, datos);
    cbxTipo.removeAllItems();
    for (int i = 0; i < datos.size(); i++) {
        String dato = datos.get(i);
        cbxTipo.addItem(dato);
    }
    }
    
    int lc = 0;

    public void LlenarCbxClipping() {

        BL.BLTemplate bltemp = new BL.BLTemplate();
    
    String homeDir = System.getProperty("user.home");
    String area = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem();
    List<String> datos = new ArrayList<>();
    cbxClipping.removeAllItems();
    bltemp.LlenarCombo(area, datos);

    for (int i = 0; i < datos.size(); i++) {
        cbxClipping.addItem(datos.get(i));
    }
    }

    public void ActivarTemplate() {
        String homeDir = System.getProperty("user.home");
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem() + File.separator + cbxClipping.getSelectedItem();
    String rutaActiva = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem() + File.separator + cbxClipping.getSelectedItem();
    BL.BLTemplate bltemp = new BL.BLTemplate();

    bltemp.ActivarTemplate(ruta, rutaActiva);
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        cbxClipping = new javax.swing.JComboBox<>();
        btnActivar = new javax.swing.JButton();
        close = new javax.swing.JLabel();
        cbxArea = new javax.swing.JComboBox<>();
        cbxTipo = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        btnAyuda = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(769, 90, -1, 78));

        cbxClipping.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        cbxClipping.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cbxClipping.setToolTipText("");
        cbxClipping.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxClipping.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbxClippingMouseEntered(evt);
            }
        });
        cbxClipping.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxClippingActionPerformed(evt);
            }
        });
        add(cbxClipping, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 150, 30));

        btnActivar.setBackground(new java.awt.Color(204, 0, 51));
        btnActivar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnActivar.setForeground(new java.awt.Color(255, 255, 255));
        btnActivar.setText("Activar");
        btnActivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnActivarActionPerformed(evt);
            }
        });
        add(btnActivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 120, -1, 30));

        close.setBackground(new java.awt.Color(153, 204, 255));
        close.setForeground(new java.awt.Color(153, 255, 255));
        add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 20, 20));

        cbxArea.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        cbxArea.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cbxArea.setToolTipText("");
        cbxArea.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxArea.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbxAreaMouseEntered(evt);
            }
        });
        cbxArea.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxAreaActionPerformed(evt);
            }
        });
        add(cbxArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 50, 150, 30));

        cbxTipo.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        cbxTipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "1" }));
        cbxTipo.setToolTipText("");
        cbxTipo.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        cbxTipo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                cbxTipoMouseEntered(evt);
            }
        });
        cbxTipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbxTipoActionPerformed(evt);
            }
        });
        add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 50, 150, 30));

        jLabel1.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        jLabel1.setText("Clipping");
        add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 30, -1, 20));

        jLabel2.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        jLabel2.setText("Area");
        add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 30, -1, 20));

        jLabel3.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        jLabel3.setText("Type");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 30, -1, 20));

        jSeparator4.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 150, 10));

        jSeparator5.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 150, 10));

        jSeparator6.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 90, 150, 10));

        btnAyuda.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnAyuda.setText("Help");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        add(btnAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 120, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void cbxClippingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClippingActionPerformed
        
    }//GEN-LAST:event_cbxClippingActionPerformed

    private void btnActivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnActivarActionPerformed
        ActivarTemplate(); 
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Clipping Activated Successfully");

    }//GEN-LAST:event_btnActivarActionPerformed

    private void cbxClippingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxClippingMouseEntered

    }//GEN-LAST:event_cbxClippingMouseEntered

    private void cbxAreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxAreaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAreaMouseEntered

    private void cbxAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAreaActionPerformed
                LlenarCbxTipoC();
                LlenarCbxClipping();
    }//GEN-LAST:event_cbxAreaActionPerformed

    private void cbxTipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMouseEntered

    private void cbxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoActionPerformed
        LlenarCbxClipping();
    }//GEN-LAST:event_cbxTipoActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        Notifications.getInstance().show(Notifications.Type.INFO,
            Notifications.Location.TOP_CENTER, 9999999,
            "Hello! Here you can activate an existing file, \n"
            + "You need to select the one you want to edit and enter the new information\n"
            + " and finally press Edit. I hope you're having a good day");
    }//GEN-LAST:event_btnAyudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnActivar;
    private javax.swing.JButton btnAyuda;
    private javax.swing.JComboBox<String> cbxArea;
    private javax.swing.JComboBox<String> cbxClipping;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JLabel lb;
    // End of variables declaration//GEN-END:variables
}
