package BL;

import java.util.List;

public class BLTipoTemplate {
    
    public List<String> CargarCombo(String area, List<String> datos){
        
        DAL.DALArea dala = new DAL.DALArea();
        
        dala.CargarCombo(area, datos);
        
        return datos;
    }
    
}
