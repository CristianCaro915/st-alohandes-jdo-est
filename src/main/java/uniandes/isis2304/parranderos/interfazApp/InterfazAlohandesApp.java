package uniandes.isis2304.parranderos.interfazApp;
import java.awt.BorderLayout;
import java.awt.Color;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Timestamp;

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
import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.VOBrindan;
import uniandes.isis2304.parranderos.negocio.VOCliente;
import uniandes.isis2304.parranderos.negocio.VOHabitacion;
import uniandes.isis2304.parranderos.negocio.VOHostal;
import uniandes.isis2304.parranderos.negocio.VOHotel;
import uniandes.isis2304.parranderos.negocio.VOServicio;



@SuppressWarnings("serial")
public class InterfazAlohandesApp extends JFrame implements ActionListener {
    private static Logger log = Logger.getLogger(InterfazAlohandesApp.class.getName());
    //estructura sin panel
    private static final String CONFIG_INTERFAZ = "./src/main/resources/config/interfaceConfigApp.json"; 
    private static final String CONFIG_INTERFAZ_CLIENTE ="./src/main/resources/config/interfaceClientApp.json";
	private static final String CONFIG_INTERFAZ_ADMIN ="./src/main/resources/config/interfaceAdminApp.json";

	//tablas
    private static final String CONFIG_TABLAS = "./src/main/resources/config/TablasBD_A.json"; 

    private JsonObject tableConfig;
    private Alohandes alohandes;
    private JsonObject guiConfig;
    private PanelDatos panelDatos;
    private JMenuBar menuBar;

	public InterfazAlohandesApp( String opcion)
    {
		// Carga la configuración de la interfaz desde un archivo JSON
		if (opcion.equals("U")){
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_CLIENTE);
			String correo = JOptionPane.showInputDialog("Ingrese su correo "); 
			String contrasenia = JOptionPane.showInputDialog("Ingrese su contraseña "); 
			String nombre=darClientePorCorreoContrasenia(correo,contrasenia);
			if (nombre.equals("")){
				JOptionPane.showMessageDialog(null, "Para continuar debe registrarse");
				adicionarCliente();
				JOptionPane.showMessageDialog(null, "Ha sido registrado correctamente");
			}
		}
		else{
			guiConfig = openConfig ("Interfaz", CONFIG_INTERFAZ_ADMIN);
		}

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
	/* CLIENTE */
	public void adicionarCliente(){
		try 
    	{
    		String nombreCliente = JOptionPane.showInputDialog (this, "Nombre del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String correoCliente = JOptionPane.showInputDialog (this, "Correo del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
			String contraseniaCliente = JOptionPane.showInputDialog (this, "Contrasenia del Cliente?", "Adicionar Cliente", JOptionPane.QUESTION_MESSAGE);
    		if (nombreCliente != null && correoCliente!= null && contraseniaCliente!=null)
    		{
        		VOCliente tb = alohandes.adicionarCliente(nombreCliente, correoCliente,contraseniaCliente);
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
	public void darClientePorCorreoContrasenia(){
		try 
    	{
    		String correoCliente = JOptionPane.showInputDialog (this, "Correo del cliente?", "Buscar cliente por correo y contrasenia", JOptionPane.QUESTION_MESSAGE);
			String contraseniaCliente = JOptionPane.showInputDialog (this, "Contrasenia del Cliente?", "Buscar cliente por correo y contrasenia", JOptionPane.QUESTION_MESSAGE);
    		if (correoCliente != null && contraseniaCliente!= null)
    		{
    			VOCliente cliente = alohandes.darClientePorCorreoContrasenia(correoCliente, contraseniaCliente);
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
	public void eliminarClientePorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del Cliente?", "Borrar Cliente por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idCl = Long.valueOf (idTipoStr);
    			long tbEliminados = alohandes.eliminarClientePorId(idCl);

    			String resultado = "En eliminar Cliente\n\n";
    			resultado += tbEliminados + " Cliente eliminado\n";
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
	public String darClientePorCorreoContrasenia(String correo, String contrasenia){
		String rta="";
		try 
    	{
    		if (correo != null && contrasenia!= null)
    		{
    			VOCliente cliente = alohandes.darClientePorCorreoContrasenia(correo, contrasenia);
    			String resultado = "En buscar un cliente por correo y contrasenia\n\n";
    			if (cliente != null)
    			{
        			resultado += "El cliente es es: " + cliente;
    			}
    			else
    			{
        			resultado += "Un cliente con correo: " + correo + " NO EXISTE\n";    				
    			}
    			resultado += "\n Operación terminada";
    			panelDatos.actualizarInterfaz(resultado);
				rta=cliente.getNombre();
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
		return rta;
	}
	/* HOTEL */
	public void adicionarHotel(){
		try 
    	{
    		String nombreHotel = JOptionPane.showInputDialog (this, "Nombre del Hotel?", "Adicionar Hotel", JOptionPane.QUESTION_MESSAGE);
			String tipoHotel = JOptionPane.showInputDialog (this, "Tipo del Hotel?", "Adicionar Hotel", JOptionPane.QUESTION_MESSAGE);
    		if (nombreHotel != null && tipoHotel!= null )
    		{
        		VOHotel tb = alohandes.adicionarHotel(nombreHotel, tipoHotel);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear un Hotel con nombre: " + nombreHotel);
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
	public void darHotelPorNombre(){
		try 
    	{
    		String nombre = JOptionPane.showInputDialog (this, "nombre del hotel?", "Buscar hotel por nombre", JOptionPane.QUESTION_MESSAGE);
    		if (nombre != null)
    		{
    			VOHotel hotel = alohandes.darHotelPorNombre(nombre);
    			String resultado = "En buscar un hotel por nombre\n\n";
    			if (hotel != null)
    			{
        			resultado += "El hotel es es: " + hotel;
    			}
    			else
    			{
        			resultado += "Un hotel con nombre: " + hotel + " NO EXISTE\n";    				
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
	public void eliminarHotelPorId( )
    {
    	try 
    	{
    		String id_Ht = JOptionPane.showInputDialog (this, "Id del Hotel", "Borrar Hotel por Id", JOptionPane.QUESTION_MESSAGE);
			if (id_Ht != null)
    		{
    			long id_Hotel = Long.valueOf (id_Ht);
    			long tbEliminados = alohandes.eliminarHotelPorId(id_Hotel);
    			String resultado = "En eliminar Hotel\n\n";
    			resultado += tbEliminados + " Hoteles eliminados\n";
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
	/* HOSTAL */
	public void adicionarHostal( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del hostal", "Adicionar hostal", JOptionPane.QUESTION_MESSAGE);
			String tipo = JOptionPane.showInputDialog (this, "Tipo de hostal?", "Adicionar hostal", JOptionPane.QUESTION_MESSAGE);
			String recep= JOptionPane.showInputDialog (this, "Incluye recepción (1|0)", "Adicionar hostal", JOptionPane.QUESTION_MESSAGE);
			int recepcion = Integer.parseInt(recep);
			String horaClose=JOptionPane.showInputDialog (this, "Hora de cierre", "Adicionar hostal", JOptionPane.QUESTION_MESSAGE);
			Timestamp horaCierre = Timestamp.valueOf(horaClose);
			String horaOpen=JOptionPane.showInputDialog (this, "Hora de apertura", "Adicionar hostal", JOptionPane.QUESTION_MESSAGE);
			Timestamp horaApertura=Timestamp.valueOf(horaOpen);
			
			if ((recepcion==0 || recepcion==1) && tipo != null && nombre != null && horaCierre != null
			&& horaCierre != null && horaApertura != null) 
			{
				VOHostal tb = alohandes.adicionarHostal(nombre, recepcion, horaApertura, horaCierre, tipo);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un hostal con nombre: " + nombre);
				}
				String resultado = "En nombreHostal\n\n";
				resultado += "Hostal adicionado exitosamente: " + tb;
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
	public void eliminarHostalPorId( )
    {
    	try 
    	{
    		String id_Hs = JOptionPane.showInputDialog (this, "Id del Hostal", "Borrar Hostal por Id", JOptionPane.QUESTION_MESSAGE);
			if (id_Hs != null)
    		{
    			long id_Hostal = Long.valueOf (id_Hs);
    			long tbEliminados = alohandes.eliminarHostalPorId(id_Hostal);
    			String resultado = "En eliminar Hostal\n\n";
    			resultado += tbEliminados + " Hostales eliminados\n";
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
	/* PROPIETARIO INMUEBLE */
	public void eliminarPropietarioIPorId( )
    {
    	try 
    	{
    		String id_Pi = JOptionPane.showInputDialog (this, "Id del PropietarioI", "Borrar PropietarioI por Id", JOptionPane.QUESTION_MESSAGE);
			if (id_Pi != null)
    		{
    			long id_PropietarioI = Long.valueOf (id_Pi);
    			long tbEliminados = alohandes.eliminarPropietarioInmueblePorId(id_PropietarioI);
    			String resultado = "En eliminar PropietarioI\n\n";
    			resultado += tbEliminados + " PropietariosI eliminados\n";
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
	/* EMPRESA */
	public void eliminarEmpresaPorId( )
    {
    	try 
    	{
    		String id_E = JOptionPane.showInputDialog (this, "Id de Empresa", "Borrar Empresa por Id", JOptionPane.QUESTION_MESSAGE);
			if (id_E != null)
    		{
    			long id_Empresa = Long.valueOf (id_E);
    			long tbEliminados = alohandes.eliminarEmpresaPorId(id_Empresa);
    			String resultado = "En eliminar Empresa\n\n";
    			resultado += tbEliminados + " Empresas eliminados\n";
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
	/* OFERTA */
	/* CONTRATO */
	/* INMUEBLE */
	/* SEGURO */
	public void eliminarSeguroPorId( )
    {
    	try 
    	{
    		String id_S = JOptionPane.showInputDialog (this, "Id del Seguro", "Borrar Seguro por Id", JOptionPane.QUESTION_MESSAGE);
			if (id_S != null)
    		{
    			long id_Seguro = Long.valueOf (id_S);
    			long tbEliminados = alohandes.eliminarSeguroPorId(id_Seguro);
    			String resultado = "En eliminar Seguro\n\n";
    			resultado += tbEliminados + " Seguros eliminados\n";
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
	/* HABITACION */
    public void adicionarHabitacion( )
    {
    	try 
    	{
			String tama = JOptionPane.showInputDialog (this, "Tamaño de la habitacion", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
            int tamanio = Integer.parseInt(tama);
			String tipoH = JOptionPane.showInputDialog (this, "Tipo de habitación?", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
            String precioF= JOptionPane.showInputDialog (this, "Precio final", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
			int precioFinal=Integer.parseInt(precioF);
			String ubicacion = JOptionPane.showInputDialog (this, "ubicacion", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
			String OfertaId=JOptionPane.showInputDialog (this, "id Oferta asociada", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
            long id_Oferta= Long.parseLong(OfertaId);
			String ContratoId=JOptionPane.showInputDialog (this, "id Contrato asociado", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
            long id_Contrato= Long.parseLong(ContratoId);
			String InmuebleId=JOptionPane.showInputDialog (this, "id Inmueble asociado", "Adicionar habitacion", JOptionPane.QUESTION_MESSAGE);
            long id_Inmueble= Long.parseLong(InmuebleId);
		
            if (tamanio > 0 && tipoH != null 
            && precioFinal > 0 && ubicacion != null && id_Oferta!=0L && id_Oferta>0
			&& id_Contrato!=0L && id_Contrato>0 && id_Inmueble!=0L && id_Inmueble>0) 
    		{
        		VOHabitacion tb = alohandes.adicionarHabitacion(tamanio, tipoH, precioFinal, ubicacion,id_Oferta,id_Contrato,id_Inmueble);
        		if (tb == null)
        		{
        			throw new Exception ("No se pudo crear una habitacion frl tipo: " + tipoH);
        		}
        		String resultado = "En numHabitacion\n\n";
        		resultado += "Habitacion adicionada exitosamente: " + tb;
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
    public void listarHabitaciones( )
    {
    	try 
    	{
			List <Habitacion> lista = alohandes.darHabitaciones();
			String resultado = "En listarHabitacion";
			resultado +=  "\n" + listarHabitacion (lista);
			panelDatos.actualizarInterfaz(resultado);
			resultado += "\n Operación terminada";
		} 
    	catch (Exception e) 
    	{
//			e.printStackTrace();
			String resultado = generarMensajeError(e);
			panelDatos.actualizarInterfaz(resultado);
		}
    }
	private String listarHabitacion(List<Habitacion> lista) 
    {
    	String resp = "Las habitaciones existentes son:\n";
    	int i = 1;
        for (Habitacion tb : lista)
        {
        	resp += i++ + ". " + tb.toString() + "\n";
        }
        return resp;
	}
    public void eliminarHabitacionPorId( )
    {
    	try 
    	{
    		String numHabitacion = JOptionPane.showInputDialog (this, "Id del tipo de la habitacion", "Borrar habitacion por Id", JOptionPane.QUESTION_MESSAGE);
			if (numHabitacion != null)
    		{
    			long id_H = Long.valueOf (numHabitacion);
    			long tbEliminados = alohandes.eliminarHabitacionId(id_H);
    			String resultado = "En eliminar habitacion\n\n";
    			resultado += tbEliminados + " habitaciones eliminados\n";
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
	/* SERVICIO */
	public void adicionarServicio( )
	{
		try 
		{
			String nombre = JOptionPane.showInputDialog (this, "Nombre del Servicio", "Adicionar Servicio", JOptionPane.QUESTION_MESSAGE);
			String cant = JOptionPane.showInputDialog (this, "Cantidad del Servicio?", "Adicionar Servicio", JOptionPane.QUESTION_MESSAGE);
			int cantidad = Integer.parseInt(cant);
			String Precio= JOptionPane.showInputDialog (this, "Precio del Servicio", "Adicionar Servicio", JOptionPane.QUESTION_MESSAGE);
			int precio=Integer.parseInt(Precio);
			String Incluido = JOptionPane.showInputDialog (this, "Está incluido (1|0)", "Adicionar Servicio", JOptionPane.QUESTION_MESSAGE);
			int incluido = Integer.parseInt(Incluido);
			
			if (cantidad >= 0 && precio > 0 && nombre != null && (incluido==0 || incluido==1)) 
			{
				VOServicio tb = alohandes.adicionarservicio(precio, incluido, cantidad, nombre);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear un servicio con nombre: " + nombre);
				}
				String resultado = "En IdServicio\n\n";
				resultado += "Servicio adicionado exitosamente: " + tb;
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
	public void eliminarServicioPorId( )
    {
    	try 
    	{
    		String idTipoStr = JOptionPane.showInputDialog (this, "Id del tipo del Servicio", "Borrar Servicio por Id", JOptionPane.QUESTION_MESSAGE);
    		if (idTipoStr != null)
    		{
    			long idTipo = Long.valueOf (idTipoStr);
    			long tbEliminados = alohandes.eliminarServicPorID(idTipo);

    			String resultado = "En eliminar Servicio\n\n";
    			resultado += tbEliminados + " Servicios eliminados\n";
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
	/* BRINDAN */
	public void adicionarBrindan( )
	{
		try 
		{
			String idHabitacion = JOptionPane.showInputDialog (this, "id de habitacion asociada", "Adicionar brindan", JOptionPane.QUESTION_MESSAGE);
			String idServicio = JOptionPane.showInputDialog (this, "id del servicio asociado?", "Adicionar brindan", JOptionPane.QUESTION_MESSAGE);
			long id_Habitacion = Long.parseLong(idHabitacion);
			long id_Servicio = Long.parseLong(idServicio);
			if (id_Habitacion>=0 && id_Servicio>=0)
			{
				VOBrindan tb = alohandes.adicionarBrindan(id_Habitacion, id_Servicio);
				if (tb == null)
				{
					throw new Exception ("No se pudo crear brindan con id habitacion: " + idHabitacion +" y id servicio: "+ idServicio);
				}
				String resultado = "En brindan\n\n";
				resultado += "brindan adicionado exitosamente: " + tb;
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
	public void eliminarBrindanPorIds( )
	{
    	try 
    	{
    		String idHabitacion = JOptionPane.showInputDialog (this, "Id habitacion asociada", "Borrar Brindan por Ids", JOptionPane.QUESTION_MESSAGE);
			String idServicio = JOptionPane.showInputDialog (this, "Id servicio asociado", "Borrar Brindan por Ids", JOptionPane.QUESTION_MESSAGE);
    		if (idHabitacion != null && idServicio != null)
    		{
    			long id_Habitacion = Long.valueOf (idHabitacion);
				long id_Servicio = Long.valueOf(idServicio);
    			long tbEliminados = alohandes.eliminarBrindanPorIds(id_Habitacion, id_Servicio);

    			String resultado = "En eliminar Brindan\n\n";
    			resultado += tbEliminados + " Brindan eliminados\n";
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
			String opcion = JOptionPane.showInputDialog("Ingrese 'U' pasa usuario y 'A' para admin ");
			if (opcion.equals("U") || opcion.equals("A")){
				InterfazAlohandesApp interfaz = new InterfazAlohandesApp(opcion);
            	interfaz.setVisible( true );
			}
			else{
				JOptionPane.showMessageDialog(null,"Error ingresando opcion, intente de nuevo");
			}
        }
        catch( Exception e )
        {
            e.printStackTrace( );
        }
    }
}
