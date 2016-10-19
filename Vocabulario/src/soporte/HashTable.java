package soporte;

import negocio.Palabra;

public class HashTable
{

    private Palabra tabla[];
    private int cantidad;

    public HashTable(int n)
    {
        if (n <= 0)
        {
            n = 997;
        }

        tabla = new Palabra[n];
        cantidad = 0;

    }

    public HashTable()
    {
        this(997);
    }

    private int hash(int k)
    {
        k = Math.abs(k);
        return k % tabla.length;
    }

    public void put(Palabra x, String doc)
    {

        if (x == null)
        {
            return;
        }

        if (averageLength() >= 8)
        {
            rehash();
        }

        int index = hash(x.hashCode());

        boolean found = false;
        while (!found)
        {
            if (tabla[index] == null)
            {
                cantidad++;
                x.agregarDocumento(doc);
                tabla[index] = x;
                System.out.println("Agregada: "+tabla[index]+" en :"+ index);
                found = true;
            } else if (tabla[index].equals(x))
            {
                tabla[index].incrementarFrecuencia();
                
                if(!tabla[index].contains(doc))
                {
                    tabla[index].agregarDocumento(doc);
                }
                System.out.println("Agregada: "+tabla[index]+" en :"+ index);
                found = true;
            } else
            {
                index++;
            }
            if (index == tabla.length)
            {
                index = 0;
            }
        }
    }

    protected void rehash()
    {
        int n = (int) (1.5 * tabla.length);

        n = siguientePrimo(n);

        Palabra temp[] = new Palabra[n];

        for (int i = 0; i < tabla.length; i++)
        {
            int k = Math.abs(tabla[i].hashCode());
            int y = k % temp.length;

            boolean found = false;
            while (!found)
            {
                if (temp[y] == null)
                {
                    temp[y] = tabla[i];
                    found = true;
                    
                } else 
                {
                    y++;
                }
                if (y == temp.length)
                {
                    y = 0;
                }
            }
        }

        tabla = temp;
    }

    private int averageLength()
    {
        return cantidad / tabla.length;
    }

    public static boolean esPrimo(int n)
    {
        if (n == 2)
        {
            return true;
        }
        if (n % 2 == 0)
        {
            return false;
        }
        long raiz = (long) Math.sqrt(n);
        for (long div = 3; div <= raiz; div += 2)
        {
            if (n % div == 0)
            {
                return false;
            }
        }
        return true;
    }

    @SuppressWarnings("empty-statement")
    public static int siguientePrimo(int n)
    {
        if (n <= 1)
        {
            return 3;
        }
        if (n % 2 == 0)
        {
            n++;
        }
        for (; !esPrimo(n); n += 2) ;
        return n;
    }

    public boolean isEmpty()
    {
        return cantidad == 0;
    }

    public Palabra[] getTabla()
    {
        return tabla;
    }

    public int getCantidad()
    {
        return cantidad;
    }
    

}
