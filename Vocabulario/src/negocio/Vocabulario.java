package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.LinkedList;
import java.util.Scanner;
import soporte.HashTable;

public class Vocabulario
{

    private HashTable ht = new HashTable();
    private File f;
    private String str = "";

    public Vocabulario()
    {

    }
    
    public Palabra[] getTabla()
    {
     return ht.getTabla();
    }

    public int getSizeHash(){
        return ht.getCantidad();
    }
    public void parserTexto(String str)
    {
        String delims = "[ \\p{Punct}¿¡0123456789ªº\\<\\>\\«\\»]+";

        String[] tokens = str.split(delims);

        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].isEmpty())
            {
                Palabra x = new Palabra(tokens[i].toLowerCase());
                ht.put(x, f.getName());
            }
        }

    }
    public void cargarHashDesdeBD(LinkedList<Palabra> lista)
    {
        while(!lista.isEmpty())
        {
            ht.putBD(lista.remove());
        }
    }

    @Override
    public String toString()
    {
        return "Vocabulario{" + "ht=" + ht + '}';
    }

    public void leerArchivo(File f)
    {
        this.f = f;

        try
        {
            Scanner sc = new Scanner(new FileInputStream(f), "ISO-8859-1");

            while (sc.hasNextLine())
            {
                String str = sc.nextLine();

                parserTexto(str);

            }
        } catch (FileNotFoundException | IllegalStateException e)
        {
            System.out.println("No existe el archivo de entrada..." + e.getMessage());
        }

    }
    
    
}
