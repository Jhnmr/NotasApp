package Vista;

import Vistas.FormIndex;
import com.formdev.flatlaf.FlatDarkLaf;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import javax.swing.ImageIcon;
import javax.swing.UIManager;
import raven.toast.Notifications;


public class Clipping extends javax.swing.JFrame {

    private static Clipping app;
    private final MainForm mainForm;

        // Constructor de la clase Clipping
    public Clipping() {
        // Inicializa los componentes de la interfaz
        initComponents();
        // Establece el tamaño y la posición inicial de la ventana
        setSize(new Dimension(900, 700));
        setLocationRelativeTo(null);
        // Inicializa los formularios principales
        mainForm = new MainForm();
        // Configura las notificaciones para que utilicen esta instancia de JFrame
        Notifications.getInstance().setJFrame(this);
        setIconImage(new ImageIcon(getClass().getResource("/icon/png/clip.ico")).getImage());
    }
 // Método estático para mostrar un formulario en la aplicación
    public static void showForm(Component component) {
        // Aplica la orientación del componente y muestra el formulario en el formulario principal
        component.applyComponentOrientation(app.getComponentOrientation());
        app.mainForm.showForm(component);
        
    }
     // Método estático para establecer el menú seleccionado en el formulario principal
    public static void setSelectedMenu(int index, int subIndex) {
        app.mainForm.setSelectedMenu(index, subIndex);
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 719, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 521, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

     // Método principal que inicia la aplicación
    public static void main(String args[]) {
        // Instala la fuente Roboto para la interfaz gráfica
        FlatRobotoFont.install();
        // Registra el origen personalizado de los valores predeterminados de FlatLaf
        FlatLaf.registerCustomDefaultsSource("theme");
        // Configura la fuente predeterminada para la interfaz gráfica
        UIManager.put("defaultFont", new Font(FlatRobotoFont.STYLE_REGULAR, Font.PLAIN, 13));
        // Configura el tema oscuro de FlatLaf
        FlatDarkLaf.setup();
        // Inicia la aplicación en el hilo de eventos de la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> {
            // Crea una instancia de la clase Clipping y la hace visible
            app = new Clipping();
            //app.setVisible(true);
         Clipping.showForm(new FormIndex());

        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
