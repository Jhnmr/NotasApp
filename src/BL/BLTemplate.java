package BL;

import DAL.DALTemplate;
import ET.ETemplate;
import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BLTemplate {

    public void GuardarTemplate(ETemplate obj) throws IOException {

        obj.setNombre(obj.getNombre() + ".txt"); 

        DALTemplate daltemp = new DAL.DALTemplate();

        daltemp.GuardarTemplate(obj);
    }
    
    public void ActivarTemplate(String ruta, String rutaAct) {
        DALTemplate daltemp = new DAL.DALTemplate();
        daltemp.ActivarTemplate(ruta, rutaAct); 
    }
    
    public void EditarTemplate(ETemplate obj) throws IOException {

        obj.setNombre(obj.getNombre() + ".txt"); 

        DALTemplate daltemp = new DAL.DALTemplate();

        daltemp.GuardarTemplate(obj);
    } 
    
    public List<String> CargarCombo(String area, List<String> datos){
        
        DAL.DALTemplate daltemp = new DAL.DALTemplate();
        
        daltemp.CargarCombo(area, datos);
        
        return datos;
    }
    
    public void ValidarInactivacion(String area, String inactivo) throws IOException{
        
        File directorio = new File(area);
        DAL.DALTemplate daltemp = new DAL.DALTemplate();
        if (directorio.isDirectory() && directorio.exists()) {
            
            String[] archivos = directorio.list();
            
            for (int i = 0; i < archivos.length; i++) {
                
                File archivo = new File(inactivo + "\\" +  archivos[i]);
                if (archivo.exists()) {
                    Path p = Paths.get(area + "/" + archivos[i]);
                    daltemp.ValidadInactivacion(p);
                }
                
            }
        }
        
    }
    
    public ETemplate MostrarTemp(ET.ETemplate obj) throws IOException {
        
        DALTemplate daltemp = new DAL.DALTemplate();
        daltemp.MostrarTemplate(obj);
        return obj;
    }

    public List<String> LlenarCombo(String area, List<String> datos) {

        String ruta = area;

        File carpeta = new File(ruta);

        if (carpeta.isDirectory()) {
            File[] templates = carpeta.listFiles();

            if (templates != null) {
                for (File archivo : templates) {

                    datos.add(archivo.getName().replaceAll("\\.txt$", ""));
                }
            }
        } else {
            System.out.println("Directorio vacio");
        }

        return datos;
    }

    public ETemplate InactivarTemplate(ET.ETemplate obj, String rutaAct, String rutaInact) throws IOException {
        
        DALTemplate daltemp = new DAL.DALTemplate();
        daltemp.InactivarTemplate(obj, rutaAct, rutaInact);    
        return obj;
        
        
    }
    
    public ETemplate CargarInputs(ET.ETemplate obj) throws IOException {

        DALTemplate daltemp = new DAL.DALTemplate();
        daltemp.CargarInputs(obj);
        Scanner entrada = new Scanner(System.in);

        if (obj.getVariables() != null) { //Seguir aca

            List<String> textoTemporal = new ArrayList<>();
            List<String> textoCambiado = new ArrayList<>();
            List<String> contenido = new ArrayList<>();
            Boolean retVal = false;
            int xi = 0;

            //Imprimir texto               
        } else {
            System.out.println("El texto no contiene ninguna variable");
        }
        return obj;

    }

    public ETemplate ModificarTemplate(ET.ETemplate obj, List<String> cambios) throws IOException {

        DALTemplate daltemp = new DAL.DALTemplate();
        daltemp.ModificarTemplate(obj);

        List<String> textoTemporal = new ArrayList<>();
        List<String> textoCambiado = new ArrayList<>();
        String[] contenido = obj.getContenido().split("\n");
        for (int i = 0; i < contenido.length; i++) {
            textoCambiado.add(contenido[i]);
        }
        Boolean retVal = false;

        for (int v = 0; v < obj.getVariables().size(); v++) {
            for (int i = 0; i < contenido.length; i++) {

                String[] linea = contenido[i].split("\\s+");

                for (int n = 0; n < linea.length; n++) {

                    if (linea[n].contains(obj.getVariables().get(v))) {
                        linea[n] = cambios.get(v);
                        retVal = true;

                    }
                    if (n != 0) {
                        textoTemporal.add(" " + linea[n]);
                    } else {
                        textoTemporal.add(linea[n]);
                    }

                }
                if (retVal == true) {
                    String linea2 = String.join("", textoTemporal);
                    textoCambiado.set(i, linea2);
                    retVal = false;
                    linea2 = "";

                }
                textoTemporal.clear();
            }
        }

        obj.setContenido(String.join("\n", textoCambiado));
        return obj;
    }

}
