package soporte;

import negocio.Palabra;


public class Persistencia
{
   
    public void guardarEnBD(Palabra p)
    {
       
            try
            {
                PalablaJDBC.Insert(p);
                
            } catch (Exception e)
            {
                
            }
    }
    
}
