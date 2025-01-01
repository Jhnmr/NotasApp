

package GUI;

import java.io.IOException;
import Vista.Clipping;
import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import java.awt.Font;
import javax.swing.UIManager;

public class GUITemplate {
    
    public static void Rar(String[] Args) throws IOException{
        /*
        Scanner leer = new Scanner(System.in);
        
        System.out.println("Ingrese el nombre del archivo");
        String nombreArchivo = leer.nextLine();
        
        System.out.println("Seleccione el area en la que se guardara el archivo");
        
        System.out.println("Ingrese el contenido del archivo");
        StringBuilder textoCompleto = new StringBuilder();
        while (leer.hasNextLine()){
            String linea = leer.nextLine();
            
            if(linea.isEmpty()){
                break;
            }
            
            textoCompleto.append(linea).append("\n");
        }
        String contenidoArchivo = textoCompleto.toString();
        
        ETemplate etemp = new ET.ETemplate( nombreArchivo, true, contenidoArchivo, "");
        
        BLTemplate bltemp = new BL.BLTemplate();
        
        bltemp.GuardarTemplate(etemp);
        
        */
        // Instala la fuente Roboto para la interfaz gráfica
        FlatRobotoFont.install();
        // Registra el origen personalizado de los valores predeterminados de FlatLaf
        FlatLaf.registerCustomDefaultsSource("theme");
        // Configura la fuente predeterminada para la interfaz gráfica
        UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 13));
        // Configura el tema oscuro de FlatLaf
        FlatMacDarkLaf.setup();
        // Inicia la aplicación en el hilo de eventos de la interfaz gráfica
        java.awt.EventQueue.invokeLater(() -> {
            // Crea una instancia de la clase Clipping y la hace visible
           Clipping app = new Vista.Clipping();
            app.setVisible(true);
        });

    }
 
}
