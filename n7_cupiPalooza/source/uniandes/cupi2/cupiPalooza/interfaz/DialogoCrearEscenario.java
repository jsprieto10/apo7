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
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

/**
 * Diálogo para asignar un patrocinador a un escenario.
 */
@SuppressWarnings("serial")
public class DialogoCrearEscenario extends JDialog implements ActionListener
{

    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para agregar el patrocinador.
     */
    public final static String AGREGAR = "Agregar Patrocinador";

    /**
     * Comando para cancelar el proceso.
     */
    public final static String CANCELAR = "Cancelar";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicación.
     */
    private InterfazFestival interfaz;

    /**
     * Número del escenario.
     */
    private int numero;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Etiqueta con el mensaje para el usuario.
     */
    private JLabel lblAviso;

    /**
     * Etiqueta patrocinador.
     */
    private JLabel lblPatrocinador;

    /**
     * Campo de texto para el patrocinador.
     */
    private JTextField txtPatrocinador;

    /**
     * Etiqueta presupuesto.
     */
    private JLabel lblPresupuesto;

    /**
     * Campo de texto para el presupuesto.
     */
    private JTextField txtPresupuesto;

    /**
     * Botón para agregar el patrocinador.
     */
    private JButton btnAgregar;

    /**
     * Botón para cancelar el proceso.
     */
    private JButton btnCancelar;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea la ventana de diálogo para asignar un patrocinador a un escenario.
     * @param pPrincipal Instancia principal de la aplicación.
     * @param pNumero Número del escenario.
     */
    public DialogoCrearEscenario( InterfazFestival pPrincipal, int pNumero )
    {
        interfaz = pPrincipal;
        numero = pNumero;
        setLayout( new BorderLayout( ) );
        setSize( 450, 180 );

        setTitle( "Crear Escenario" );
        setLocationRelativeTo( null );

        lblAviso = new JLabel( "Para crear un escenario se necesita un patrocinador." );
        lblAviso.setHorizontalAlignment( SwingConstants.CENTER );
        lblAviso.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        add( lblAviso, BorderLayout.NORTH );

        JPanel campos = new JPanel( );
        campos.setLayout( new GridLayout( 2, 2 ) );
        campos.setBorder( new EmptyBorder( 10, 10, 0, 10 ) );
        add( campos, BorderLayout.CENTER );

        lblPatrocinador = new JLabel( "Nombre: " );
        campos.add( lblPatrocinador );
        txtPatrocinador = new JTextField( );
        campos.add( txtPatrocinador );
        lblPresupuesto = new JLabel( "Presupuesto: " );
        campos.add( lblPresupuesto );
        txtPresupuesto = new JTextField( );
        campos.add( txtPresupuesto );

        JPanel botones = new JPanel( );
        botones.setLayout( new GridLayout( 1, 2 ) );
        botones.setBorder( new EmptyBorder( 10, 10, 10, 10 ) );
        add( botones, BorderLayout.SOUTH );

        btnAgregar = new JButton( "Agregar" );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        botones.add( btnAgregar );

        btnCancelar = new JButton( "Cancelar" );
        btnCancelar.addActionListener( this );
        btnCancelar.setActionCommand( CANCELAR );
        botones.add( btnCancelar );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Manejo de eventos del usuario.
     * @param pEvento Evento de usuario. pEvento != null.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );

        if( comando.equals( AGREGAR ) )
        {
            interfaz.crearEscenario( txtPatrocinador.getText( ), txtPresupuesto.getText( ), numero, this );
        }
        else if( comando.equals( CANCELAR ) )
        {
            this.dispose( );
        }

    }

}
