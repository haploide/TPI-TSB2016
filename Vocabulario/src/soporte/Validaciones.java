
package soporte;

import java.util.regex.Pattern;


public class Validaciones
{
    
    
    public static boolean esTexto(char st)
    {
        return Pattern.matches("[a-zA-Z]", st+"");
    }
    
}
