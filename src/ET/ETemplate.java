
package ET;

import java.util.ArrayList;
import java.util.List;


public class ETemplate {

    private String nombre;
    private boolean estado;
    private List<String> variables = new ArrayList<>();
    private String contenido;
    private String Area;

    public ETemplate(String nombre, boolean estado, String contenido, String Area) {
        this.nombre = nombre;
        this.estado = estado;
        this.contenido = contenido;
        this.Area = Area;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public List<String> getVariables() {
        return variables;
    }

    public void setVariables(List<String> variables) {
        this.variables = variables;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getArea() {
        return Area;
    }

    public void setArea(String Area) {
        this.Area = Area;
    }



    

    
    
    
    
}
