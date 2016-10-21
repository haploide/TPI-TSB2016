
package soporte;

import java.util.regex.Pattern;


public class Validaciones
{
    
    
    public static boolean esTexto(char st)
    {
        return !Pattern.matches("[0-9 \\p{Punct}]", st+"");
    }
    public static boolean estaVacio(String st)
    {
        return st.length()==0;
    }
    
}
