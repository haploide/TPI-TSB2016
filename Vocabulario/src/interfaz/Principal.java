/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package interfaz;

import java.io.File;
import java.nio.charset.Charset;
import negocio.Palabra;
import negocio.Vocabulario;
import soporte.*;

/**
 *
 * @author pablo
 */
public class Principal
{

    public static void main(String[] args)
    {
        File arch = new File("C:\\Users\\pablo\\Desktop\\TSB 2016\\00 TRABAJO PRACTICO\\16082-8.txt");
        DocumentoJDBC d = new DocumentoJDBC();
        Palabra p= new Palabra("petaJensen");
        PalablaJDBC p2= new PalablaJDBC();
        p2.Insert(p);
//        d.Insert("kaka");
//        d.Insert("pito");
//        d.Insert("poto");
        
//         Vocabulario voc = new Vocabulario(arch);
//         voc.leerArchivo(arch);
//         System.out.println(voc.toString());

    }

}
