package DAL;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;
import ET.ETemplate;
import java.io.FileNotFoundException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class DALTemplate {

    public void GuardarTemplate(ET.ETemplate obj) throws IOException {
        File ruta = new File(obj.getArea());

        if (!ruta.exists()) {
            ruta.mkdirs();
        }

        File archivo = new File(ruta, obj.getNombre());
        archivo.createNewFile();

        String rutaConcreta = new File(ruta, obj.getNombre()).getPath();

        FileWriter escribirArchivo = new FileWriter(rutaConcreta);

        escribirArchivo.write(obj.getContenido());
        escribirArchivo.close();
    }

    public void EditarTemplate(String nombre, String contenido) throws IOException {
        String homeDir = System.getProperty("user.home");
        File ruta = new File(homeDir, "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Activos");

        if (!ruta.exists()) {
            ruta.mkdirs();
        }

        File archivo = new File(ruta, nombre);
        if (archivo.exists()) {
            String rutaConcreta = archivo.getPath();

            FileWriter escribirArchivo = new FileWriter(rutaConcreta);
            escribirArchivo.write(contenido);
            escribirArchivo.close();
        }
    }

    public ETemplate MostrarTemplate(ET.ETemplate obj) throws FileNotFoundException, IOException {
        File archivo = new File(obj.getArea() + File.separator + obj.getNombre() + ".txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea;
        List<String> contenido = new ArrayList<>();

        while ((linea = br.readLine()) != null) {
            contenido.add(linea);
        }

        obj.setContenido(String.join("\n", contenido));

        return obj;
    }

    public ETemplate CargarInputs(ET.ETemplate obj) throws IOException {
        File archivo = new File(obj.getArea() + File.separator + obj.getNombre() + ".txt");
        FileReader fr = new FileReader(archivo);
        BufferedReader br = new BufferedReader(fr);

        String linea;
        List<String> variables = new ArrayList<>();
        List<String> contenido = new ArrayList<>();
        List<String> temp = new ArrayList<>();

        while ((linea = br.readLine()) != null) {
            String[] palabras = linea.split(" ");
            String lineatemp = "";
            for (String palabra : palabras) {
                if (palabra.contains("$")) {
                    variables.add(palabra);
                }
                temp.add(palabra);
            }
            lineatemp = String.join(" ", temp);
            temp.clear();
            contenido.add(lineatemp);
        }

        obj.setVariables(variables);
        obj.setContenido(String.join("\n", contenido));

        return obj;
    }

    public ETemplate ModificarTemplate(ET.ETemplate obj) throws IOException {
        return CargarInputs(obj);
    }

    public ETemplate InactivarTemplate(ET.ETemplate obj, String rutaArchivo, String rutaInactivo) throws IOException {
        Path origenPath = Paths.get(rutaArchivo);
        Path destinoPath = Paths.get(rutaInactivo);

        try {
            Files.copy(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo copiado exitosamente de " + origenPath + " a " + destinoPath);
        } catch (IOException e) {
            System.err.println("Error al mover el archivo: " + e.getMessage());
        }

        return obj;
    }

    public void ActivarTemplate(String rutaInac, String rutaAct) {
        Path origenPath = Paths.get(rutaInac + ".txt");
        Path destinoPath = Paths.get(rutaAct + ".txt");

        try {
            Files.move(origenPath, destinoPath, StandardCopyOption.REPLACE_EXISTING);
            System.out.println("Archivo movido exitosamente de " + origenPath + " a " + destinoPath);
        } catch (IOException e) {
            System.err.println("Error al mover el archivo: " + e.getMessage());
        }
    }

    public void ValidadInactivacion(Path p) throws IOException {
        Files.delete(p);
    }

    public List<String> CargarCombo(String area, List<String> datos) {
        File directorio = new File(area);

        if (directorio.isDirectory() && directorio.exists()) {
            File[] archivos = directorio.listFiles();

            if (archivos != null) {
                for (File archivo : archivos) {
                    if (archivo.isFile()) {
                        datos.add(archivo.getName().replaceAll("\\.txt$", ""));
                    }
                }
            }
        }

        return datos;
    }
}
