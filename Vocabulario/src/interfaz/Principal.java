/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.File;
import java.nio.charset.Charset;
import negocio.Vocabulario;

/**
 *
 * @author pablo
 */
public class Principal
{
    public static void main(String[] args)
    {
         File arch = new File("I:\\UTN\\TSB\\TPI\\16082-8.txt");
         
         Vocabulario voc = new Vocabulario(arch);
         voc.leerArchivo(arch);
         System.out.println(voc.toString());
         
         
    }
    
}
