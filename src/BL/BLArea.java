
package BL;

import java.util.List;


public class BLArea {
    
    
    public List<String> CargarCombo(String area, List<String> datos){
        
        DAL.DALArea dala = new DAL.DALArea();
        
        dala.CargarCombo(area, datos);
        
        return datos;
    }
    
    public void VerificarCarpetas(String ruta, String rutaInac) {
        
        DAL.DALArea dala = new DAL.DALArea();
        
        dala.VerificarCarpeta(ruta, rutaInac);
    }
}
