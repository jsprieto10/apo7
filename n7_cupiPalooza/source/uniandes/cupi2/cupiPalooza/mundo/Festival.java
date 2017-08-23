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

package uniandes.cupi2.cupiPalooza.mundo;

import java.util.ArrayList;

/**
 * Festival de música. <br>
 * TODO Parte 1 Punto F: Documente la invariante de la clase.
 */

public class Festival
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Cantidad máxima de escenarios en el festival.
     */
    public final static int CANTIDAD_MAXIMA_ESCENARIOS = 5;

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Escenarios que componen el festival.
     */
    private ArrayList escenarios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Construye el festival con su estado inicial. <br>
     * <b>post: </b> Se inicializó la lista de escenarios con 3 escenarios y sus bandas.
     */
    public Festival( )
    {
        escenarios = new ArrayList( );

        crearEscenario( "Movistar", 20000000, 1 );

        agregarBandaAEscenario( 1, "Seis Peatones", 56720, 5, 750000, "./data/imagenes/bandas/rock.png" );
        agregarBandaAEscenario( 1, "Zoey", 4820, 8, 850000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 1, "Randy Travis", 12760, 3, 1200000, "./data/imagenes/bandas/country.png" );
        agregarBandaAEscenario( 1, "Batalla Épica", 34877, 4, 70000, "./data/imagenes/bandas/house.png" );

        crearEscenario( "Adidas", 50000000, 2 );
        agregarBandaAEscenario( 2, "Plásticos Adolescentes", 475312, 12, 480000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 2, "Monos Sin Disfraz", 7945138, 6, 1050000, "./data/imagenes/bandas/rock.png" );
        agregarBandaAEscenario( 2, "Korean Kitchen", 687124, 7, 220000, "./data/imagenes/bandas/house.png" );
        agregarBandaAEscenario( 2, "Cultura Profética", 77621, 3, 876000, "./data/imagenes/bandas/reggae.png" );
        agregarBandaAEscenario( 2, "John Waters", 64523, 8, 890000, "./data/imagenes/bandas/country.png" );

        crearEscenario( "Google", 15000000, 3 );
        agregarBandaAEscenario( 3, "Pixie Wings", 56720, 10, 564000, "./data/imagenes/bandas/pop.png" );
        agregarBandaAEscenario( 3, "Fiesta Flow", 587412, 5, 1300000, "./data/imagenes/bandas/reggae.png" );
        agregarBandaAEscenario( 3, "Los Melancólicos", 22648, 8, 2400000, "./data/imagenes/bandas/rock.png" );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Retorna el escenario con el número recibido por parámetro.
     * @param pNumero Número del escenario buscado. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
     * @return El escenario con el número especificado.
     */
    public Escenario darEscenario( int pNumero )
    {
        boolean termino = false;
        Escenario buscado = null;
        for( int i = 0; i < escenarios.size( ) && !termino; i++ )
        {
            Escenario actual = ( Escenario )escenarios.get( i );
            if( actual.darNumero( ) == pNumero )
            {
                buscado = actual;
                termino = true;
            }
        }
        return buscado;
    }

    /**
     * Retorna el siguiente número disponible para un escenario.
     * @return Número válido para asignar a un nuevo escenario. En caso de no haber más números disponibles retorna 0.
     */
    public int darNumeroDisponible( )
    {
        int numero = 0;
        boolean termino = false;
        for( int i = 1; i <= CANTIDAD_MAXIMA_ESCENARIOS && !termino; i++ )
        {
            int provisional = i;
            for( int j = 0; j < escenarios.size( ); j++ )
            {
                Escenario actual = ( Escenario )escenarios.get( j );
                if( actual.darNumero( ) == provisional )
                {
                    provisional++;
                }
            }

            if( provisional == i )
            {
                termino = true;
                numero = provisional;
            }
        }
        return numero;
    }

    /**
     * Retorna la lista de escenarios en el festival.
     * @return Lista de los escenarios.
     */
    public ArrayList darEscenarios( )
    {
        return escenarios;
    }

    /**
     * Crea un escenario con la información recibida por parámetro. <br>
     * <b>post: </b> Se creó el escenario con el número especificado, el patrocinador y el presupuesto dados.
     * @param pPatrocinador Nombre del patrocinador. pPatrocinador != null && pPatrocinador != "".
     * @param pPresupuesto Presupuesto para el escenario. pPresupuesto > 0.
     * @param pNumero Número del escenario. pNumero > 0 && pNumero <= 5.
     * @return True si fue posible asignar el patrocinador, false si otro escenario con el mismo patrocinador o si ya alcanzó el número máximo de escenarios.
     */
    public boolean crearEscenario( String pPatrocinador, double pPresupuesto, int pNumero )
    {
        boolean repetido = false;
        boolean resultado = false;
        if( escenarios.size( ) < CANTIDAD_MAXIMA_ESCENARIOS )
        {
            for( int i = 0; i < escenarios.size( ) && !repetido; i++ )
            {
                Escenario actual = ( Escenario )escenarios.get( i );
                if( actual.darPatrocinador( ).equals( pPatrocinador ) )
                {
                    repetido = true;
                }
            }
            if( !repetido )
            {
                Escenario nuevo = new Escenario( pNumero, pPatrocinador, pPresupuesto );
                escenarios.add( nuevo );
                resultado = true;
            }
        }
        return resultado;
    }

    /**
     * Elimina un escenario. <br>
     * <b>post: </b> El escenario fue eliminado del festival.
     * @param pNumero Número del escenario a eliminar. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
     * @return true si fue posible eliminar el escenario, false en caso de que el escenario no exista.
     */
    public boolean eliminarEscenario( int pNumero )
    {
        boolean termino = false;
        for( int i = 0; i < escenarios.size( ) && !termino; i++ )
        {
            Escenario actual = ( Escenario )escenarios.get( i );
            if( actual.darNumero( ) == pNumero )
            {
                escenarios.remove( actual );
                termino = true;
            }
        }
        return termino;
    }

    /**
     * Agrega una banda a un escenario. <br>
     * <b>post: </b> Al escenario especificado se agregó una nueva banda en su repertorio.
     * @param pEscenario Número del escenario al cual se agregará la banda. pNumero > 0 && pNumero <= 5.
     * @param pNombre Nombre de la banda. pNombre != null && pNombre != "".
     * @param pCantidadDeFans Cantidad de fans que tiene la banda. pCantidadDeFans > 0.
     * @param pCantidadDeCanciones Cantidad de canciones que tocará la banda. pCantidadDeCanciones > 0.
     * @param pCosto Costo de la banda por presentarse en un escenario. pCosto > 0.
     * @param pRutaImagen Ruta de la imagen descriptiva de la banda. pRutaImagen != null && pRutaImagen != "".
     * @return true si fue posible agregar la banda, false si ya existía una banda con ese nombre, si el escenario ya tenía todas las bandas permitidas o si se excede el
     *         presupuesto del escenario.
     */
    public boolean agregarBandaAEscenario( int pEscenario, String pNombre, int pCantidadDeFans, int pCantidadDeCanciones, double pCosto, String pRutaImagen )
    {
        Escenario escenario = darEscenario( pEscenario );
        boolean resultado = escenario.agregarBanda( pNombre, pCantidadDeFans, pCantidadDeCanciones, pCosto, pRutaImagen );
        return resultado;
    }

    /**
     * Elimina la banda con el nombre dado del escenario especificado. <br>
     * <b>post: </b> Se eliminó la banda especificada del escenario.
     * @param pEscenario Número del escenario al cual se agregará la banda. pNumero > 0 && pNumero <= CANTIDAD_MAXIMA_ESCENARIOS.
     * @param pNombre Nombre de la banda. pNombre != null && pNombre != "".
     * @return True si fue posible eliminar la banda, false si la banda no existía.
     */
    public boolean eliminarBandaEscenario( int pEscenario, String pNombre )
    {
        Escenario escenario = darEscenario( pEscenario );
        boolean resultado = escenario.eliminarBanda( pNombre );
        return resultado;
    }

    /**
     * Ordena ascendentemente las bandas de todos los escenarios por nombre. <br>
     * <b> post: </b> Las bandas en los escenarios fueron ordenadas alfabéticamente por su nombre.
     */
    public void ordenarPorNombre( )
    {
        for( int i = 0; i < escenarios.size( ); i++ )
        {
            Escenario actual = ( Escenario )escenarios.get( i );
            if( actual != null )
            {
                actual.ordenarPorNombre( );
            }
        }
    }

    /**
     * Ordena descendentemente las bandas de todos los escenarios por su cantidad de fans. <br>
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios según su cantidad de fans.
     */
    public void ordenarPorCantidadDeFans( )
    {
        for( int i = 0; i < escenarios.size( ); i++ )
        {
            Escenario actual = ( Escenario )escenarios.get( i );
            if( actual != null )
            {
                actual.ordenarPorCantidadDeFans( );
            }
        }
    }

    /**
     * Ordena ascendentemente las bandas en los escenarios por su cantidad de canciones. <br>
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios según su cantidad de canciones.
     */
    public void ordenarPorCantidadDeCanciones( )
    {
        for( int i = 0; i < escenarios.size( ); i++ )
        {
            Escenario actual = ( Escenario )escenarios.get( i );
            if( actual != null )
            {
                actual.ordenarPorCantidadDeCanciones( );
            }
        }
    }

    // -----------------------------------------------------------------
    // Invariante
    // -----------------------------------------------------------------

    // TODO Parte 1 Punto G: Documente e implemente la invariante de la clase.
    // Si lo necesita, puede crear método privados adicionales.

    // -----------------------------------------------------------------
    // Puntos de Extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1.
     * @return respuesta1.
     */
    public String metodo1( )
    {
        return "Respuesta 1";
    }

    /**
     * Extensión 2.
     * @return respuesta2.
     */
    public String metodo2( )
    {
        return "Respuesta 2";
    }
}
