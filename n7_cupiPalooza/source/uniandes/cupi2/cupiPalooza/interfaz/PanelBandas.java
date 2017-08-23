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
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;
import java.util.ArrayList;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import uniandes.cupi2.cupiPalooza.mundo.Banda;

/**
 * Panel para ver la información de las bandas en el escenario actual.
 */
@SuppressWarnings("serial")
public class PanelBandas extends JPanel implements ActionListener, ListSelectionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para agregar una banda.
     */
    public final static String AGREGAR_BANDA = "Agregar Banda";

    /**
     * Comando para eliminar una banda.
     */
    public final static String ELIMINAR_BANDA = "Eliminar Banda";

    /**
     * Comando para eliminar el escenario.
     */
    public final static String ELIMINAR_ESCENARIO = "Eliminar Escenario";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicación.
     */
    private InterfazFestival interfaz;

    /**
     * Banda actual seleccionada.
     */
    private Banda bandaActual;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Objeto scroll para la lista de bandas del escenario.
     */
    private JScrollPane scroll;

    /**
     * Lista de las bandas en el escenario.
     */
    // TODO Parte 5 Punto A: Declarar el atributo listaBandas de tipo JList.

    /**
     * Imagen de la banda actual.
     */
    private JLabel imagenBanda;

    /**
     * Etiqueta con el nombre del escenario, mostrando el patrocinador.
     */
    private JLabel lblPatrocinador;

    /**
     * Etiqueta nombre.
     */
    private JLabel lblNombre;

    /**
     * Etiqueta cantidad de fans.
     */
    private JLabel lblFans;

    /**
     * Campo de texto para la cantidad de fans.
     */
    private JTextField txtFans;

    /**
     * Etiqueta cantidad de canciones.
     */
    private JLabel lblCanciones;

    /**
     * Campo de texto para la cantidad de canciones.
     */
    private JTextField txtCanciones;

    /**
     * Etiqueta costo.
     */
    private JLabel lblCosto;

    /**
     * Campo de texto para el costo.
     */
    private JTextField txtCosto;

    /**
     * Botón para agregar una banda.
     */
    private JButton btnAgregarBanda;

    /**
     * Botón para eliminar una banda.
     */
    private JButton btnEliminarBanda;

    /**
     * Botón para eliminar el escenario.
     */
    private JButton btnEliminarEscenario;

    /**
     * Imagen de fondo actual en el panel.
     */
    private ImageIcon fondo;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con la información de las bandas en un escenario.
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public PanelBandas( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        fondo = new ImageIcon( "./data/imagenes/esc1.png" );
        setLayout( new BorderLayout( ) );

        // TODO Parte 5 Punto B: Inicialice el atributo listaBandas.

        // TODO Parte 5 Punto C: Agregue el ListSelecionListener al atributo listaBandas.

        listaBandas.setSelectionMode( ListSelectionModel.SINGLE_SELECTION );
        scroll = new JScrollPane( listaBandas );
        TitledBorder titulo = new TitledBorder( "Bandas en el escenario" );
        setBorder( titulo );
        scroll.setPreferredSize( new Dimension( 200, 400 ) );
        scroll.setVerticalScrollBarPolicy( ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS );
        scroll.setHorizontalScrollBarPolicy( ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS );

        JPanel info = new JPanel( );
        info.setForeground( null );
        info.setOpaque( false );
        info.setLayout( new BorderLayout( ) );

        lblPatrocinador = new JLabel( "" );
        lblPatrocinador.setForeground( Color.WHITE );
        lblPatrocinador.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        lblPatrocinador.setFont( new Font( "Arial", Font.BOLD, 24 ) );
        lblPatrocinador.setHorizontalAlignment( SwingConstants.CENTER );
        info.add( lblPatrocinador, BorderLayout.NORTH );

        JPanel cuadros1 = new JPanel( );
        cuadros1.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        cuadros1.setLayout( new BorderLayout( ) );
        cuadros1.setOpaque( false );
        info.add( cuadros1, BorderLayout.CENTER );

        JPanel cuadros2 = new JPanel( );
        cuadros2.setBorder( new EmptyBorder( 5, 5, 5, 5 ) );
        cuadros2.setLayout( new GridLayout( 1, 6, 5, 5 ) );
        cuadros2.setOpaque( false );
        info.add( cuadros2, BorderLayout.SOUTH );

        lblNombre = new JLabel( "." );
        lblNombre.setForeground( Color.WHITE );
        lblNombre.setFont( new Font( "Arial", Font.BOLD, 18 ) );
        lblNombre.setHorizontalAlignment( SwingConstants.CENTER );
        cuadros1.add( lblNombre, BorderLayout.CENTER );

        lblFans = new JLabel( "Fans: " );
        lblFans.setForeground( Color.WHITE );
        lblFans.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblFans );
        txtFans = new JTextField( );
        txtFans.setEditable( false );
        cuadros2.add( txtFans );

        lblCanciones = new JLabel( "Canciones: " );
        lblCanciones.setForeground( Color.WHITE );
        lblCanciones.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblCanciones );
        txtCanciones = new JTextField( );
        txtCanciones.setEditable( false );
        cuadros2.add( txtCanciones );

        lblCosto = new JLabel( "Costo: " );
        lblCosto.setForeground( Color.WHITE );
        lblCosto.setHorizontalAlignment( SwingConstants.RIGHT );
        cuadros2.add( lblCosto );
        txtCosto = new JTextField( );
        txtCosto.setEditable( false );
        cuadros2.add( txtCosto );

        JPanel botones = new JPanel( );
        botones.setLayout( new GridLayout( 3, 1 ) );

        btnAgregarBanda = new JButton( "Agregar Banda" );
        btnAgregarBanda.setActionCommand( AGREGAR_BANDA );
        btnAgregarBanda.addActionListener( this );
        botones.add( btnAgregarBanda );

        btnEliminarBanda = new JButton( "Eliminar Banda" );
        btnEliminarBanda.setActionCommand( ELIMINAR_BANDA );
        btnEliminarBanda.addActionListener( this );
        botones.add( btnEliminarBanda );

        btnEliminarEscenario = new JButton( "Eliminar Escenario" );
        btnEliminarEscenario.setActionCommand( ELIMINAR_ESCENARIO );
        btnEliminarEscenario.addActionListener( this );
        botones.add( btnEliminarEscenario );

        JPanel oeste = new JPanel( );
        oeste.setLayout( new BorderLayout( ) );
        add( oeste, BorderLayout.WEST );
        oeste.add( scroll, BorderLayout.CENTER );
        oeste.add( botones, BorderLayout.SOUTH );

        JPanel centro = new JPanel( );
        centro.setLayout( new BorderLayout( ) );
        centro.setOpaque( false );
        add( centro, BorderLayout.CENTER );
        imagenBanda = new JLabel( );
        centro.add( imagenBanda, BorderLayout.CENTER );
        centro.add( info, BorderLayout.NORTH );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el panel con la información que entra por parámetro.
     * @param pBandas Bandas del escenario actual.
     * @param pNumero Número del escenario.
     * @param pPatrocinador Nombre del patrocinador del escenario.
     */
    public void actualizar( ArrayList pBandas, int pNumero, String pPatrocinador )
    {
        habilitar( );

        // TODO Parte 5 Punto D: Asigne la lista de bandas al atributo listaBandas.

        imagenBanda.setIcon( null );
        txtFans.setText( "" );
        txtCanciones.setText( "" );
        txtCosto.setText( "" );
        lblPatrocinador.setText( "Escenario " + pPatrocinador );
        Graphics graficos = this.getGraphics( );
        if( graficos != null )
        {
            fondo = new ImageIcon( "./data/imagenes/esc" + pNumero + ".png" );
            repaint( );
        }
        if( pBandas.size( ) > 0 )
        {
            listaBandas.setSelectedIndex( 0 );
        }
        else
        {
            lblNombre.setText( " " );
        }

    }

    /**
     * Actualiza el panel a su vista inhabilitada.
     */
    public void inhabilitar( )
    {
        listaBandas.setListData( new ArrayList( ).toArray( ) );
        listaBandas.setEnabled( false );
        imagenBanda.setIcon( null );
        btnAgregarBanda.setEnabled( false );
        btnEliminarBanda.setEnabled( false );
        btnEliminarEscenario.setEnabled( false );
        txtFans.setText( "" );
        txtCanciones.setText( "" );
        txtCosto.setText( "" );
        lblPatrocinador.setText( " " );
        lblNombre.setText( " " );
        Graphics graficos = this.getGraphics( );
        if( graficos != null )
        {
            fondo = new ImageIcon( "./data/imagenes/esc0.png" );
            repaint( );
        }
    }

    /**
     * Actualiza el panel a su vista habilitada.
     */
    public void habilitar( )
    {
        listaBandas.setEnabled( true );
        btnAgregarBanda.setEnabled( true );
        btnEliminarBanda.setEnabled( true );
        btnEliminarEscenario.setEnabled( true );
    }

    /**
     * Actualiza la banda seleccionada.
     * @param pBanda Banda a seleccionar.
     */
    public void cambiarSeleccionada( Banda pBanda )
    {
        listaBandas.setSelectedValue( pBanda, true );
    }

    /**
     * Pinta el panel y sus componentes.
     * @param pG Superficie del panel.
     */
    public void paintComponent( Graphics pG )
    {
        pG.drawImage( fondo.getImage( ), 0, 0, null );
        setOpaque( false );
        super.paintComponent( pG );
    }

    /**
     * Manejo de los eventos de la lista.
     * @param pEvento Acción que generó el evento.
     */
    public void valueChanged( ListSelectionEvent pEvento )
    {
        bandaActual = ( Banda )listaBandas.getSelectedValue( );
        if( bandaActual != null )
        {
            imagenBanda.setIcon( new ImageIcon( bandaActual.darRutaImagen( ) ) );
            lblNombre.setText( bandaActual.darNombre( ) );
            txtFans.setText( bandaActual.darCantidadDeFans( ) + "" );
            txtCanciones.setText( bandaActual.darCantidadDeCanciones( ) + "" );
            NumberFormat formatter = NumberFormat.getInstance( );
            String moneyString = formatter.format( bandaActual.darCosto( ) );
            txtCosto.setText( "$" + moneyString );

        }
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        if( comando.equals( AGREGAR_BANDA ) )
        {
            interfaz.dialogoAgregarBanda( );
        }
        else if( comando.equals( ELIMINAR_BANDA ) )
        {
            interfaz.eliminarBanda( bandaActual );
        }
        else if( comando.equals( ELIMINAR_ESCENARIO ) )
        {
            interfaz.eliminarEscenario( );
        }
    }

}
