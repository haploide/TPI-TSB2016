package negocio;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import soporte.HashTable;

public class Vocabulario
{

    private HashTable ht = new HashTable();
    private File f;
    private String str = "";

    public Vocabulario(File f)
    {
        this.f = f;
    }

    public void parserTexto( String str)
    {
        String delims = "[ \\p{Punct}¿¡0123456789ªº\\<\\>]+";

        String[] tokens = str.split(delims);

        for (int i = 0; i < tokens.length; i++)
        {
            if (!tokens[i].isEmpty())
            {
                System.out.println(tokens[i]);
            }
        }

    }

    public void leerArchivo(File f)
    {
     
 
        try 
        {
            Scanner sc = new Scanner(new FileInputStream(f),"ISO-8859-1");
            
            
            while (sc.hasNextLine())
            {
                String str = sc.nextLine();
                parserTexto(str);

//                System.out.println(str);
            }
        } catch (FileNotFoundException | IllegalStateException e)
        {
            System.out.println("No existe el archivo de entrada..."+e.getMessage());
        }
        
        
    }
}
