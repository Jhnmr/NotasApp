package menu;

import Vistas.FormIndex;
import menu.mode.LightDarkMode;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.ui.FlatUIUtils;
import com.formdev.flatlaf.util.UIScale;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.prefs.Preferences;
import java.util.stream.Collectors;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import menu.mode.ToolBarAccentColor;


public class Menu extends JPanel {

    private final String menuItems[][] = {
        {"~Main~"},
        {"Begin"},
        {"~Clippings~"},
        {"Options", "Add New Clipping","Reactivate Clipping","Clipping Tool","Add New Folder","Created By"},
        {"~Folders~"}
    };

    public boolean isMenuFull() {
        return menuFull;
    }

    public void setMenuFull(boolean menuFull) {
        this.menuFull = menuFull;
        if (menuFull) {
            header.setText(headerName);
            header.setHorizontalAlignment(getComponentOrientation().isLeftToRight() ? JLabel.LEFT : JLabel.RIGHT);
        } else {
            header.setText("");
            header.setHorizontalAlignment(JLabel.CENTER);
        }
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).setFull(menuFull);
            }
        }
        lightDarkMode.setMenuFull(menuFull);
        toolBarAccentColor.setMenuFull(menuFull);
    }

    private final List<MenuEvent> events = new ArrayList<>();
    private boolean menuFull = true;
    private final String headerName = "";
    private JTree directoryTree;
    private DefaultMutableTreeNode root;
    
    protected final boolean hideMenuTitleOnMinimum = true;
    protected final int menuTitleLeftInset = 5;
    protected final int menuTitleVgap = 5;
    protected final int menuMaxWidth = 250;
    protected final int menuMinWidth = 60;
    protected final int headerFullHgap = 5;

    private static final String PREFS_KEY = "DirectoriosAgregados";
    private Preferences prefs;
    private FormIndex formIndex;

    public Menu() {
        this.formIndex = formIndex;
        prefs = Preferences.userNodeForPackage(getClass());
        init();
        cargarDirectoriosGuardados();
        initTree();
    }

    private void init() {
    setLayout(new MenuLayout());

     panelMenu = new JPanel(new MenuItemLayout(this));
    panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5;"
            + "background:$Menu.background");


    putClientProperty(FlatClientProperties.STYLE, ""
            + "border:20,2,2,2;"
            + "background:$Menu.background;"
            + "arc:10");
    header = new JLabel(headerName);
    header.putClientProperty(FlatClientProperties.STYLE, ""
            + "font:$Menu.header.font;"
            + "foreground:$Menu.foreground");

    //  Menu
    scroll = new JScrollPane();
    // No necesitas volver a crear panelMenu aquí
    // panelMenu = new JPanel(new MenuItemLayout(this));
    panelMenu.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:5,5,5,5;"
            + "background:$Menu.background");

    scroll.setViewportView(panelMenu);
    scroll.putClientProperty(FlatClientProperties.STYLE, ""
            + "border:null");
    JScrollBar vscroll = scroll.getVerticalScrollBar();
    vscroll.setUnitIncrement(10);
    vscroll.putClientProperty(FlatClientProperties.STYLE, ""
            + "width:$Menu.scroll.width;"
            + "trackInsets:$Menu.scroll.trackInsets;"
            + "thumbInsets:$Menu.scroll.thumbInsets;"
            + "background:$Menu.ScrollBar.background;"
            + "thumb:$Menu.ScrollBar.thumb");
    createMenu();
    lightDarkMode = new LightDarkMode();
    toolBarAccentColor = new ToolBarAccentColor(this);
    toolBarAccentColor.setVisible(FlatUIUtils.getUIBoolean("AccentControl.show", false));

  
    // Inicializa el JTree
        directoryTree = new JTree();
        root = new DefaultMutableTreeNode("Directorios");
        directoryTree.setModel(new DefaultTreeModel(root));

        // Agrega el JTree al formulario
        JScrollPane treeScrollPane = new JScrollPane(directoryTree);
        add(treeScrollPane);
    
    
    

    add(header);
    add(scroll);
    add(lightDarkMode);
    add(toolBarAccentColor);
    
}

    private void initTree() {
        // Asegúrate de que el modelo del árbol se actualiza cuando cambian los directorios
        DefaultTreeModel treeModel = (DefaultTreeModel) directoryTree.getModel();
        for (File directorio : directorios) {
            addDirectoryToTree(directorio);
        }
        treeModel.reload();
    }

     public void updatePanelWithDirectories(List<File> directorios) {
        // Actualiza la lista de directorios y el JTree
        this.directorios = directorios;
        updateTree();
    }

    private void updateTree() {
        // Limpia el JTree y vuelve a agregar los directorios
        root.removeAllChildren();
        for (File directorio : directorios) {
            addDirectoryToTree(directorio);
        }

        // Notifica al modelo del árbol que los datos han cambiado
        DefaultTreeModel treeModel = (DefaultTreeModel) directoryTree.getModel();
        treeModel.reload();
    }

    private void addDirectoryToTree(File directorio) {
        // Agrega un nodo al JTree para el directorio dado
        DefaultMutableTreeNode directoryNode = new DefaultMutableTreeNode(directorio.getName());
        root.add(directoryNode);
        // Puedes personalizar esta lógica para incluir subdirectorios, archivos, etc.
    }

public void agregarDirectorio() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY);

    // Establecer la ruta inicial del JFileChooser en el escritorio
    String homeDir = System.getProperty("user.home");
    String escritorio = homeDir + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos";
    fileChooser.setCurrentDirectory(new File(escritorio));

    int result = fileChooser.showOpenDialog(this);

    if (result == JFileChooser.APPROVE_OPTION) {
        File directorioSeleccionado = fileChooser.getSelectedFile();
        // Imprime la ruta completa del directorio seleccionado
        System.out.println("Ruta del directorio seleccionado: " + directorioSeleccionado.getAbsolutePath());
        // Ahora puedes guardar el directorioSeleccionado junto con tus datos
        // Por ejemplo, puedes imprimir la ruta en la consola
        System.out.println("Directorio seleccionado: " + directorioSeleccionado.getAbsolutePath());
        // Puedes adaptar esta lógica para guardar la ruta del directorio seleccionado
        // junto con tus datos en el array bidimensional
        // arraybidimencional.add(directorioSeleccionado.getAbsolutePath());
        // Actualizar el menú después de agregar el directorio
        addDirectoryToMenu(directorioSeleccionado);
        // Guardar la preferencia después de agregar un directorio
        guardarDirectorio(directorioSeleccionado.getAbsolutePath());
        // Agrega el directorio a la lista de directorios
        directorios.add(directorioSeleccionado);
        // Llama al método en FormIndex para actualizar el contenido
        formIndex.updatePanelWithDirectories(directorios);
    }
}

    
     // Agrega este método para seleccionar automáticamente el directorio
    private void selectDirectory(String nombreDirectorio) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) com;
                if (nombreDirectorio.equals(menuItem.getName())) {
                    menuItem.setSelectedIndex(0); // Suponiendo que 0 representa la primera opción en el menú
                    break;
                }
            }
        }
    }
   
    private void cargarDirectoriosGuardados() {
    // Recuperar la lista de directorios guardados desde las preferencias
    String directoriosGuardados = prefs.get(PREFS_KEY, "");
 
    System.out.println("Cadena de directorios guardados en preferencias: " + directoriosGuardados);
 
    // Verificar si la cadena de preferencias está vacía
    if (!directoriosGuardados.isEmpty()) {
        // Dividir la cadena en una matriz de rutas de directorios
        String[] directorios = directoriosGuardados.split(",");
 
        System.out.println("Número de elementos después de dividir: " + directorios.length);
 
        // Filtrar los nombres de directorios nulos o vacíos
        List<String> directoriosFiltrados = Arrays.stream(directorios)
                .map(String::trim) // Eliminar espacios en blanco alrededor de los nombres
                .filter(nombre -> nombre != null && !nombre.isEmpty() && !nombre.equalsIgnoreCase("null"))
                .collect(Collectors.toList());
 
        // Limpiar las preferencias antes de agregar directorios filtrados
        prefs.remove(PREFS_KEY);
 
        // Agregar los directorios al menú
        for (String directorio : directoriosFiltrados) {
            if (!directorio.equalsIgnoreCase("null")) {
                System.out.println("Directorio cargado desde preferencias: " + directorio);
                File directorioFile = new File(directorio);
                addDirectoryToMenu(directorioFile);
            }
        }
 
        // Guardar nuevamente los directorios en las preferencias
        saveDirectoriesToPreferences();
    } else {
        System.out.println("No hay directorios guardados en las preferencias.");
    }
}
    
    private void saveDirectoriesToPreferences() {
    // Obtener todas las rutas de los directorios del menú
    List<String> directorios = new ArrayList<>();
    for (Component com : panelMenu.getComponents()) {
        if (com instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) com;
            directorios.add(menuItem.getName());
        }
    }

    // Convertir la lista de directorios a una cadena separada por comas
    String directoriosGuardados = String.join(",", directorios);

    // Guardar la cadena en las preferencias
    prefs.put(PREFS_KEY, directoriosGuardados);
}

private void addDirectoryToMenu(File directorio) {
    String nombreDirectorio = obtenerNombreDirectorio(directorio.getAbsolutePath());

    // Verificar si el directorio ya está presente en el menú
    if (!isDirectorioPresente(nombreDirectorio)) {
        MenuItem menuItem = new MenuItem(this, new String[]{nombreDirectorio}, 0, events);
        
        // Agregar un MouseListener al MenuItem
        menuItem.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // Verificar si se hizo clic derecho
                if (SwingUtilities.isRightMouseButton(e)) {
                    // Mostrar un menú contextual para borrar el directorio
                    mostrarMenuContextual(directorio, e.getX(), e.getY());
                }
            }
        });

        panelMenu.add(menuItem);
        revalidate();
        repaint();
        saveDirectoryToPreferences(nombreDirectorio);
    } else {
        System.out.println("El directorio ya está presente en el menú.");
    }
    // Actualizar el JTextArea de FormIndex con el contenido del directorio
    formIndex.updatePanelWithDirectories(directorios);
}

    private boolean isDirectorioPresente(String nombreDirectorio) {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                MenuItem menuItem = (MenuItem) com;
                if (nombreDirectorio.equals(menuItem.getName())) {
                    return true;
                }
            }
        }
        return false;
    }
    
private void guardarDirectorio(String nombreDirectorio) {
    // Obtener todas las rutas de los directorios del menú
    List<String> directorios = new ArrayList<>();
    for (Component com : panelMenu.getComponents()) {
        if (com instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) com;
            directorios.add(menuItem.getName());
        }
    }

    // Agregar el nuevo directorio a la lista si no es nulo
    if (nombreDirectorio != null) {
        directorios.add(nombreDirectorio);
    }

    // Convertir la lista de directorios a una cadena separada por comas
    String directoriosGuardados = String.join(",", directorios);

    // Guardar la cadena en las preferencias
    prefs.put(PREFS_KEY, directoriosGuardados);
}

    private String obtenerNombreDirectorio(String ruta) {
    File file = new File(ruta);
    String nombre = file.getName();
    System.out.println("Nombre del directorio obtenido: " + nombre);
    return nombre;
}

private void saveDirectoryToPreferences(String nombreDirectorio) {
    // Obtener todas las rutas de los directorios del menú
    List<String> directorios = new ArrayList<>();
    for (Component com : panelMenu.getComponents()) {
        if (com instanceof MenuItem) {
            MenuItem menuItem = (MenuItem) com;
            directorios.add(menuItem.getName());
        }
    }

    // Agregar el nuevo directorio a la lista
    directorios.add(nombreDirectorio);

    // Convertir la lista de directorios a una cadena separada por comas
    String directoriosGuardados = String.join(",", directorios);

    // Guardar la cadena en las preferencias
    prefs.put(PREFS_KEY, directoriosGuardados);
}

    private void mostrarMenuContextual(File directorio, int x, int y) {
    JPopupMenu popupMenu = new JPopupMenu();

    // Agregar una opción para borrar el directorio
    JMenuItem borrarMenuItem = new JMenuItem("Borrar");
    borrarMenuItem.addActionListener(new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Lógica para borrar el directorio
          //  borrarDirectorio(directorio);
        }
    });
    popupMenu.add(borrarMenuItem);

    // Mostrar el menú contextual en la posición del clic
    popupMenu.show(panelMenu, x, y);
    
// Agregar una opción para cambiar el nombre del directorio
JMenuItem cambiarNombreMenuItem = new JMenuItem("Cambiar Nombre");
cambiarNombreMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica para cambiar el nombre del directorio
        //cambiarNombreDirectorio(directorio);
    }
});
popupMenu.add(cambiarNombreMenuItem);

// Agregar una opción para ver el contenido del directorio
JMenuItem verContenidoMenuItem = new JMenuItem("Ver Contenido");
verContenidoMenuItem.addActionListener(new ActionListener() {
    @Override
    public void actionPerformed(ActionEvent e) {
        // Lógica para ver el contenido del directorio
      //  verContenidoDirectorio(directorio);
    }
});
popupMenu.add(verContenidoMenuItem);

}
    
    
   
    private void createMenu() {
        int index = 0;
        for (int i = 0; i < menuItems.length; i++) {
            String menuName = menuItems[i][0];
            if (menuName.startsWith("~") && menuName.endsWith("~")) {
                panelMenu.add(createTitle(menuName));
            } else {
                MenuItem menuItem = new MenuItem(this, menuItems[i], index++, events);
                panelMenu.add(menuItem);
            }
        }
    }

    private JLabel createTitle(String title) {
        String menuName = title.substring(1, title.length() - 1);
        JLabel lbTitle = new JLabel(menuName);
        lbTitle.putClientProperty(FlatClientProperties.STYLE, ""
                + "font:$Menu.label.font;"
                + "foreground:$Menu.title.foreground");
        return lbTitle;
    }

    public void setSelectedMenu(int index, int subIndex) {
        runEvent(index, subIndex);
    }

    protected void setSelected(int index, int subIndex) {
        int size = panelMenu.getComponentCount();
        for (int i = 0; i < size; i++) {
            Component com = panelMenu.getComponent(i);
            if (com instanceof MenuItem) {
                MenuItem item = (MenuItem) com;
                if (item.getMenuIndex() == index) {
                    item.setSelectedIndex(subIndex);
                } else {
                    item.setSelectedIndex(-1);
                }
            }
        }
    }

    protected void runEvent(int index, int subIndex) {
        MenuAction menuAction = new MenuAction();
        for (MenuEvent event : events) {
            event.menuSelected(index, subIndex, menuAction);
        }
        if (!menuAction.isCancel()) {
            setSelected(index, subIndex);
        }
    }

    public void addMenuEvent(MenuEvent event) {
        events.add(event);
    }

    public void hideMenuItem() {
        for (Component com : panelMenu.getComponents()) {
            if (com instanceof MenuItem) {
                ((MenuItem) com).hideMenuItem();
            }
        }
        revalidate();
    }

    public boolean isHideMenuTitleOnMinimum() {
        return hideMenuTitleOnMinimum;
    }

    public int getMenuTitleLeftInset() {
        return menuTitleLeftInset;
    }

    public int getMenuTitleVgap() {
        return menuTitleVgap;
    }

    public int getMenuMaxWidth() {
        return menuMaxWidth;
    }

    public int getMenuMinWidth() {
        return menuMinWidth;
    }

    private JLabel header;
    private JScrollPane scroll;
    private JPanel panelMenu;
    private LightDarkMode lightDarkMode;
    private ToolBarAccentColor toolBarAccentColor;

    private class MenuLayout implements LayoutManager {

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

        @Override
public void layoutContainer(Container parent) {
    synchronized (parent.getTreeLock()) {
        Insets insets = parent.getInsets();
        int x = insets.left;
        int y = insets.top;
        int gap = UIScale.scale(12);
        int sheaderFullHgap = UIScale.scale(headerFullHgap);
        int width = parent.getWidth() - (insets.left + insets.right);
        int height = parent.getHeight() - (insets.top + insets.bottom);
        int iconWidth = width;
        int iconHeight = header.getPreferredSize().height;
        int hgap = menuFull ? sheaderFullHgap : 0;
        int accentColorHeight = 0;
        if (toolBarAccentColor.isVisible()) {
            accentColorHeight = toolBarAccentColor.getPreferredSize().height + gap;
        }

        header.setBounds(x + hgap, y, iconWidth - (hgap * 2), iconHeight);
        int ldgap = UIScale.scale(10);
        int ldWidth = width - ldgap * 2;
        int ldHeight = lightDarkMode.getPreferredSize().height;
        int ldx = x + ldgap;
        int ldy = y + height - ldHeight - ldgap - accentColorHeight;

        int menux = x;
        int menuy = y + iconHeight + gap;
        int menuWidth = width;
        int menuHeight = height - (iconHeight + gap) - (ldHeight + ldgap * 2) - (accentColorHeight);

        // Ajusta las coordenadas y dimensiones del JScrollPane
        scroll.setBounds(menux, menuy, menuWidth, menuHeight);
        directoryTree.setBounds(menux, menuy, menuWidth, menuHeight);

        lightDarkMode.setBounds(ldx, ldy, ldWidth, ldHeight);


        if (toolBarAccentColor.isVisible()) {
            int tbheight = toolBarAccentColor.getPreferredSize().height;
            int tbwidth = Math.min(toolBarAccentColor.getPreferredSize().width, ldWidth);
            int tby = y + height - tbheight - ldgap;
            int tbx = ldx + ((ldWidth - tbwidth) / 2);
            toolBarAccentColor.setBounds(tbx, tby, tbwidth, tbheight);
        }
    }
}
    }


     private List<File> directorios = new ArrayList<>();

    public List<File> getDirectorios() {
        return directorios;
    }
}