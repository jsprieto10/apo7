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
import java.awt.ComponentOrientation;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

/**
 * Panel para navegar entre los escenarios del festival.
 */
@SuppressWarnings("serial")
public class PanelEscenarios extends JPanel implements ActionListener
{
    // -----------------------------------------------------------------
    // Constantes
    // -----------------------------------------------------------------

    /**
     * Comando para ir al escenario 1.
     */
    public final static String UNO = "1";

    /**
     * Comando para ir al escenario 2.
     */
    public final static String DOS = "2";

    /**
     * Comando para ir al escenario 3.
     */
    public final static String TRES = "3";

    /**
     * Comando para ir al escenario 4.
     */
    public final static String CUATRO = "4";

    /**
     * Comando para ir al escenario 5.
     */
    public final static String CINCO = "5";

    /**
     * Comando para agregar un escenario.
     */
    public final static String AGREGAR = "Agregar\n Escenario";

    // -----------------------------------------------------------------
    // Atributos
    // -----------------------------------------------------------------

    /**
     * Instancia principal de la aplicación.
     */
    private InterfazFestival interfaz;

    // -----------------------------------------------------------------
    // Atributos de la interfaz
    // -----------------------------------------------------------------

    /**
     * Botón para ir al escenario 1.
     */
    private JButton btnEscenario1;

    /**
     * Botón para ir al escenario 2.
     */
    private JButton btnEscenario2;

    /**
     * Botón para ir al escenario 3.
     */
    private JButton btnEscenario3;

    /**
     * Botón para ir al escenario 4.
     */
    private JButton btnEscenario4;

    /**
     * Botón para ir al escenario 5.
     */
    private JButton btnEscenario5;

    /**
     * Botón para agregar un escenario.
     */
    private JButton btnAgregar;

    /**
     * Panel para organizar los botones de los escenarios.
     */
    private JPanel panelEscenarios;

    // -----------------------------------------------------------------
    // Constructores
    // -----------------------------------------------------------------

    /**
     * Crea el panel con los botones para la navegación.
     * @param pPrincipal Instancia principal de la aplicación.
     */
    public PanelEscenarios( InterfazFestival pPrincipal )
    {
        interfaz = pPrincipal;
        setLayout( new BorderLayout( ) );

        panelEscenarios = new JPanel( );
        panelEscenarios.setLayout( new GridLayout( 1, 5 ) );
        panelEscenarios.setComponentOrientation( ComponentOrientation.LEFT_TO_RIGHT );
        add( panelEscenarios, BorderLayout.CENTER );

        btnEscenario1 = new JButton( );
        btnEscenario1.setBorder( null );
        btnEscenario1.setEnabled( false );
        btnEscenario1.setActionCommand( UNO );
        btnEscenario1.addActionListener( this );
        panelEscenarios.add( btnEscenario1 );

        btnEscenario2 = new JButton( );
        btnEscenario2.setBorder( null );
        btnEscenario2.setEnabled( false );
        btnEscenario2.setActionCommand( DOS );
        btnEscenario2.addActionListener( this );
        panelEscenarios.add( btnEscenario2 );

        btnEscenario3 = new JButton( );
        btnEscenario3.setBorder( null );
        btnEscenario3.setEnabled( false );
        btnEscenario3.setActionCommand( TRES );
        btnEscenario3.addActionListener( this );
        panelEscenarios.add( btnEscenario3 );

        btnEscenario4 = new JButton( );
        btnEscenario4.setBorder( null );
        btnEscenario4.setEnabled( false );
        btnEscenario4.setActionCommand( CUATRO );
        btnEscenario4.addActionListener( this );
        panelEscenarios.add( btnEscenario4 );

        btnEscenario5 = new JButton( );
        btnEscenario5.setBorder( null );
        btnEscenario5.setEnabled( false );
        btnEscenario5.setActionCommand( CINCO );
        btnEscenario5.addActionListener( this );
        panelEscenarios.add( btnEscenario5 );

        btnAgregar = new JButton( AGREGAR );
        btnAgregar.setActionCommand( AGREGAR );
        btnAgregar.addActionListener( this );
        add( btnAgregar, BorderLayout.EAST );

    }

    // -----------------------------------------------------------------
    // Métodos
    // -----------------------------------------------------------------

    /**
     * Actualiza el estado de un botón según el escenario.
     * @param pNumero Número del escenario a actualizar.
     * @param pAgregar Determina si el botón debe ser eliminado o agregado.
     */
    public void actualizar( int pNumero, boolean pAgregar )
    {
        if( pNumero == 1 )
        {
            btnEscenario1.setEnabled( pAgregar );
            if( pAgregar )
            {
                btnEscenario1.setIcon( new ImageIcon( new ImageIcon( "./data/imagenes/mini1.png" ).getImage( ).getScaledInstance( 130, 60, Image.SCALE_DEFAULT ) ) );
            }
            else
            {
                btnEscenario1.setIcon( null );
                panelEscenarios.remove( btnEscenario1 );
                panelEscenarios.add( btnEscenario1 );
            }
        }
        else if( pNumero == 2 )
        {
            btnEscenario2.setEnabled( pAgregar );
            if( pAgregar )
            {
                btnEscenario2.setIcon( new ImageIcon( new ImageIcon( "./data/imagenes/mini2.png" ).getImage( ).getScaledInstance( 130, 60, Image.SCALE_DEFAULT ) ) );
            }
            else
            {
                btnEscenario2.setIcon( null );
                panelEscenarios.remove( btnEscenario2 );
                panelEscenarios.add( btnEscenario2 );
            }
        }
        else if( pNumero == 3 )
        {
            btnEscenario3.setEnabled( pAgregar );
            if( pAgregar )
            {
                btnEscenario3.setIcon( new ImageIcon( new ImageIcon( "./data/imagenes/mini3.png" ).getImage( ).getScaledInstance( 130, 60, Image.SCALE_DEFAULT ) ) );
            }
            else
            {
                btnEscenario3.setIcon( null );
                panelEscenarios.remove( btnEscenario3 );
                panelEscenarios.add( btnEscenario3 );
            }
        }
        else if( pNumero == 4 )
        {
            btnEscenario4.setEnabled( pAgregar );
            if( pAgregar )
            {
                btnEscenario4.setIcon( new ImageIcon( new ImageIcon( "./data/imagenes/mini4.png" ).getImage( ).getScaledInstance( 130, 60, Image.SCALE_DEFAULT ) ) );
            }
            else
            {
                btnEscenario4.setIcon( null );
                panelEscenarios.remove( btnEscenario4 );
                panelEscenarios.add( btnEscenario4 );
            }
        }
        else if( pNumero == 5 )
        {
            btnEscenario5.setEnabled( pAgregar );
            if( pAgregar )
            {
                btnEscenario5.setIcon( new ImageIcon( new ImageIcon( "./data/imagenes/mini5.png" ).getImage( ).getScaledInstance( 130, 60, Image.SCALE_DEFAULT ) ) );
            }
            else
            {
                btnEscenario5.setIcon( null );
                panelEscenarios.remove( btnEscenario5 );
                panelEscenarios.add( btnEscenario5 );
            }
        }

        revalidate( );
        repaint( );
    }

    /**
     * Manejo de los eventos de los botones.
     * @param pEvento Acción que generó el evento.
     */
    public void actionPerformed( ActionEvent pEvento )
    {
        String comando = pEvento.getActionCommand( );
        int escenario = 0;
        if( comando.equals( UNO ) )
        {
            escenario = 1;
        }
        else if( comando.equals( DOS ) )
        {
            escenario = 2;
        }
        else if( comando.equals( TRES ) )
        {
            escenario = 3;
        }
        else if( comando.equals( CUATRO ) )
        {
            escenario = 4;
        }
        else if( comando.equals( CINCO ) )
        {
            escenario = 5;
        }
        else if( comando.equals( AGREGAR ) )
        {
            escenario = 0;
        }

        interfaz.irAEscenario( escenario );
    }

}
