package soporte;

import java.util.ArrayList;
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

    public static ArrayList<Palabra> getAllPalabras()
    {
        return PalablaJDBC.getAll();
    }

    public static ArrayList<Palabra> getByFilterPalabras(String palabra)
    {
        return PalablaJDBC.getByFilter(palabra);
    }

}
