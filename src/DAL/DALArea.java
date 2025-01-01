package DAL;

import java.io.File;
import java.util.List;

public class DALArea {

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

    public void VerificarCarpeta(String ruta, String rutaInac) {

        File directoriobase = new File(ruta);

        if (!directoriobase.exists()) {
        // Si la ruta no existe, se crea en el disco local C:
        directoriobase = new File("C:\\Users\\"+System.getProperty("user.name") +"\\Desktop\\Clipping\\Archivos\\Activos" + ruta);
        }
        
        if (directoriobase.isDirectory() && directoriobase.exists()) {
            File[] areas = directoriobase.listFiles();

            if (areas != null) {
                for (File carpeta : areas) {
                    File request = new File(carpeta.getAbsolutePath() + "//Request");
                    if (!request.exists()) {
                        request.mkdirs();
                    }
                    File issue = new File(carpeta.getAbsolutePath() + "//Issue");
                    if (!issue.exists()) {
                        issue.mkdirs();
                    }
                    File inactivo = new File(rutaInac + "\\" + carpeta.getName());
                    if (!inactivo.exists()) {
                        inactivo.mkdirs();
                    }
                }
            }
        }

        File directoriobaseInac = new File(rutaInac);

        if (directoriobaseInac.isDirectory() && directoriobaseInac.exists()) {
            File[] areas = directoriobaseInac.listFiles();

            if (areas != null) {
                for (File carpeta : areas) {
                    File request = new File(carpeta.getAbsolutePath() + "//Request");
                    if (!request.exists()) {
                        request.mkdirs();
                    }
                    File issue = new File(carpeta.getAbsolutePath() + "//Issue");
                    if (!issue.exists()) {
                        issue.mkdirs();
                    }
                }
            }

        }
    }
}
