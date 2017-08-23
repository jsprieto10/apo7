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

package uniandes.cupi2.cupiPalooza.interfaz;

import java.awt.BorderLayout;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import uniandes.cupi2.cupiPalooza.mundo.Banda;
import uniandes.cupi2.cupiPalooza.mundo.Escenario;
import uniandes.cupi2.cupiPalooza.mundo.Festival;
/**
 * Festival de música que permite administrar sus escenarios y las bandas que se presentan en ellos.
 */
@SuppressWarnings("serial")
public class InterfazFestival extends JFrame
{

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Festival de música.
     */
    private Festival festival;

    /**
     * Escenario actual que se está manipulando en la vista.
     */
    private Escenario actual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Panel con el banner de la aplicación.
     */
    private PanelImagen panelImagen;

    /**
     * Panel con los botones de navegación entre escenarios.
     */
    private PanelEscenarios panelEscenarios;

    /**
     * Panel que muestra la información de las bandas del escenario actual.
     */
    private PanelBandas panelBandas;

    /**
     * Panel con las opciones.
     */
    private PanelOpciones panelOpciones;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana principal con la información del festival.
     */
    public InterfazFestival( )
    {
        festival = new Festival( );
        setLayout( new BorderLayout( ) );
        setTitle( "CupiPalooza" );
        setResizable( false );

        panelImagen = new PanelImagen( );
        panelEscenarios = new PanelEscenarios( this );
        panelBandas = new PanelBandas( this );
        panelOpciones = new PanelOpciones( this );

        JPanel norte = new JPanel( );
        norte.setLayout( new BorderLayout( ) );
        norte.setBorder( null );

        norte.add( panelImagen, BorderLayout.NORTH );
        norte.add( panelEscenarios, BorderLayout.CENTER );
        add( norte, BorderLayout.NORTH );
        add( panelBandas, BorderLayout.CENTER );
        add( panelOpciones, BorderLayout.SOUTH );

        ArrayList losEscenarios = festival.darEscenarios( );
        for( int i = 0; i < losEscenarios.size( ); i++ )
        {
            Escenario escenario = (Escenario) losEscenarios.get( i );
            panelEscenarios.actualizar( escenario.darNumero( ), true );
        }

        setSize( 800, 730 );
        setLocationRelativeTo( null );
        setDefaultCloseOperation( EXIT_ON_CLOSE );

        actual = festival.darEscenario( 1 );
        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Cambia el escenario actual que se está viendo. <br>
     * <b> post: </b> Se va al escenario especificado por el usuario. Si el escenario no tiene patrocinador se informa al usuario y se da la posibilidad de agregarlo.
     * @param pEscenario Número del escenario. En caso de que el usuario busque crear un escenario este valor es 0.
     */
    public void irAEscenario( int pEscenario )
    {
        Escenario escenario = festival.darEscenario( pEscenario );
        if( escenario == null )
        {
            int numero = festival.darNumeroDisponible();
            DialogoCrearEscenario dialogo = new DialogoCrearEscenario( this, numero );
            dialogo.setVisible( true );
        }
        else
        {
            actual = escenario;
            panelBandas.habilitar( );
            panelBandas.actualizar( escenario.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
        }

    }

    /**
     * Crea un escenario. <br>
     * <b> post: </b> El escenario ahora posee patrocinador en caso de que la información introducida sea correcta.
     * @param pPatrocinador Nombre del patrocinador.
     * @param pPresupuesto Presupuesto disponible para el escenario.
     * @param pNumero Número del escenario.
     * @param pDialogo Ventana de diálogo de la que provienen los datos.
     */
    public void crearEscenario( String pPatrocinador, String pPresupuesto, int pNumero, DialogoCrearEscenario pDialogo )
    {
        double presupuesto = 0;
        if( pPatrocinador.equals( "" ) || pPresupuesto.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            try
            {
                presupuesto = Double.parseDouble( pPresupuesto );
                if( presupuesto <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca un presupuesto válido", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
                }
                else if( festival.darEscenarios( ).size( ) == Festival.CANTIDAD_MAXIMA_ESCENARIOS )
                {
                    JOptionPane.showMessageDialog( this, "Se ha alcanzado el número máximo de escenarios en el festival", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    boolean resultado = festival.crearEscenario( pPatrocinador, presupuesto, pNumero );
                    pDialogo.dispose( );
                    if( !resultado )
                    {
                        JOptionPane.showMessageDialog( this, "No puede haber dos escenarios con el mismo patrocinador", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );

                    }
                    else 
                    {
                        actual = festival.darEscenario( pNumero );
                        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
                        actualizarPanelEscenarios();
                    }
                }
            }
            catch( Exception e )
            {
                e.printStackTrace( );
                JOptionPane.showMessageDialog( this, "El presupuesto debe ser un valor numérico", "Agregar Escenario", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Crea una ventana de diálogo para agregar una banda a un escenario.
     */
    public void dialogoAgregarBanda( )
    {
        if(actual.darBandas( ).size( ) == Escenario.CANTIDAD_MAXIMA_BANDAS)
        {
            JOptionPane.showMessageDialog( this, "No hay más espacio en este escenario para otra banda", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
        }
        else
        {
            DialogoCrearBanda dialogo = new DialogoCrearBanda( this );
            dialogo.setVisible( true );
        }
    }

    /**
     * Agrega una banda a un escenario. <br>
     * <b> post: </b> Se agrega la banda al repertorio del escenario, en caso de ser válida toda la información.
     * @param pNombre Nombre de la banda.
     * @param pFans Cantidad de fans.
     * @param pCanciones Cantidad de canciones.
     * @param pCosto Costo de la banda.
     * @param pImagen Ruta de la imagen.
     * @param pDialogo Diálogo que contiene la información.
     */
    public void agregarBanda( String pNombre, String pFans, String pCanciones, String pCosto, String pImagen, DialogoCrearBanda pDialogo )
    {
        int fans = 0;
        int canciones = 0;
        double costo = 0;

        if( pNombre.equals( "" ) || pFans.equals( "" ) || pCanciones.equals( "" ) || pCosto.equals( "" ) || pImagen.equals( "" ) )
        {
            JOptionPane.showMessageDialog( this, "Debe llenar todos los campos", "Agregar Banda", JOptionPane.ERROR_MESSAGE );

        }
        else
        {
            try
            {
                fans = Integer.parseInt( pFans );
                canciones = Integer.parseInt( pCanciones );
                costo = Double.parseDouble( pCosto );
                if( fans <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca una cantidad de fans válido", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                }
                else if( canciones <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca una cantidad de canciones válido", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                }
                else if( costo <= 0 )
                {
                    JOptionPane.showMessageDialog( this, "Introduzca un costo válido", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                }
                else if( actual.buscarPorNombre( pNombre ) != null )
                {
                    JOptionPane.showMessageDialog( this, "Ya existe una banda con este nombre", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                }
                else
                {
                    boolean resultado = festival.agregarBandaAEscenario( actual.darNumero( ), pNombre, fans, canciones, costo, pImagen );
                    if(resultado)
                    {
                        actual = festival.darEscenario( actual.darNumero( ) );
                        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
                        pDialogo.dispose( );
                    }
                    else
                    {
                        double presupuestoDisponible = actual.darPresupuesto( ) - actual.darCostoAcumulado( );
                        NumberFormat formatter = NumberFormat.getInstance( );
                        String moneyString = formatter.format( presupuestoDisponible );
                        JOptionPane.showMessageDialog( this, "El costo de la banda sobrepasa el presupuesto disponible. \n Presupuesto disponible: $" + moneyString, "Agregar Banda", JOptionPane.ERROR_MESSAGE );
                    }
                }
            }
            catch( Exception e )
            {
                JOptionPane.showMessageDialog( this, "La cantidad de fans, la cantidad de canciones y el costo deben ser valores numéricos", "Agregar Banda", JOptionPane.ERROR_MESSAGE );
            }
        }

    }

    /**
     * Elimina una banda del escenario actual. <br>
     * <b> post: </b> Se ha eliminado la banda del escenario.
     * @param pBanda Banda a eliminar.
     */
    public void eliminarBanda( Banda pBanda )
    {
        if( pBanda != null )
        {
            festival.eliminarBandaEscenario( actual.darNumero( ), pBanda.darNombre( ) );
            panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
        }
        else
        {
            JOptionPane.showMessageDialog( this, "Elija una banda", "Eliminar Banda", JOptionPane.ERROR_MESSAGE );
        }
    }

    /**
     * Elimina el escenario actual. <br>
     * <b> post: </b> Se eliminó el escenario actual.
     */
    public void eliminarEscenario( )
    {
        int resultado = JOptionPane.showConfirmDialog( this, "¿Está seguro que desea eliminar este escenario?", "Eliminar Escenario", JOptionPane.INFORMATION_MESSAGE );
        if( resultado == JOptionPane.OK_OPTION )
        {
            festival.eliminarEscenario( actual.darNumero( ) );
            panelBandas.inhabilitar( );
            actualizarPanelEscenarios();
        }
    }

    /**
     * Ordena las bandas en los escenarios por nombre. <br>
     * <b> post: </b> Las bandas en los escenarios están ordenadas alfabéticamente por su nombre.
     */
    public void ordenarPorNombre( )
    {
        int seleccion = JOptionPane.showOptionDialog( null, "¿Desea ordenar en este escenario o en todo el festival?", "Ordenar Por Nombre", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{ "Escenario Actual", "Todo el Festival" }, "Escenario Actual" );

        if( seleccion == 0 )
        {
            festival.darEscenario( actual.darNumero( ) ).ordenarPorNombre( );
        }
        else if( seleccion == 1 )
        {
            festival.ordenarPorNombre( );
        }
        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
    }

    /**
     * Ordena las bandas en los escenarios por su cantidad de fans. <br>
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios de menor a mayor según su cantidad de fans.
     */
    public void ordenarPorCantidadDeFans( )
    {
        int seleccion = JOptionPane.showOptionDialog( null, "¿Desea ordenar en este escenario o en todo el festival?", "Ordenar Por Cantidad de Fans", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{ "Escenario Actual", "Todo el Festival" }, "Escenario Actual" );

        if( seleccion == 0 )
        {
            festival.darEscenario( actual.darNumero( ) ).ordenarPorCantidadDeFans( );
        }
        else if( seleccion == 1 )
        {
            festival.ordenarPorCantidadDeFans( );
        }
        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );

    }

    /**
     * Ordena las bandas en los escenarios por su cantidad de canciones. <br>
     * <b> post: </b> Las bandas fueron ordenadas en los escenarios de menor a mayor según su cantidad de canciones.
     */
    public void ordenarPorCantidadDeCanciones( )
    {
        int seleccion = JOptionPane.showOptionDialog( null, "¿Desea ordenar en este escenario o en todo el festival?", "Ordenar Por Cantidad de Canciones", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null,
                new Object[]{ "Escenario Actual", "Todo el Festival" }, "Escenario Actual" );

        if( seleccion == 0 )
        {
            festival.darEscenario( actual.darNumero( ) ).ordenarPorCantidadDeCanciones( );
        }
        else if( seleccion == 1 )
        {
            festival.ordenarPorCantidadDeCanciones( );
        }
        panelBandas.actualizar( actual.darBandas( ), actual.darNumero( ), actual.darPatrocinador( ) );
    }
    
    /**
     * Busca una banda en el escenario actual por su nombre.
     * <b> post: </b> Se estableció la banda buscada como la banda actual y se muestra en el escenario. Si no se encuentra se informa al usuario.
     */
    public void buscarBandaPorNombre( )
    {
        String nombre = JOptionPane.showInputDialog( this, "Introduzca el nombre de la banda", "Buscar banda por nombre" );
        Banda buscada = actual.buscarPorNombre( nombre );

        if(buscada != null)
        {
            panelBandas.cambiarSeleccionada(buscada);
        }
        else
        {
            JOptionPane.showMessageDialog( this, "No se encontró una banda con este nombre", "Buscar Banda Por Nombre", JOptionPane.ERROR_MESSAGE );
        }
    }
    
    /**
     * Busca una banda en el escenario actual por su cantidad de canciones.
     * <b> post: </b> Se estableció la banda buscada como la banda actual y se muestra en el escenario. Si no se encuentra se informa al usuario.
     */
    public void buscarBandaPorCantidadDeCanciones( )
    {
        String cantidad = JOptionPane.showInputDialog( this, "Introduzca la cantidad de canciones", "Buscar banda por cantidad de canciones" );
        try
        {
            int cantidadNumero = Integer.parseInt( cantidad );

            if(cantidadNumero <= 0)
            {
                JOptionPane.showMessageDialog( this, "Ingrese una cantidad de canciones válida", "Buscar Banda Por Cantidad de Canciones", JOptionPane.ERROR_MESSAGE );
            }
            else
            {
                Banda buscada = actual.buscarPorCantidadDeCanciones( cantidadNumero );
                if(buscada != null)
                {
                    panelBandas.cambiarSeleccionada(buscada);
                }
                else
                {
                    JOptionPane.showMessageDialog( this, "No se encontró una banda con esta cantidad de canciones", "Buscar Banda Por Cantidad de Canciones", JOptionPane.ERROR_MESSAGE );
                }
            }
        }
        catch(Exception e)
        {
            JOptionPane.showMessageDialog( this, "Ingrese una cantidad de canciones válida", "Buscar Banda Por Cantidad de Canciones", JOptionPane.ERROR_MESSAGE );
        }        
        
    }
    
    /**
     * Actualiza el panel de escenarios según los escenarios existentes en el festival.
     */
    public void actualizarPanelEscenarios()
    {
        int[] posiciones = new int[Festival.CANTIDAD_MAXIMA_ESCENARIOS];
        ArrayList escenarios = festival.darEscenarios( );
        for(int i = 0; i < escenarios.size( ); i++)
        {
            Escenario escenario = (Escenario) escenarios.get( i );
            posiciones[i] = escenario.darNumero( );  
            panelEscenarios.actualizar( escenario.darNumero( ), true );
        }
                
        for(int i = 0; i < posiciones.length; i++)
        {
            int comprobacion = i + 1;
            boolean existente = false;
            
            for(int j = 0; j < posiciones.length && !existente; j++)
            {
                if(comprobacion == posiciones[j])
                {
                    existente = true;
                }
            }
            
            if(!existente)
            {
                panelEscenarios.actualizar( comprobacion, false );
            }
            
        }
    }

    // -----------------------------------------------------------------
    // Puntos de extensión
    // -----------------------------------------------------------------

    /**
     * Extensión 1.
     */
    public void reqFuncOpcion1( )
    {

        String resultado = festival.metodo1( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 1", JOptionPane.INFORMATION_MESSAGE );
    }

    /**
     * Extensión 2.
     */
    public void reqFuncOpcion2( )
    {

        String resultado = festival.metodo2( );
        JOptionPane.showMessageDialog( this, resultado, "Respuesta 2", JOptionPane.INFORMATION_MESSAGE );
    }

    // -----------------------------------------------------------------
    // Main
    // -----------------------------------------------------------------

    /**
     * Este método ejecuta la aplicación, creando una nueva interfaz.
     * @param args Arreglo opcional de argumentos que se recibe por línea de comandos.
     */
    public static void main( String[] args )
    {
        InterfazFestival interfaz = new InterfazFestival( );
        interfaz.setVisible( true );

    }

}
