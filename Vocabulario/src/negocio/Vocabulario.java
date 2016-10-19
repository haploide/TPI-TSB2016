package negocio;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
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
        String delims = "[ \\p{Punct}¿¡0123456789ªº]+";

        String[] tokens = str.split(delims);

        for (int i = 0; i < tokens.length; i++)
        {
            System.out.println(tokens[i]);
        }

    }

    public void leerArchivo(File f)
    {
     
//         try(FileReader fr = new FileReader(f); BufferedReader br = new BufferedReader(fr))
//        {
//           String ln = br.readLine();
//           while(ln!=null)
//           {
//               String str = ln;
//               parserTexto(str);
//               
//               ln = br.readLine();
//           }
//        }
//        
//        catch(IOException e)
//        {
//            System.out.println("Error al procesar el archivo de entrada...");
//        }        
//     

        try (Scanner sc = new Scanner(f))
        {
            while (sc.hasNextLine())
            {
                String str = sc.nextLine();
                parserTexto(str);

//                System.out.println(str);
            }
        } catch (FileNotFoundException e)
        {
            System.out.println("No existe el archivo de entrada...");
        }
    }
}
