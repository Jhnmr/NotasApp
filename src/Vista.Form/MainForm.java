package Vista;

import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import com.formdev.flatlaf.util.UIScale;
import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.ComponentOrientation;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JLayeredPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import Vista.Clipping;
import Vistas.FormAbout;
import Vistas.FormInicio;
import Vistas.FormCrearClipping;
import Vistas.FormEditarClipping;
import Vistas.FormInactivos;
import Vistas.FormIndex;
import java.awt.Frame;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import menu.Menu;
import menu.MenuAction;

// Clase que representa el formulario principal de la aplicación
public class MainForm extends JLayeredPane {

    private JFrame mainFrame; // Marco principal de la aplicación
        Menu menuInstance = new Menu();

    // Constructor de la clase MainForm
    public MainForm() {
        init();
    }

    // Método para inicializar la interfaz del formulario principal
    private void init() {
        setBorder(new EmptyBorder(5, 5, 5, 5));
        setLayout(new MainFormLayout());
       
        FormIndex formIndex = new FormIndex(); // Crea una instancia de FormIndex

        // Pasa la instancia de FormIndex al constructor de Menu
        menu = new Menu();
        
        panelBody = new JPanel(new BorderLayout());
        initMenuArrowIcon();
        menuButton.putClientProperty(FlatClientProperties.STYLE, ""
                + "background:$Menu.button.background;"
                + "arc:999;"
                + "focusWidth:0;"
                + "borderWidth:0");
        menuButton.addActionListener((ActionEvent e) -> {
            setMenuFull(!menu.isMenuFull());
        });
        initMenuEvent();
        setLayer(menuButton, JLayeredPane.POPUP_LAYER);
        add(menuButton);
        add(menu);
        add(panelBody);

        // Inicializa el JFrame principal
        mainFrame = new JFrame();
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(800, 600);
        mainFrame.add(this); // Agrega esta instancia de MainForm al JFrame principal
        mainFrame.setVisible(true);
        mainFrame.setLocationRelativeTo(null);
    }

    // Método para minimizar la ventana
    public void minimizarVentana() {
        mainFrame.setState(Frame.ICONIFIED);
    }

    // Método para cerrar la ventana
    public void cerrarVentana() {
        mainFrame.dispose();
    }

    // Método para mover la ventana a una posición específica
    public void moverVentana(int x, int y) {
        mainFrame.setLocation(x, y);
    }

    // Sobrescribe el método para aplicar la orientación de componente
    @Override
    public void applyComponentOrientation(ComponentOrientation o) {
        super.applyComponentOrientation(o);
        initMenuArrowIcon();
    }

    // Método para inicializar el ícono de flecha del menú
    private void initMenuArrowIcon() {
        if (menuButton == null) {
            menuButton = new JButton();
        }
        String icon = (getComponentOrientation().isLeftToRight()) ? "menu_left.svg" : "menu_right.svg";
        menuButton.setIcon(new FlatSVGIcon("icon/svg/" + icon, 0.8f));
    }

   
    
    
  private List<File> directorios = new ArrayList<>();  // Lista para almacenar los directorios
  
// Método para inicializar el evento del menú
private void initMenuEvent() {
    menu.addMenuEvent((int index, int subIndex, MenuAction action) -> {
        // Obtiene la lista de directorios actualizada
        directorios = menu.getDirectorios();

        // Application.mainForm.showForm(new DefaultForm("Form : " + index + " " + subIndex));
        if (index == 0) {
            // Pasa la lista de directorios al FormIndex cuando se crea
            Clipping.showForm(new FormIndex());
        } else if (index == 1) {
            if (subIndex == 1) {
                Clipping.showForm(new FormCrearClipping());
            } else if (subIndex == 2) {
                try {
                    Clipping.showForm(new FormInactivos());
                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (subIndex == 3) {
                try {
                    Clipping.showForm(new FormInicio());
                } catch (IOException ex) {
                    Logger.getLogger(MainForm.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if(subIndex == 4){
                menuInstance.agregarDirectorio();
            }else if(subIndex == 5){
                    Clipping.showForm(new FormAbout());
            }else{
                action.cancel();
            }
        } else if (index == 9) {
            // Clipping.logout();
        } else {
            action.cancel();
        }
    });
}










    // Método para establecer si el menú está completamente expandido o no
    private void setMenuFull(boolean full) {
        String icon;
        if (getComponentOrientation().isLeftToRight()) {
            icon = (full) ? "menu_left.svg" : "menu_right.svg";
        } else {
            icon = (full) ? "menu_right.svg" : "menu_left.svg";
        }
        menuButton.setIcon(new FlatSVGIcon("icon/svg/" + icon, 0.8f));
        menu.setMenuFull(full);
        revalidate();
    }

    // Método para ocultar el menú
    public void hideMenu() {
        menu.hideMenuItem();
    }

    // Método para mostrar un formulario en el cuerpo del formulario principal
    public void showForm(Component component) {
        panelBody.removeAll();
        panelBody.add(component);
        panelBody.repaint();
        panelBody.revalidate();
    }

    // Método para establecer el menú seleccionado en el formulario principal
    public void setSelectedMenu(int index, int subIndex) {
        menu.setSelectedMenu(index, subIndex);
    }

    private Menu menu; // Menú lateral de la aplicación
    private JPanel panelBody; // Panel que contiene el cuerpo del formulario principal
    private JButton menuButton; // Botón para expandir/cerrar el menú

    // Clase interna que representa el diseño del formulario principal
    private class MainFormLayout implements LayoutManager {

        // Métodos de diseño que no se utilizan en este contexto
        @Override
        public void addLayoutComponent(String name, Component comp) {
        }

        @Override
        public void removeLayoutComponent(Component comp) {
        }

        @Override
        public Dimension preferredLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(5, 5);
            }
        }

        @Override
        public Dimension minimumLayoutSize(Container parent) {
            synchronized (parent.getTreeLock()) {
                return new Dimension(0, 0);
            }
        }

        // Método para diseñar los componentes en el formulario principal
        @Override
        public void layoutContainer(Container parent) {
            synchronized (parent.getTreeLock()) {
                boolean ltr = parent.getComponentOrientation().isLeftToRight();
                Insets insets = UIScale.scale(parent.getInsets());
                int x = insets.left;
                int y = insets.top;
                int width = parent.getWidth() - (insets.left + insets.right);
                int height = parent.getHeight() - (insets.top + insets.bottom);
                int menuWidth = UIScale.scale(menu.isMenuFull() ? menu.getMenuMaxWidth() : menu.getMenuMinWidth());
                int menuX = ltr ? x : x + width - menuWidth;
                menu.setBounds(menuX, y, menuWidth, height);
                int menuButtonWidth = menuButton.getPreferredSize().width;
                int menuButtonHeight = menuButton.getPreferredSize().height;
                int menubX;
                if (ltr) {
                    menubX = (int) (x + menuWidth - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.3f)));
                } else {
                    menubX = (int) (menuX - (menuButtonWidth * (menu.isMenuFull() ? 0.5f : 0.7f)));
                }
                menuButton.setBounds(menubX, UIScale.scale(30), menuButtonWidth, menuButtonHeight);
                int gap = UIScale.scale(5);
                int bodyWidth = width - menuWidth - gap;
                int bodyHeight = height;
                int bodyx = ltr ? (x + menuWidth + gap) : x;
                int bodyy = y;
                panelBody.setBounds(bodyx, bodyy, bodyWidth, bodyHeight);
            }
        }
    }
}
