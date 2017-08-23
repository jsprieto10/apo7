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

package uniandes.cupi2.cupiPalooza.interfaz;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
import javax.swing.border.TitledBorder;

/**
 * Panel con las opciones de la aplicaci�n.
 */
@SuppressWarnings("serial")
public class PanelOpciones extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ordenar las bandas por nombre.
     */
    public final static String ORDENAR_NOMBRE = "Por Nombre";

    /**
     * Comando para ordenar las bandas por su cantidad de fans.
     */
    public final static String ORDENAR_FANS = "Por Cantidad de Fans";

    /**
     * Comando para ordenar las bandas por su cantidad de canciones.
     */
    public final static String ORDENAR_CANCIONES = "Por Cantidad de Canciones";

    /**
     * Comando para buscar una banda por su nombre.
     */
    public final static String BUSCAR_POR_NOMBRE = "Buscar Banda Por Nombre";
    
    /**
     * Comando para buscar una banda por su cantidad de canciones.
     */
    public final static String BUSCAR_POR_CANCIONES = "Buscar Banda Por Cantidad de Canciones";
    
    /**
     * Comando para la opci�n 1.
     */
    public final static String OPC_1 = "Opci�n 1";

    /**
     * Comando para la opci�n 2.
     */
    public final static String OPC_2 = "Opci�n 2";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicaci�n.
     */
    private InterfazFestival interfaz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Bot�n para ordenar por nombre.
     */
    private JButton btnOrdenarNombre;

    /**
     * Bot�n para ordenar por cantidad de fans.
     */
    private JButton btnOrdenarFans;

    /**
     * Bot�n para ordenar por cantidad de canciones.
     */
    private JButton btnOrdenarCanciones;
    
    /**
     * Bot�n para buscar una banda por su nombre.
     */
    private JButton btnBuscarNombre;
    
    /**
     * Bot�n para buscar una banda por su cantidad de canciones.
     */
    private JButton btnBuscarCantidadCanciones;

    /**
     * Bot�n para llamar la opci�n 1.
     */
    private JButton btnOpc1;

    /**
     * Bot�n para llamar la opci�n 2.
     */
    private JButton btnOpc2;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con los botones y sus respectivos comandos.
     * @param pPrincipal instancia principal de la aplicaci�n.
     */
    public PanelOpciones( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout() );
        JPanel ordenar = new JPanel( );
        ordenar.setLayout( new GridLayout( 1, 3 ) );
        ordenar.setBorder(new TitledBorder("Ordenar Bandas"));
        add( ordenar, BorderLayout.CENTER );
        JPanel opciones = new JPanel( );
        opciones.setLayout( new GridLayout( 2, 2 ) );
        opciones.setBorder(new TitledBorder("Opciones"));
        add( opciones, BorderLayout.SOUTH );

        btnOrdenarNombre = new JButton( ORDENAR_NOMBRE );
        btnOrdenarNombre.addActionListener( this );
        btnOrdenarNombre.setActionCommand( ORDENAR_NOMBRE );
        ordenar.add( btnOrdenarNombre );

        btnOrdenarFans = new JButton( ORDENAR_FANS );
        btnOrdenarFans.addActionListener( this );
        btnOrdenarFans.setActionCommand( ORDENAR_FANS );
        ordenar.add( btnOrdenarFans );

        btnOrdenarCanciones = new JButton( ORDENAR_CANCIONES );
        btnOrdenarCanciones.addActionListener( this );
        btnOrdenarCanciones.setActionCommand( ORDENAR_CANCIONES );
        ordenar.add( btnOrdenarCanciones );
        
        btnBuscarNombre = new JButton( BUSCAR_POR_NOMBRE );
        btnBuscarNombre.addActionListener( this );
        btnBuscarNombre.setActionCommand( BUSCAR_POR_NOMBRE );
        opciones.add( btnBuscarNombre );
        
        btnBuscarCantidadCanciones = new JButton( BUSCAR_POR_CANCIONES );
        btnBuscarCantidadCanciones.addActionListener( this );
        btnBuscarCantidadCanciones.setActionCommand( BUSCAR_POR_CANCIONES );
        opciones.add( btnBuscarCantidadCanciones );

        btnOpc1 = new JButton( OPC_1 );
        btnOpc1.addActionListener( this );
        btnOpc1.setActionCommand( OPC_1 );
        opciones.add( btnOpc1 );

        btnOpc2 = new JButton( OPC_2 );
        btnOpc2.addActionListener( this );
        btnOpc2.setActionCommand( OPC_2 );
        opciones.add( btnOpc2 );
        
    }

    // -----------------------------------------------------------------
    // M�todos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( ORDENAR_NOMBRE ) )
        {
            interfaz.ordenarPorNombre( );
        }
        else if( comando.equals( ORDENAR_FANS ) )
        {
            interfaz.ordenarPorCantidadDeFans( );
        }
        else if( comando.equals( ORDENAR_CANCIONES ) )
        {
            interfaz.ordenarPorCantidadDeCanciones( );
        }
        else if( comando.equals( BUSCAR_POR_NOMBRE ) )
        {
            interfaz.buscarBandaPorNombre( );
        }
        else if( comando.equals( BUSCAR_POR_CANCIONES ) )
        {
            interfaz.buscarBandaPorCantidadDeCanciones( );
        }
        else if( comando.equals( OPC_1 ) )
        {
            interfaz.reqFuncOpcion1( );
        }
        else if( comando.equals( OPC_2 ) )
        {
            interfaz.reqFuncOpcion2( );
        }
    }
}
