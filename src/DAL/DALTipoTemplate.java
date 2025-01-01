package DAL;

import java.io.File;
import java.util.List;


public class DALTipoTemplate {
    
    public List<String> CargarCombo(String area, List<String> datos) {

        File directorio = new File(area);

        if (directorio.isDirectory() && directorio.exists()) {

            File[] archivos = directorio.listFiles();

            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isDirectory()) {
                        datos.add(archivo.getName());
                    }
                }
            }
        }

        return datos;

    }
    
}
