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
import uniandes.cupi2.cupiPalooza.mundo.Festival;

/**
 * Clase usada para verificar que los métodos de la clase Festival estén correctamente implementados.
 */
public class FestivalTest
{
    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de música con sus escenarios y bandas
     */
    private Festival festival;

    // -----------------------------------------------------------------
    // Escenarios
    // -----------------------------------------------------------------
    /**
     * Crea una instancia de la clase festival. Este método se ejecuta antes de cada método de prueba.
     */
    @Before
    public void setupEscenario1( )
    {
        festival = new Festival( );
    }

    /**
     * Crea un festival, asignando un patrocinador al cuarto escenario y tres bandas al mismo.
     */
    public void setupEscenario2( )
    {
        festival.crearEscenario( "Huawei", 20000000, 4 );
        festival.agregarBandaAEscenario( 4, "Aventureros", 125000, 4, 750000, "./data/imagenes/bandas/pop.png" );
        festival.agregarBandaAEscenario( 4, "Borbotones", 100000, 3, 850000, "./data/imagenes/bandas/rock.png" );
        festival.agregarBandaAEscenario( 4, "Caifanes", 50000, 8, 1200000, "./data/imagenes/bandas/country.png" );
    }

    /**
     * Crea un festival, asignando un patrocinador al quinto escenario y tres bandas al mismo.
     */
    public void setupEscenario3( )
    {
        festival.crearEscenario( "Sony", 600000, 5 );
        festival.agregarBandaAEscenario( 5, "On Planets", 125000, 4, 200000, "./data/imagenes/bandas/pop.png" );
        festival.agregarBandaAEscenario( 5, "Other", 100000, 3, 200000, "./data/imagenes/bandas/rock.png" );
        festival.agregarBandaAEscenario( 5, "Airia", 50000, 8, 100000, "./data/imagenes/bandas/country.png" );
    }

    // -----------------------------------------------------------------
    // Métodos de prueba
    // -----------------------------------------------------------------

    /**
     * <b>Prueba 1:</b> Verifica el método Festival.<br>
     * <b>Métodos a probar:</b><br>
     * Festival<br>
     * darEscenario<br>
     * darEscenarios<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se crea el festival.
     */
    @Test
    public void testFestival( )
    {
        setupEscenario1( );
        Escenario escenario1 = festival.darEscenario( 1 );
        Escenario escenario2 = festival.darEscenario( 2 );
        Escenario escenario3 = festival.darEscenario( 3 );
        Escenario escenario4 = festival.darEscenario( 4 );
        Escenario escenario5 = festival.darEscenario( 5 );

        assertNotNull( "No se inicializó el escenario 1 correctamente.", escenario1 );
        assertNotNull( "No se inicializó el escenario 2 correctamente.", escenario2 );
        assertNotNull( "No se inicializó el escenario 3 correctamente.", escenario3 );
        assertNull( "No se inicializó el escenario 4 correctamente.", escenario4 );
        assertNull( "No se inicializó el escenario 5 correctamente.", escenario5 );

        assertEquals( "El número de bandas en el escenario no es correcto", 3, festival.darEscenarios( ).size( ), 0 );

        assertEquals( "El número del escenario 1 no es correcto", 1, escenario1.darNumero( ), 0 );
        assertEquals( "El número del escenario 2 no es correcto", 2, escenario2.darNumero( ), 0 );
        assertEquals( "El número del escenario 3 no es correcto", 3, escenario3.darNumero( ), 0 );

        assertTrue( "El patrocinador del escenario 1 no es correcto", escenario1.darPatrocinador( ).equals( "Movistar" ) );
        assertTrue( "El patrocinador del escenario 2 no es correcto", escenario2.darPatrocinador( ).equals( "Adidas" ) );
        assertTrue( "El patrocinador del escenario 3 no es correcto", escenario3.darPatrocinador( ).equals( "Google" ) );

        assertEquals( "El número de bandas del escenario 1 no es correcto", 4, escenario1.darBandas( ).size( ), 0 );
        assertEquals( "El número de bandas del escenario 2 no es correcto", 5, escenario2.darBandas( ).size( ), 0 );
        assertEquals( "El número de bandas del escenario 1 no es correcto", 3, escenario3.darBandas( ).size( ), 0 );

    }
    
    /**
     * <b>Prueba 2:</b> Verifica el método darNumeroDisponible.<br>
     * <b>Métodos a probar:</b><br>
     * darNumeroDisponible<br>
     * <b>Casos de prueba:</b><br>
     * 1. Existen los escenarios 1, 2 y 3.<br>
     * 2. Existen todos los escenarios.<br>
     * 3. Existen los escenarios 1, 3 y 5.<br>
     */
    @Test
    public void testDarNumeroDisponible( )
    {
        //Caso de prueba 1
        assertEquals( "El siguiente número disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );
        
        //Caso de prueba 2
        setupEscenario2( );
        setupEscenario3( );
        assertEquals( "El siguiente número disponible es incorrecto, no hay más escenarios", 0, festival.darNumeroDisponible( ), 0 );
    
        //Caso de prueba 3
        festival.eliminarEscenario( 4 );
        assertEquals( "El siguiente número disponible es incorrecto.", 4, festival.darNumeroDisponible( ), 0 );
        festival.eliminarEscenario( 2 );
        assertEquals( "El siguiente número disponible es incorrecto.", 2, festival.darNumeroDisponible( ), 0 );

    }

    /**
     * <b>Prueba 3:</b> Verifica el método crearEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * crearEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe un escenario con el patrocinador dado.<br>
     * 2. No existe un escenario con el patrocinador dado.<br>
     * 3. Ya existen todos los escenarios permitidos.<br>
     */
    @Test
    public void testCrearEscenario( )
    {
        setupEscenario2( );

        // Caso de prueba 1
        boolean resultado = festival.crearEscenario( "Huawei", 50000, 5 );
        assertFalse( "No debió agregar el patrocinador pues está repetido", resultado );

        // Caso de prueba 2
        resultado = festival.crearEscenario( "Kinder Eggs", 50000, 5 );
        assertTrue( "Debió asignar el patrocinador", resultado );

        // Caso de prueba 3
        resultado = festival.crearEscenario( "Oracle", 50000, 7 );
        assertFalse( "Se alcanzó el número máximo de escenarios", resultado );

    }

    /**
     * <b>Prueba 4:</b> Verifica el método eliminarEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. El escenario a eliminar no existe.<br>
     * 3. El escenario a eliminar existe.<br>
     */
    @Test
    public void testEliminarEscenario( )
    {
        setupEscenario2();
        // Caso de prueba 1
        boolean resultado = festival.eliminarEscenario( 6 );
        assertFalse( "No debió eliminar el patrocinador pues el escenario no existe", resultado );

        // Caso de prueba 2
        resultado = festival.eliminarEscenario( 4 );
        assertTrue( "Debió eliminar el escenario", resultado );

    }

    /**
     * <b>Prueba 5:</b> Verifica el método agregarBandaAEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * agregarBandaAEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. Ya existe una banda con el mismo nombre en el escenario.<br>
     * 2. No existe una banda con ese nombre en el escenario.<br>
     * 3. El presupuesto no alcanza para agregar la banda.<br>
     * 4. Se alcanzó el límite de bandas en el escenario.
     */
    @Test
    public void testAgregarBandaAEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        Escenario escenario = festival.darEscenario( 5 );
        boolean resultado = festival.agregarBandaAEscenario( 5, "On Planets", 10, 10, 50000, "rutaImagen" );
        assertFalse( "No debió agregar la banda pues ya existe en este escenario", resultado );

        // Caso de prueba 2
        resultado = festival.agregarBandaAEscenario( 5, "Otra banda", 10, 10, 50000, "rutaImagen" );
        assertTrue( "Debió agregar la banda exitosamente", resultado );
        assertNotNull( "El escenario deberìa tener la banda", escenario.buscarPorNombre( "Otra banda" ) );

        // Caso de prueba 3
        resultado = festival.agregarBandaAEscenario( 5, "Otra banda 2", 10, 10, 60000, "rutaImagen" );
        assertFalse( "No debería haber presupuesto suficiente para agregar la banda", resultado );

        // Caso de prueba 4
        festival.agregarBandaAEscenario( 5, "Otra banda 3", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 4", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 5", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 6", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 7", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 8", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 9", 10, 10, 600, "rutaImagen" );
        festival.agregarBandaAEscenario( 5, "Otra banda 10", 10, 10, 600, "rutaImagen" );
        resultado = festival.agregarBandaAEscenario( 5, "Otra banda 11", 10, 10, 600, "rutaImagen" );
        assertFalse( "No debe haber mas espacio en el escenario para agregar la banda", resultado );

    }

    /**
     * <b>Prueba 6:</b> Verifica el método eliminarBandaEscenario.<br>
     * <b>Métodos a probar:</b><br>
     * eliminarBandaEscenario<br>
     * <b>Casos de prueba:</b><br>
     * 1. La banda a eliminar no existe en el escenario.<br>
     * 2. La banda a eliminar existe en el escenario.
     */
    @Test
    public void testEliminarBandaEscenario( )
    {
        setupEscenario3( );
        // Caso de prueba 1
        Escenario escenario = festival.darEscenario( 5 );
        boolean resultado = festival.eliminarBandaEscenario( 5, "On Planets 2" );
        assertFalse( "No debió eliminar la banda pues no existe en este escenario", resultado );

        // Caso de prueba 2
        resultado = festival.eliminarBandaEscenario( 5, "On Planets" );
        assertTrue( "Debió eliminar la banda", resultado );
        assertNull( "La bandan no debería existir en el escenario", escenario.buscarPorNombre( "On Planets" ) );

    }

    /**
     * <b>Prueba 7:</b> Verifica el método ordenarPorNombre.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNombre<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorNombre( )
    {
        // Caso de prueba 1
        festival.ordenarPorNombre( );
        Escenario escenario = festival.darEscenario( 1 );

        Banda banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        Banda banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        Banda banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        Banda banda3 = ( Banda )escenario.darBandas( ).get( 3 );

        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Batalla Épica" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Randy Travis" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Seis Peatones" ) );
        assertTrue( "La cuarta banda no está en la posición correcta", banda3.darNombre( ).equals( "Zoey" ) );

        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorNombre( );
        escenario = festival.darEscenario( 4 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Aventureros" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Borbotones" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Caifanes" ) );

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorNombre( );
        escenario = festival.darEscenario( 5 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Airia" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "On Planets" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Other" ) );
    }

    /**
     * <b>Prueba 8:</b> Verifica el método ordenarPorNumeroDeFans.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNumeroDeFans<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorNumeroDeFans( )
    {
        // Caso de prueba 1
        festival.ordenarPorCantidadDeFans( );
        Escenario escenario = festival.darEscenario( 1 );
        Banda banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        Banda banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        Banda banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        Banda banda3 = ( Banda )escenario.darBandas( ).get( 3 );
        assertTrue( "La cuarta banda no está en la posición correcta", banda0.darNombre( ).equals( "Seis Peatones" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda1.darNombre( ).equals( "Batalla Épica" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda2.darNombre( ).equals( "Randy Travis" ) );
        assertTrue( "La primera banda no está en la posición correcta", banda3.darNombre( ).equals( "Zoey" ) );
        
        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorCantidadDeFans( );
        escenario = festival.darEscenario( 4 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );

        assertTrue( "La primera banda no está en la posición correcta", banda2.darNombre( ).equals( "Caifanes" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Borbotones" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda0.darNombre( ).equals( "Aventureros" ) );

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorCantidadDeFans( );
        escenario = festival.darEscenario( 5 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );

        assertTrue( "La primera banda no está en la posición correcta", banda2.darNombre( ).equals( "Airia" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Other" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda0.darNombre( ).equals( "On Planets" ) );
    }

    /**
     * <b>Prueba 9:</b> Verifica el método ordenarPorNumeroDeCanciones.<br>
     * <b>Métodos a probar:</b><br>
     * ordenarPorNumeroDeCanciones<br>
     * <b>Casos de prueba:</b><br>
     * 1. Se ordenan las bandas del escenario de prueba 1.<br>
     * 2. Se ordenan las bandas del escenario de prueba 2.<br>
     * 3. Se ordenan las bandas del escenario de prueba 3.
     */
    @Test
    public void testOrdenarPorNumeroDeCanciones( )
    {
        // Caso de prueba 1
        festival.ordenarPorCantidadDeCanciones( );
        Escenario escenario = festival.darEscenario( 1 );
        Banda banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        Banda banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        Banda banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        Banda banda3 = ( Banda )escenario.darBandas( ).get( 3 );

        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Randy Travis" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Batalla Épica" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Seis Peatones" ) );
        assertTrue( "La cuarta banda no está en la posición correcta", banda3.darNombre( ).equals( "Zoey" ) );

        // Caso de prueba 2
        setupEscenario2( );
        festival.ordenarPorCantidadDeCanciones( );
        escenario = festival.darEscenario( 4 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );
        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Borbotones" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "Aventureros" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Caifanes" ) );

        // Caso de prueba 3
        setupEscenario3( );
        festival.ordenarPorCantidadDeCanciones( );
        escenario = festival.darEscenario( 5 );
        banda0 = ( Banda )escenario.darBandas( ).get( 0 );
        banda1 = ( Banda )escenario.darBandas( ).get( 1 );
        banda2 = ( Banda )escenario.darBandas( ).get( 2 );

        assertTrue( "La primera banda no está en la posición correcta", banda0.darNombre( ).equals( "Other" ) );
        assertTrue( "La segunda banda no está en la posición correcta", banda1.darNombre( ).equals( "On Planets" ) );
        assertTrue( "La tercera banda no está en la posición correcta", banda2.darNombre( ).equals( "Airia" ) );
    }
}
