package Vistas;

import BL.BLTemplate;
import ET.ETemplate;
import Vista.Clipping;
import com.formdev.flatlaf.FlatClientProperties;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.TextArea;
import java.awt.Toolkit;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.StringSelection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import raven.toast.Notifications;

public class FormInicio extends javax.swing.JPanel {

    private javax.swing.JLabel clip;  // Declarar la variable JLabel
    private javax.swing.JTextField[] textFields; //Campo de entrada

    public FormInicio() throws IOException {
         initComponents();
        try {
            lb.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$h1.font");
        //scaleImage();
        VerificarCarpetas();
        CargarInputs(true);
        LlenarCbxArea();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void VerificarCarpetas(){
         String homeDir = System.getProperty("user.home");
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos";
    String rutaInac = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos";
    BL.BLArea blar = new BL.BLArea();

    blar.VerificarCarpetas(ruta, rutaInac);
    }
    
    public void LlenarCbxArea(){
       String homeDir = System.getProperty("user.home");
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos";
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
    String ruta = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem();
    BL.BLTipoTemplate bltt = new BL.BLTipoTemplate();
    List<String> datos = new ArrayList<>();
    bltt.CargarCombo(ruta, datos);
    cbxTipo.removeAllItems();
    for (int i = 0; i < datos.size(); i++) {
        String dato = datos.get(i);
        cbxTipo.addItem(dato);
    }
        
    }
    
    public void LlenarCbxClipping() {

       BL.BLTemplate bltemp = new BL.BLTemplate();
    
    String homeDir = System.getProperty("user.home");
    String area = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem();
    List<String> datos = new ArrayList<>();
    cbxClipping.removeAllItems();
    bltemp.LlenarCombo(area, datos);

    for (int i = 0; i < datos.size(); i++) {
        cbxClipping.addItem(datos.get(i));
    }
    }
    
    public void CargarTemplate() throws IOException {

        String homeDir = System.getProperty("user.home");
    String OpcCombo = (String) cbxClipping.getSelectedItem();
    ETemplate obj = new ET.ETemplate(OpcCombo, true, "", homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem());
    BL.BLTemplate bltemp = new BL.BLTemplate();
    bltemp.CargarInputs(obj);

    List<String> cambios = new ArrayList<>();
    List<String> variables = new ArrayList<>();
    variables = obj.getVariables();
    for (javax.swing.JTextField textField : textFields) {
        String name = textField.getName();
        for (int i = 0; i < variables.size(); i++) {
            if (variables.get(i).contains(name)) {
                String nombre = textField.getText();
                cambios.add(nombre);
            }
        }
    }

    bltemp.ModificarTemplate(obj, cambios);

    String[] tempCambiado = obj.getContenido().split("\n");
    TextArea txtTemplate = txtAreaTemplate;
    txtTemplate.setForeground(Color.BLACK);
    Font font = new Font("Arial", Font.PLAIN, 12);
    txtTemplate.setFont(font);
    for (int i = 0; i < tempCambiado.length; i++) {
        txtTemplate.append("\n" + tempCambiado[i]);
    }
    }

    int lc = 0;


    Boolean ret = true;

    public void CargarInputs(Boolean flag) throws IOException {

        if (flag == true) {
        BL.BLTemplate bltemp = new BL.BLTemplate();
        String OpcCombo = (String) cbxClipping.getSelectedItem();
        String homeDir = System.getProperty("user.home");
        String area = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem();
        String areaInac = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem();
        ETemplate obj = new ET.ETemplate(OpcCombo, true, "", area);
        if (ret == true) {
            bltemp.ValidarInactivacion(area, areaInac);
        }
        bltemp.CargarInputs(obj);

        List<String> variables = new ArrayList<>();
        variables = obj.getVariables();

        textFields = new javax.swing.JTextField[variables.size()];

        int x = 30;
        int y = 80;
        Boolean retVal = true;
        for (int i = 0; i < textFields.length; i++) {
            textFields[i] = new javax.swing.JTextField();
            add(textFields[i], new org.netbeans.lib.awtextra.AbsoluteConstraints(x, y, 85, 25));
            x = x + 95;
            if (x > 490 && retVal == true) {
                y = y + 40;
                x = 30;
                if (y == 120) {
                    x = 30;
                }
            }
            textFields[i].setName(variables.get(i));
            textFields[i].setText(textFields[i].getName());
        }
    }

    }
    //forma para definir tamaño de iconos

    public void scaleImage() {
         String homeDir = System.getProperty("user.home");
    ImageIcon Icon = new ImageIcon(homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "src" + File.separator + "icon" + File.separator + "png" + File.separator + "cerrar.png");
    Image img = Icon.getImage();
    Image imgScale = img.getScaledInstance(close.getWidth(), close.getHeight(), Image.SCALE_SMOOTH);
    ImageIcon scaledIcon = new ImageIcon(imgScale);
    close.setIcon(scaledIcon);
    }

    public void InactivarTemplate() throws IOException {

       String homeDir = System.getProperty("user.home");
    String OpcCombo = (String) cbxClipping.getSelectedItem();
    String rutaAct = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem() + File.separator + OpcCombo + ".txt";
    String rutaInact = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Inactivos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem() + File.separator + OpcCombo + ".txt";
    String area = "MES\\";

    ETemplate obj = new ET.ETemplate(OpcCombo, true, "", area);
    BL.BLTemplate bltemp = new BL.BLTemplate();

    bltemp.InactivarTemplate(obj, rutaAct, rutaInact);
    LlenarCbxClipping();
    CargarInputs(true);

    Vista.Clipping.showForm(close);

    ret = false;
    }

    public void EliminarInputs() {

        for (javax.swing.JTextField textField : textFields) {
            remove(textField);
        }
        revalidate();
        repaint();
    }

    public void LimpiarTXT() {

        TextArea txtTemplate = txtAreaTemplate;

        txtTemplate.setText("");

        revalidate();
        repaint();
    }

    public void CopiarTexto() {
        TextArea txtTemplate = txtAreaTemplate;

        String texto = txtTemplate.getText();

        StringSelection copia = new StringSelection(texto);

        Clipboard portapapeles = Toolkit.getDefaultToolkit().getSystemClipboard();
        portapapeles.setContents(copia, null);
    }

    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lb = new javax.swing.JLabel();
        btnLimpiar = new javax.swing.JButton();
        cbxClipping = new javax.swing.JComboBox<>();
        txtAreaTemplate = new java.awt.TextArea();
        btnInactivar = new javax.swing.JButton();
        btnCopiar = new javax.swing.JButton();
        close = new javax.swing.JLabel();
        btnCargar = new javax.swing.JButton();
        btnEditar = new javax.swing.JButton();
        jSeparator4 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        cbxTipo = new javax.swing.JComboBox<>();
        cbxArea = new javax.swing.JComboBox<>();
        jSeparator7 = new javax.swing.JSeparator();
        btnAyuda = new javax.swing.JButton();

        setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        setMinimumSize(new java.awt.Dimension(800, 500));
        setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        lb.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        add(lb, new org.netbeans.lib.awtextra.AbsoluteConstraints(769, 90, -1, 78));

        btnLimpiar.setBackground(new java.awt.Color(51, 51, 51));
        btnLimpiar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnLimpiar.setForeground(new java.awt.Color(255, 255, 255));
        btnLimpiar.setText("Clean");
        btnLimpiar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnLimpiarMouseClicked(evt);
            }
        });
        btnLimpiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLimpiarActionPerformed(evt);
            }
        });
        add(btnLimpiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 490, -1, 30));

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
        add(cbxClipping, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 150, 30));

        txtAreaTemplate.setBackground(new java.awt.Color(250, 250, 250));
        txtAreaTemplate.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txtAreaTemplate.setEditable(false);
        add(txtAreaTemplate, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 490, 270));

        btnInactivar.setBackground(new java.awt.Color(204, 0, 51));
        btnInactivar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnInactivar.setForeground(new java.awt.Color(255, 255, 255));
        btnInactivar.setText("Inactivate");
        btnInactivar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnInactivarActionPerformed(evt);
            }
        });
        add(btnInactivar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 490, -1, 30));

        btnCopiar.setBackground(new java.awt.Color(51, 51, 51));
        btnCopiar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnCopiar.setForeground(new java.awt.Color(255, 255, 255));
        btnCopiar.setText("Copy");
        btnCopiar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCopiarActionPerformed(evt);
            }
        });
        add(btnCopiar, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 490, -1, 30));

        close.setBackground(new java.awt.Color(153, 204, 255));
        close.setForeground(new java.awt.Color(153, 255, 255));
        add(close, new org.netbeans.lib.awtextra.AbsoluteConstraints(1010, 10, 20, 20));

        btnCargar.setBackground(new java.awt.Color(0, 102, 153));
        btnCargar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnCargar.setForeground(new java.awt.Color(255, 255, 255));
        btnCargar.setText("Load");
        btnCargar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCargarActionPerformed(evt);
            }
        });
        add(btnCargar, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 490, -1, 30));

        btnEditar.setBackground(new java.awt.Color(204, 0, 51));
        btnEditar.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnEditar.setForeground(new java.awt.Color(255, 255, 255));
        btnEditar.setText("Edit");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });
        add(btnEditar, new org.netbeans.lib.awtextra.AbsoluteConstraints(122, 490, 70, 30));

        jSeparator4.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 40, 150, 10));

        jLabel3.setFont(new java.awt.Font("Kanit", 0, 12)); // NOI18N
        jLabel3.setText("Clipping");
        add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 40, -1, -1));

        jLabel4.setFont(new java.awt.Font("Kanit", 0, 12)); // NOI18N
        jLabel4.setText("Area");
        add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, -1, -1));

        jSeparator6.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 40, 150, 10));

        jLabel5.setFont(new java.awt.Font("Kanit", 0, 12)); // NOI18N
        jLabel5.setText("Type");
        add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 40, -1, -1));

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
        add(cbxTipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 10, 150, 30));

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
        add(cbxArea, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 10, 150, 30));

        jSeparator7.setForeground(new java.awt.Color(204, 0, 51));
        add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 40, 150, 10));

        btnAyuda.setBackground(new java.awt.Color(51, 51, 51));
        btnAyuda.setFont(new java.awt.Font("Kanit", 0, 14)); // NOI18N
        btnAyuda.setForeground(new java.awt.Color(255, 255, 255));
        btnAyuda.setText("Help");
        btnAyuda.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAyudaActionPerformed(evt);
            }
        });
        add(btnAyuda, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 490, -1, 30));
    }// </editor-fold>//GEN-END:initComponents

    private void btnLimpiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLimpiarActionPerformed
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Text has been cleaned up");
    }//GEN-LAST:event_btnLimpiarActionPerformed

    private void cbxClippingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxClippingActionPerformed
        try {
            LimpiarTXT();
            EliminarInputs();
            CargarInputs(true);
        } catch (IOException ex) {
            Logger.getLogger(FormInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_cbxClippingActionPerformed

    private void btnInactivarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnInactivarActionPerformed
        try {
                    Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Successfully disabled");

            InactivarTemplate();
        } catch (IOException ex) {
            Logger.getLogger(FormInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnInactivarActionPerformed

    private void btnCopiarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCopiarActionPerformed

        CopiarTexto();
        Notifications.getInstance().show(Notifications.Type.INFO, Notifications.Location.TOP_CENTER, "Text copied correctly!");


    }//GEN-LAST:event_btnCopiarActionPerformed

    private void btnLimpiarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnLimpiarMouseClicked
        LimpiarTXT();
    }//GEN-LAST:event_btnLimpiarMouseClicked

    private void btnCargarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCargarActionPerformed
        try {
            LimpiarTXT();
            CargarTemplate();
        } catch (IOException ex) {
            Logger.getLogger(FormInicio.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnCargarActionPerformed

    private void cbxClippingMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxClippingMouseEntered

    }//GEN-LAST:event_cbxClippingMouseEntered

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        String homeDir = System.getProperty("user.home");
    String nombre = (String) cbxClipping.getSelectedItem();
    String contenido = (String) txtAreaTemplate.getText();
    String area = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos" + File.separator + cbxArea.getSelectedItem() + File.separator + cbxTipo.getSelectedItem(); 
    ET.ETemplate obj = new ET.ETemplate(nombre, true, contenido, area);
    try {
        Clipping.showForm(new Vistas.FormEditarClipping(obj));
    } catch (IOException ex) {
        Logger.getLogger(FormInicio.class.getName()).log(Level.SEVERE, null, ex);
    }
    }//GEN-LAST:event_btnEditarActionPerformed

    private void cbxTipoMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxTipoMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxTipoMouseEntered

    private void cbxTipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxTipoActionPerformed
        LlenarCbxClipping();
    }//GEN-LAST:event_cbxTipoActionPerformed

    private void cbxAreaMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cbxAreaMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_cbxAreaMouseEntered

    private void cbxAreaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbxAreaActionPerformed
        LlenarCbxTipoC();
                LlenarCbxClipping();
    }//GEN-LAST:event_cbxAreaActionPerformed

    private void btnAyudaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAyudaActionPerformed
        Notifications.getInstance().show(Notifications.Type.INFO,
            Notifications.Location.TOP_CENTER, 9999999,
            "Hello! Here you can activate an existing file, \n"
            + "You need to select the one you want to edit and enter the new information\n"
            + " and finally press Edit. I hope you're having a good day");
    }//GEN-LAST:event_btnAyudaActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAyuda;
    private javax.swing.JButton btnCargar;
    private javax.swing.JButton btnCopiar;
    private javax.swing.JButton btnEditar;
    private javax.swing.JButton btnInactivar;
    private javax.swing.JButton btnLimpiar;
    private javax.swing.JComboBox<String> cbxArea;
    private javax.swing.JComboBox<String> cbxClipping;
    private javax.swing.JComboBox<String> cbxTipo;
    private javax.swing.JLabel close;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JLabel lb;
    private java.awt.TextArea txtAreaTemplate;
    // End of variables declaration//GEN-END:variables
}
