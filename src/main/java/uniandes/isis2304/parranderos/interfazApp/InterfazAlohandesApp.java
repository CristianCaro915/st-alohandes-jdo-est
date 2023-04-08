package uniandes.isis2304.parranderos.interfazApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;

import javax.jdo.JDODataStoreException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.UIManager;

import org.apache.log4j.Logger;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.stream.JsonReader;

import uniandes.isis2304.parranderos.negocio.Alohandes;
import uniandes.isis2304.parranderos.negocio.VOCliente;



@SuppressWarnings("serial")
public class InterfazAlohandesApp extends JFrame implements ActionListener {
    private static Logger log = Logger.getLogger(InterfazAlohandesApp.class.getName());
    //estructura sin panel
    private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
    //tablas
    private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

    private JsonObject tableConfig;
    private Alohandes alohandes;
    private JsonObject guiConfig;
    private PanelDatos panelDatos;
    private JMenuBar menuBar;

    public InterfazAlohandesApp()
    {
        // Carga la configuración de la interfaz desde un archivo JSON
        guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ);
        
        // Configura la apariencia del frame que contiene la interfaz gráfica
        configurarFrame ( );
        if (guiConfig != null) 	   
        {
     	   crearMenu( guiConfig.getAsJsonArray("menuBar") );
        }
        
        tableConfig = openConfig ("Tablas BD", CONFIG_TABLAS);
        alohandes = new Alohandes (tableConfig);
        
    	String path = guiConfig.get("bannerPath").getAsString();
        panelDatos = new PanelDatos ( );

        setLayout (new BorderLayout());
        add (new JLabel (new ImageIcon (path)), BorderLayout.NORTH );          
        add( panelDatos, BorderLayout.CENTER );        
    }
    private JsonObject openConfig (String tipo, String archConfig)
    {
    	JsonObject config = null;
		try 
		{
			Gson gson = new Gson( );
			FileReader file = new FileReader (archConfig);
			JsonReader reader = new JsonReader ( file );
			config = gson.fromJson(reader, JsonObject.class);
			log.info ("Se encontró un archivo de configuración válido: " + tipo);
		} 
		catch (Exception e)
		{
//			e.printStackTrace ();
			log.info ("NO se encontró un archivo de configuración válido");			
			JOptionPane.showMessageDialog(null, "No se encontró un archivo de configuración de interfaz válido: " + tipo, "Parranderos App", JOptionPane.ERROR_MESSAGE);
		}	
        return config;
    }
    private void configurarFrame(  )
    {
    	int alto = 0;
    	int ancho = 0;
    	String titulo = "";	
    	
    	if ( guiConfig == null )
    	{
    		log.info ( "Se aplica configuración por defecto" );			
			titulo = "Parranderos APP Default";
			alto = 300;
			ancho = 500;
    	}
    	else
    	{
			log.info ( "Se aplica configuración indicada en el archivo de configuración" );
    		titulo = guiConfig.get("title").getAsString();
			alto= guiConfig.get("frameH").getAsInt();
			ancho = guiConfig.get("frameW").getAsInt();
    	}
    	
        setDefaultCloseOperation( JFrame.EXIT_ON_CLOSE );
        setLocation (50,50);
        setResizable( true );
        setBackground( Color.WHITE );

        setTitle( titulo );
		setSize ( ancho, alto);        
    }
    private void crearMenu(  JsonArray jsonMenu )
    {    	
    	// Creación de la barra de menús
        menuBar = new JMenuBar();       
        for (JsonElement men : jsonMenu)
        {
        	// Creación de cada uno de los menús
        	JsonObject jom = men.getAsJsonObject(); 

        	String menuTitle = jom.get("menuTitle").getAsString();        	
        	JsonArray opciones = jom.getAsJsonArray("options");
        	
        	JMenu menu = new JMenu( menuTitle);
        	
        	for (JsonElement op : opciones)
        	{       	
        		// Creación de cada una de las opciones del menú
        		JsonObject jo = op.getAsJsonObject(); 
        		String lb =   jo.get("label").getAsString();
        		String event = jo.get("event").getAsString();
        		
        		JMenuItem mItem = new JMenuItem( lb );
        		mItem.addActionListener( this );
        		mItem.setActionCommand(event);
        		
        		menu.add(mItem);
        	}       
        	menuBar.add( menu );
        }        
        setJMenuBar ( menuBar );	
    }
                                                    /* CRUD */
	public void adicionarCliente(String nombre,String correo, String contrasenia){
		try 
    	{
    		String nombreCliente = JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String correoCliente = JOptionPane.showInputDialog (this, "Correo del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String contraseniaCliente = JOptionPane.showInputDialog (this, "Contrasenia del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
    		if (nombreCliente != null && correoCliente!= null && contraseniaCliente!=null)
    		{
        		VOCliente tb = alohandes.adicionarCliente(nombre, correo,contrasenia);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un Cliente con nombre: " + nombreCliente);
        		}
        		String resultado = "En adicionarCliente\n\n";
        		resultado += "Cliente exitosamente: " + tb;
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
	public void darClientePorCorreoContrasenia(String nombre, String contrasenia){
		try 
    	{
    		String correoCliente = JOptionPane.showInputDialog (this, "Correo del cliente?", "Buscar cliente por correo y contrasenia", JOptionPane.QUESTION_MESSAGE);
			String contraseniaCliente = JOptionPane.showInputDialog (this, "Contrasenia del Cliente?", "Buscar cliente por correo y contrasenia", JOptionPane.QUESTION_MESSAGE);
    		if (correoCliente != null && contraseniaCliente!= null)
    		{
    			VOCliente cliente = alohandes.darClientePorCorreoContrasenia(correoCliente, contrasenia);
    			String resultado = "En buscar un cliente por correo y contrasenia\n\n";
    			if (cliente != null)
    			{
        			resultado += "El cliente es es: " + cliente;
    			}
    			else
    			{
        			resultado += "Un cliente con correo: " + correoCliente + " NO EXISTE\n";    				
    			}
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
    		}
    		else
    		{
    			panelDatos.actualizarInterfaz("Operación cancelada por el usuario");
    		}
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
													/* METODOS ADMIN */
	public void limpiarBD ()
	{
		try 
		{
    		// Ejecución de la demo y recolección de los resultados
			long eliminados [] = alohandes.limpiarAlohandes();
			
			// Generación de la cadena de caracteres con la traza de la ejecución de la demo
			String resultado = "\n\n***** Limpiando la base de datos ***** \n";
			resultado += eliminados [0] + " TablaHotel eliminados\n";
			resultado += eliminados [1] + " TablaHostal eliminados\n";
			resultado += eliminados [2] + " TablaPropietarioInmueble eliminados\n";
			resultado += eliminados [3] + " TablaEmpresa eliminadas\n";
			resultado += eliminados [4] + " TablaCliente eliminados\n";
			resultado += eliminados [5] + " TablaOferta eliminados\n";
			resultado += eliminados [6] + " TablaContrato eliminados\n";
            resultado += eliminados [7] + " TablaInmueble eliminados\n";
            resultado += eliminados [8] + " TablaSeguro eliminados\n";
            resultado += eliminados [9] + " TablaHabitacion eliminados\n";
            resultado += eliminados [10] + " TablaServicio eliminados\n";
            resultado += eliminados [11] + " TablaBrindan eliminados\n";

			resultado += "\nLimpieza terminada";
   
			panelDatos.actualizarInterfaz(resultado);
		} 
		catch (Exception e) 
		{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
	}
													/* METODOS DE INTERACCIÓN */
	@Override
	public void actionPerformed(ActionEvent pEvento)
	{
		String evento = pEvento.getActionCommand( );		
        try 
        {
			Method req = InterfazAlohandesApp.class.getMethod ( evento );			
			req.invoke ( this );
		} 
        catch (Exception e) 
        {
			e.printStackTrace();
		} 
	}
	private String generarMensajeError(Exception e) 
	{
		String resultado = "************ Error en la ejecución\n";
		resultado += e.getLocalizedMessage() + ", " + darDetalleException(e);
		resultado += "\n\nRevise datanucleus.log y parranderos.log para más detalles";
		return resultado;
	}
	private String darDetalleException(Exception e) 
	{
		String resp = "";
		if (e.getClass().getName().equals("javax.jdo.JDODataStoreException"))
		{
			JDODataStoreException je = (javax.jdo.JDODataStoreException) e;
			return je.getNestedExceptions() [0].getMessage();
		}
		return resp;
	}
											/*PROGRAMA PRINCIPAL */
	public static void main( String[] args )
    {
        try
        {
        	
            // Unifica la interfaz para Mac y para Windows.
            UIManager.setLookAndFeel( UIManager.getCrossPlatformLookAndFeelClassName( ) );
            InterfazAlohandesApp interfaz = new InterfazAlohandesApp( );
            interfaz.setVisible( true );
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
