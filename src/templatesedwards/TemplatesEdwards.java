package templatesedwards;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TemplatesEdwards {

    public static void main(String[] args) throws IOException {

        Scanner entrada = new Scanner(System.in);
        
        /*
        File ruta = new File(System.getProperty("user.home") + File.separator + "Edwards Lifesciences" + File.separator + "IT Costa Rica - Clippings");
        System.out.println("Ingrese el nombre del archivo:");
        String clipping = entrada.nextLine() + ".txt";
        File archivo = new File(ruta, clipping);
        archivo.createNewFile();
        String rutaconcreta = ruta + File.separator + clipping;
        FileWriter llenar = new FileWriter(rutaconcreta);
        System.out.println("Ingrese el contenido que irá en el archivo:");
        StringBuilder textoCompleto = new StringBuilder();
        while (entrada.hasNextLine()) {
            String linea = entrada.nextLine();
            
            if (linea.isEmpty()) {
                break;
            }
            
            textoCompleto.append(linea).append("\n");
        }
        
        String texto = textoCompleto.toString();
        llenar.write(texto);
        llenar.close();
        */

        System.out.println("Ahora vamos a leer el archivo");

        String rutaArchivo = System.getProperty("user.home") + File.separator + "Downloads" + File.separator + "Clipping" + File.separator + "Archivos" + File.separator + "Nota.txt";

        try {
            File archivo = new File(rutaArchivo);
            FileReader fr = new FileReader(archivo);
            BufferedReader br = new BufferedReader(fr);

            Scanner scn = new Scanner(archivo);

            while (scn.hasNextLine()) {
                System.out.println(scn.nextLine()); // Lee el archivo
            }
            String linea; //
            List<String> variables = new ArrayList<>();
            List<String> prueba = new ArrayList<>();
            List<String> temp = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                // Dividir la línea en palabras
                String[] palabras = linea.split(" ");
                String lineatemp = "";
                for (int i = 0; i < palabras.length; i++) {
                    if (palabras[i].contains("$")) {
                        variables.add(palabras[i]);
                    }
                    temp.add(palabras[i]);
                }
                lineatemp = String.join(" ", temp);
                temp.clear();
                prueba.add(lineatemp);
            }
            if (!variables.isEmpty()) {
                System.out.println("El texto contiene las siguientes variables:");

                List<String> textoTemporal = new ArrayList<>();
                Boolean retVal = false;

                for (String variable : variables) {

                    System.out.println("Ingrese el reemplazo de la variable=" + variable);
                    String cambio = entrada.nextLine();

                    for (int x = 0; x < prueba.size(); x++) {

                        String[] renglon = prueba.get(x).split("\\s+");

                        for (int n = 0; n < renglon.length; n++) {

                            if (renglon[n].contains(variable)) {
                                renglon[n] = cambio;
                                retVal = true;
                            }
                            if (n != 0) {
                                textoTemporal.add(" " + renglon[n]);
                            } else {
                                textoTemporal.add(renglon[n]);
                            }
                        }
                        if (retVal) {
                            String linea2 = String.join("", textoTemporal);
                            prueba.set(x, linea2);
                            retVal = false;
                        }
                        textoTemporal.clear();
                    }
                }
                // Imprimir texto
                for (String s : prueba) {
                    System.out.println(s);
                }
            } else {
                System.out.println("El texto no contiene ninguna variable");
            }

            br.close();
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
        }
        
        /*
        File ruta = new File(System.getProperty("user.home") + File.separator + "Edwards Lifesciences" + File.separator + "IT Costa Rica - Clippings" + File.separator + "Activos" + File.separator);
        File[] listaFiles = ruta.listFiles();
        int x = 0;
        for (File file : listaFiles) {
            System.out.println(x + " " + file.getName());
            x++;
        }
        System.out.println("Seleccione la carpeta a la que desea acceder");
        int opcion = entrada.nextInt();
        */
        
    }
}
