
package negocio;

import java.util.LinkedList;


public class Palabra implements Comparable<Palabra>
{
    private String palabra;
    private int frecuencia;
    private LinkedList<String> documentos= new LinkedList<>();

    public String getPalabra()
    {
        return palabra;
    }

    public int getFrecuencia()
    {
        return frecuencia;
    }

    public LinkedList<String> getDocumentos()
    {
        return documentos;
    }

    public Palabra(String palabra)
    {
        this.palabra = palabra;
        this.frecuencia=1;
    }
    
    public void incrementarFrecuencia()
    {
        frecuencia++;
    }
    public void agregarDocumento(String doc)
    {
        documentos.add(doc);
        
    }
    @Override
    public boolean equals (Object obj)
    {
      if( obj == null ) { return false; }
      if( ! (obj instanceof Palabra) ) { return false; }
      
      Palabra x = (Palabra) obj;    
      return palabra.equals(x.palabra);
    }
    @Override
    public int hashCode()
    {
      return Math.abs(palabra.hashCode()); 
    }
    
    @Override
    public int compareTo(Palabra o)
    {
        return palabra.compareTo(o.palabra);
    }
    
    
}
