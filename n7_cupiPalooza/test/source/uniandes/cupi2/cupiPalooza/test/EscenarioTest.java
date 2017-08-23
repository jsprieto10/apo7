/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad  de  los Andes   (Bogotá - Colombia)
 * Departamento de  Ingeniería  de  Sistemas    y   Computación
 * Licenciado   bajo    el  esquema Academic Free License versión 2.1
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
import uniandes.cupi2.cupiPalooza.mundo.Escenario;

/**
 * Clase usada para verificar que los métodos de la clase Escenario estén correctamente implementados.
 */
public class EscenarioTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenario del festival
     */
    private Escenario escenario;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase escenario. Este método se ejecuta antes de cada método de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        escenario = new Escenario( 1, "Fujifilm", 3000000 );
    }

    /**
     * Agrega 3 bandas al escenario
     */
    public void setupEscenario2( )
    {
        escenario.agregarBanda( "Aventureros", 125000, 4, 750000, "./data/imagenes/bandas/pop.png" );
        escenario.agregarBanda( "Borbotones", 100000, 3, 850000, "./data/imagenes/bandas/rock.png" );
        escenario.agregarBanda( "Caifanes", 50000, 8, 1200000, "./data/imagenes/bandas/country.png" );
    }

    /**
     * Agrega 2 bandas al escenario para dejarlo al máximo de capacidad
     */
    public void setupEscenario3( )
    {
        setupEscenario2( );
        escenario.agregarBanda( "On Planets", 110000, 5, 20000, "./data/imagenes/bandas/pop.png" );
        escenario.agregarBanda( "Other", 80000, 7, 20000, "./data/imagenes/bandas/rock.png" );
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el método Escenario.<br>
     * <b>Métodos a probar:</b><br>
     * Festival<br>
     * darBandas<br>
     * darNumero<br>
     * darPatrocinador<br>
     * darPresupuesto<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el correctamente.<br>
     */
    @Test
    public void testEscenario( )
    {
        assertNotNull( "No se inicializó el arreglo de bandas correctamente.", escenario.darBandas( ) );
        assertEquals( "El arreglo de bandas no está vacío", escenario.darBandas( ).size( ), 0 );
        assertEquals( "El número del escenario no es correcto", 1, escenario.darNumero( ) );

        assertNotNull( "El patrocinador del escenario debería existir", escenario.darPatrocinador( ) );
        assertTrue( "El presupuesto actual debe ser mayor a 0", 0 < escenario.darPresupuesto( ) );

    }

    /**
     * <b>Prueba 3:</b> Verifica el método darCostoAcumulado.<br>
     * <b>Métodos a probar:</b><br>
     * darCostoAcumulado<br>
     * <b>Casos de prueba:</b><br>
     * 1. No hay ninguna banda en el escenario.<br>
     * 2. Hay tres bandas en el escenario.<br>
     * 3. Hay cinco bandas en el escenario.
     */
    @Test
    public void testDarCostoAcumulado( )
    {
        // Caso de prueba 1
        assertTrue( "El costo acumulado no es correcto", 0 == escenario.darCostoAcumulado( ) );

        // Caso de prueba 2
        setupEscenario2( );
        assertTrue( "El costo acumulado no es correcto", 2800000 == escenario.darCostoAcumulado( ) );

        // Caso de prueba 3
        setupEscenario3( );
        assertTrue( "El costo acumulado no es correcto", 2840000 == escenario.darCostoAcumulado( ) );
    }

    /**
     * <b>Prueba 4:</b> Verifica el método agregarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el nombre dado.<br>
     * 2. Existe una banda con el nombre dado y se puede agregar.<br>
     * 3. No hay presupuesto suficiente para agregar la banda.<br>
     * 4. El escenario ya tiene todas las bandas permitidas.
     */
    @Test
    public void testAgregarBanda( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        boolean resultado = escenario.agregarBanda( "Aventureros", 10, 10, 50000, "rutaImagen" );
        assertFalse( "No debió agregar la banda pues ya existe en este escenario", resultado );

        // Caso de prueba 2
        resultado = escenario.agregarBanda( "Otra banda", 10, 10, 100000, "rutaImagen" );
        assertTrue( "Debió agregar la banda exitosamente", resultado );
        assertNotNull( "El escenario deberìa tener la banda", escenario.buscarPorNombre( "Otra banda" ) );

        // Caso de prueba 3
        resultado = escenario.agregarBanda( "Otra banda 2", 10, 10, 300000, "rutaImagen" );
        assertFalse( "No debería haber presupuesto suficiente para agregar la banda", resultado );

        // Caso de prueba 4
        escenario.agregarBanda( "Otra banda 3", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 4", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 5", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 6", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 7", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 8", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 9", 10, 10, 100, "rutaImagen" );
        escenario.agregarBanda( "Otra banda 10", 10, 10, 100, "rutaImagen" );
        resultado = escenario.agregarBanda( "Otra banda 11", 10, 10, 300, "rutaImagen" );
        assertFalse( "No debería haber presupuesto suficiente para agregar la banda", resultado );

    }

    /**
     * <b>Prueba 5:</b> Verifica el método eliminarBanda.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarBanda<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda a eliminar no existe en el escenario.<br>
     * 2. La banda a eliminar existe en el escenario.
     */
    @Test
    public void testEliminarBanda( )
    {
        setupEscenario2( );
        // Caso de prueba 1
        boolean resultado = escenario.eliminarBanda( "On Planets" );
        assertFalse( "No debió eliminar la banda pues no existe en este escenario", resultado );

        // Caso de prueba 2
        resultado = escenario.eliminarBanda( "Caifanes" );
        assertTrue( "Debió eliminar la banda", resultado );
        assertNull( "La bandan no debería existir en el escenario", escenario.buscarPorNombre( "On Planets" ) );

    }

    /**
     * <b>Prueba 6:</b> Verifica el método buscarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda buscada existe en el escenario.<br>
     * 2. La banda buscada no existe en el escenario.
     */
    @Test
    public void testBuscarPorNombre( )
    {
        // TODO Parte 4 Punto C: Completar el método según la documentación dada.
   setupEscenario2();
   assertNotNull("No se encontro",escenario.buscarPorNombre("Aventureros"));
   assertNull("No existe",escenario.buscarPorNombre("Prueba"));
    }

    /**
     * <b>Prueba 7:</b> Verifica el método buscarPorNumeroDeCanciones.<br>
     * <b>Métodos a probar:</b><br>
     * buscarPorNumeroDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existe una banda con la cantidad de canciones buscada.<br>
     * 2. No existe una banca con la cantidad de canciones buscada.
     */
    @Test
    public void testBuscarPorNumeroDeCanciones( )
    {
        // TODO Parte 4 Punto D: Completar el método según la documentación dada.
    	setupEscenario2();
    	System.out.println();
    	assertNotNull("No se encontro",escenario.buscarPorCantidadDeCanciones(4));
    	 assertNull("No existe",escenario.buscarPorCantidadDeCanciones(12));
    	  
    }

    /**
     * <b>Prueba 8:</b> Verifica el método ordenarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario. <br>
     */
    @Test
    public void testOrdenarPorNombre( )
    {
        // TODO Parte 3 Punto D: Completar el método según la documentación dada.
    	setupEscenario2();
    	setupEscenario3();
    	escenario.ordenarPorNombre();
    	Banda banda1= (Banda) escenario.darBandas().get(0);
    	Banda banda2= (Banda)escenario.darBandas().get(1);
    	Banda banda3= (Banda)escenario.darBandas().get(2);
    	Banda banda4= (Banda)escenario.darBandas().get(3);
    	Banda banda5= (Banda)escenario.darBandas().get(4);
    	System.out.println(banda1.darNombre());
    	assertTrue("No esta bien arreglado", banda1.darNombre().equals("Aventureros"));

    	assertTrue("No esta bien arreglado", banda2.darNombre().equals("Borbotones"));

    	assertTrue("No esta bien arreglado", banda3.darNombre().equals("Caifanes"));

    	assertTrue("No esta bien arreglado", banda4.darNombre().equals("On Planets"));
    	
    	assertTrue("No esta bien arreglado", banda5.darNombre().equals("Other"));
    }

    /**
     * <b>Prueba 9:</b> Verifica el método ordenarPorNumeroDeFans.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNumeroDeFans<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario.
     */
    @Test
    public void testOrdenarPorNumeroDeFans( )
    {
        // TODO Parte 3 Punto E: Completar el método según la documentación dada.
    	setupEscenario2();
    	setupEscenario3();
    	escenario.ordenarPorCantidadDeFans();
    	Banda banda1= (Banda) escenario.darBandas().get(0);
    	Banda banda2= (Banda)escenario.darBandas().get(1);
    	Banda banda3= (Banda)escenario.darBandas().get(2);
    	Banda banda4= (Banda)escenario.darBandas().get(3);
    	Banda banda5= (Banda)escenario.darBandas().get(4);
    	System.out.println(banda2.darNombre());
    	assertTrue("No esta bien arreglado", banda1.darNombre().equals("Aventureros"));

    	assertTrue("No esta bien arreglado", banda2.darNombre().equals("On Planets"));

    	assertTrue("No esta bien arreglado", banda3.darNombre().equals("Borbotones"));

    	assertTrue("No esta bien arreglado", banda4.darNombre().equals("Other"));

    	assertTrue("No esta bien arreglado", banda5.darNombre().equals("Caifanes"));
   
    }

    /**
     * <b>Prueba 10:</b> Verifica el método ordenarPorNumeroDeCanciones.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNumeroDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen 5 bandas en el escenario.
     */
    @Test
    public void testOrdenarPorNumeroDeCanciones( )
    {
        // TODO Parte 3 Punto F: Completar el método según la documentación dada.
    	setupEscenario2();
    	setupEscenario3();
    	escenario.ordenarPorCantidadDeCanciones();
    	Banda banda1= (Banda) escenario.darBandas().get(0);
    	Banda banda2= (Banda)escenario.darBandas().get(1);
    	Banda banda3= (Banda)escenario.darBandas().get(2);
    	Banda banda4= (Banda)escenario.darBandas().get(3);
    	Banda banda5= (Banda)escenario.darBandas().get(4);
    	System.out.println( banda1.darNombre());
    	assertTrue("No esta bien arreglado", banda1.darNombre().equals("Borbotones"));

    	assertTrue("No esta bien arreglado", banda2.darNombre().equals("Aventureros"));
    	
    	assertTrue("No esta bien arreglado", banda3.darNombre().equals("On Planets"));

    	assertTrue("No esta bien arreglado", banda4.darNombre().equals("Other"));
   
    	
    	assertTrue("No esta bien arreglado", banda5.darNombre().equals("Caifanes"));

    	
    }

}
