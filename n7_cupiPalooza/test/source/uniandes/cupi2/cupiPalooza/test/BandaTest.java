/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogot� - Colombia)
 * Departamento de  Ingenier�a  de  Sistemas    y   Computaci�n
 * Licenciado   bajo    el  esquema Academic Free License versi�n 2.1
 *      
 * Proyecto Cupi2   (http://cupi2.uniandes.edu.co)
 * Ejercicio: n7_festival
 * Autor: Equipo Cupi2 2017
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.cupi2.cupiPalooza.test;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Clase usada para verificar que los m�todos de la clase Banda est�n correctamente implementados.
 */
public class BandaTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Banda banda;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase Banda. Este m�todo se ejecuta antes de cada m�todo de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        banda = new Banda( "Caifanes", 56000, 5, 5000000, "./data/imagenes/bandas/rock.png" );
    }

    // -----------------------------------------------------------------
    // M�todos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el m�todo Banda.<br>
     * <b>M�todos a probar:</b><br>
     * Banda<br>
     * darNombre<br>
     * darNumeroDeFans<br>
     * darNumeroDeCanciones<br>
     * darCosto<br>
     * darRutaImagen<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el correctamente, se comprueba cada uno de sus atributos.
     */
    @Test
    public void testBanda( )
    {
        assertTrue( "El nombre no es correcto", banda.darNombre( ).equals( "Caifanes" ) );
        assertEquals( "El n�mero de canciones no es correcto", banda.darCantidadDeCanciones( ), 5 );
        assertTrue( "El costo no es correcto", 5000000 == banda.darCosto( ) );
        assertTrue( "La ruta de la imagen no es correcta", banda.darRutaImagen( ).equals( "./data/imagenes/bandas/rock.png" ) );
    }

    /**
     * Prueba 2: Verifica el m�todo compararPorNombre. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorNombre<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen el mismo nombre.<br>
     * 2) La banda contra la cual se compara tiene un nombre lexicogr�ficamente mayor. <br>
     * 3) La banda contra la cual se compara tiene un nombre lexicogr�ficamente menor. <br>
     */
    @Test
    public void testCompararPorNombre( )
    {
        // TODO Parte 2 Punto D: Completar el m�todo seg�n la documentaci�n dada.
    	Banda banda1= new Banda("Caifanes", 783, 6, 345656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda2= new Banda("Zolaida", 784, 5, 3656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda3= new Banda("Ade", 73, 6, 5656,"./data/imagenes/bandas/rock.png"  );
    	assertEquals( "El resultado no es el esperado", banda.compararPorNombre(banda1), 0 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorNombre(banda2), -1 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorNombre(banda3), 1 );
    	 
    	  
         
    }

    /**
     * Prueba 3: Verifica el m�todo compararPorCantidadDeFans. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeFans<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de fans.<br>
     * 2) La banda contra la cual se compara tiene mayor cantidad de fans. <br>
     * 3) La banda contra la cual se compara tiene menor cantidad de fans. <br>
     */
    @Test
    public void testCompararPorCantidadDeFans( )
    {
        // TODO Parte 2 Punto E: Completar el m�todo seg�n la documentaci�n dada.
    	Banda banda1= new Banda("Caifanes", 56000, 6, 345656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda2= new Banda("Zolaida", 56050, 5, 3656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda3= new Banda("Ade", 73, 6, 5656,"./data/imagenes/bandas/rock.png"  );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeFans(banda1), 0 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeFans(banda2), -1 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeFans(banda3), 1 );
    	 
    }
    

    /**
     * Prueba 4: Verifica el m�todo compararPorCantidadDeCanciones. <br>
     * <b>M�todos a probar:</b> <br>
     * Banda<br>
     * compararPorCantidadDeCanciones<br>
     * <b> Casos de prueba: </b><br>
     * 1) Las dos bandas tienen la misma cantidad de canciones.<br>
     * 2) La banda contra la cual se compara tiene mayor cantidad de canciones. <br>
     * 3) La banda contra la cual se compara tiene menor cantidad de canciones. <br>
     */
    @Test
    public void testCompararPorCantidadDeCanciones( )
    {
    	        // TODO Parte 2 Punto F: Completar el m�todo seg�n la documentaci�n dada.
    	Banda banda1= new Banda("Caifanes", 56000, 5, 345656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda2= new Banda("Zolaida", 56050, 8, 3656,"./data/imagenes/bandas/rock.png"  );
    	Banda banda3= new Banda("Ade", 73, 3, 5656,"./data/imagenes/bandas/rock.png"  );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeCanciones(banda1), 0 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeCanciones(banda2), -1 );
    	assertEquals( "El resultado no es el esperado", banda.compararPorCantidadDeCanciones(banda3), 1 );
    	 
    }

}
