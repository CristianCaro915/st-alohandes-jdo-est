package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;

import javax.jdo.JDODataStoreException;
import javax.jdo.JDOHelper;
import javax.jdo.PersistenceManager;
import javax.jdo.PersistenceManagerFactory;
import javax.jdo.Transaction;

import org.apache.log4j.Logger;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

import uniandes.isis2304.parranderos.negocio.Empresa;
import uniandes.isis2304.parranderos.negocio.PropietarioInmueble;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.Hostal;
import uniandes.isis2304.parranderos.negocio.Contrato;
import uniandes.isis2304.parranderos.negocio.Inmueble;
import uniandes.isis2304.parranderos.negocio.Oferta;
import uniandes.isis2304.parranderos.negocio.Seguro;
import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.Servicio;
import uniandes.isis2304.parranderos.negocio.Brindan;
import uniandes.isis2304.parranderos.negocio.Cliente;

public class PersistenciaAlohandes {

    private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
    public final static String SQL = "javax.jdo.query.SQL";
    private static PersistenciaAlohandes instance;
    private PersistenceManagerFactory pmf;
    private List <String> tablas;
    private SQLUtil sqlUtil;

    private SQLEmpresa sqlEmpresa;
    private SQLPropietarioInmueble sqlPropietarioInmueble;
    private SQLHotel sqlHotel;
    private SQLHostal sqlHostal;
    private SQLInmueble sqlInmueble;
    private SQLContrato sqlContrato;
    private SQLSeguro sqlSeguro;
    private SQLHabitacion sqlHabitacion;
    private SQLServicio sqlServicio;
    private SQLOferta sqlOferta;
    private SQLCliente sqlCliente;
    private SQLBrindan sqlBrindan;

    private PersistenciaAlohandes(){
        pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();

        tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");//0
		tablas.add ("HOTEL");//1
		tablas.add ("HOSTAL");//2
		tablas.add ("PROPIETARIOINMUEBLE");//3
		tablas.add ("EMPRESA");//4
		tablas.add ("CLIENTE");//5
		tablas.add ("OFERTA");//6
		tablas.add ("CONTRATO");//7
        tablas.add ("INMUEBLE");//8
        tablas.add ("SEGURO");//9
        tablas.add ("HABITACION");//10
        tablas.add ("SERVICIO");//11
        tablas.add ("BRINDAN");//12
    }

    private PersistenciaAlohandes (JsonObject tableConfig)
	{
		crearClasesSQL ();
		tablas = leerNombresTablas(tableConfig);
		
		String unidadPersistencia = tableConfig.get ("unidadPersistencia").getAsString ();
		log.trace ("Accediendo unidad de persistencia: " + unidadPersistencia);
		pmf = JDOHelper.getPersistenceManagerFactory (unidadPersistencia);
	}
    public static PersistenciaAlohandes getInstance ()
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes();
		}
		return instance;
	}
    public static PersistenciaAlohandes getInstance (JsonObject tableConfig)
	{
		if (instance == null)
		{
			instance = new PersistenciaAlohandes (tableConfig);
		}
		return instance;
	}
    public void cerrarUnidadPersistencia ()
	{
		pmf.close ();
		instance = null;
	}
    private List <String> leerNombresTablas (JsonObject tableConfig)
	{
		JsonArray nombres = tableConfig.getAsJsonArray("tablas") ;
		List <String> resp = new LinkedList <String> ();
		for (JsonElement nom : nombres)
		{
			resp.add (nom.getAsString ());
		}
		return resp;
	}
    private void crearClasesSQL ()
	{
        sqlHotel = new SQLHotel(this);
        sqlHostal = new SQLHostal(this);
        sqlEmpresa = new SQLEmpresa(this);
        sqlPropietarioInmueble = new SQLPropietarioInmueble(this);
        sqlCliente = new SQLCliente(this);
        sqlOferta = new SQLOferta(this);
        sqlContrato = new SQLContrato(this);
        sqlInmueble = new SQLInmueble(this);
        sqlSeguro = new SQLSeguro(this);
        sqlHabitacion = new SQLHabitacion(this);
        sqlServicio = new SQLServicio(this);
        sqlBrindan = new SQLBrindan(this);
	}
    public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}
    public String darTablaHotel ()
	{
		return tablas.get (1);
	}
    public String darTablaHostal ()
	{
		return tablas.get (2);
	}
    public String darTablaPropietarioInmueble ()
	{
		return tablas.get (3);
	}
    public String darTablaEmpresa ()
	{
		return tablas.get (4);
	}
    public String darTablaCliente ()
	{
		return tablas.get (5);
	}
    public String darTablaOferta ()
	{
		return tablas.get (6);
	}
    public String darTablaContrato ()
	{
		return tablas.get (7);
	}
    public String darTablaInmueble ()
	{
		return tablas.get (8);
	}
    public String darTablaSeguro()
	{
		return tablas.get (9);
	}
    public String darTablaHabitacion ()
	{
		return tablas.get (10);
	}
    public String darTablaServicio ()
	{
		return tablas.get (11);
	}
    public String darTablaBrindan ()
	{
		return tablas.get (12);
	}


    private long nextval ()
	{
        long resp = sqlUtil.nextval (pmf.getPersistenceManager());
        log.trace ("Generando secuencia: " + resp);
        return resp;
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
    /*  MANEJADOR DE EMPRESA   */
    public Empresa adicionarEmpresa(BigDecimal id_e,String nombre, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idPN = nextval ();
            long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm, id_e, nombre,tipo);
            tx.commit();

            log.trace ("Inserción de Empresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Empresa (id_e, nombre, tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarEmpresaPorNombre (String nombreE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpresa.eliminarEmpresaPorNombre(pm, nombreE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarEmpresaPorId(long idE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlEmpresa.eliminarEmpresaPorId(pm, idE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Empresa> darEmpresas ()
	{
		return sqlEmpresa.darEmpresas(pmf.getPersistenceManager());
	}
    public List<Empresa> darEmpresasPorNombre (String nombreE)
	{
		return sqlEmpresa.darEmpresasPorNombre(pmf.getPersistenceManager(), nombreE);
	}
    public Empresa darEmpresaPorId (long idE)
	{
		return sqlEmpresa.darEmpresaPorId(pmf.getPersistenceManager(), idE);
	}
    /*  MANEJADOR DE PROPIETARIO INMUEBLE */
    public PropietarioInmueble adicionarPropietarioInmueble(BigDecimal id_Pi,String nombre, String vinculo, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idPI = nextval ();
            long tuplasInsertadas = sqlPropietarioInmueble.adicionarPropietarioInmueble(pm, id_Pi, nombre, vinculo, tipo);
            tx.commit();

            log.trace ("Inserción de Empresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new PropietarioInmueble (id_Pi,tipo,nombre,vinculo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarPropietarioInmueblePorNombre (String nombreE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPropietarioInmueble.eliminarPropietarioInmueblePorNombre(pm, nombreE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarPropietarioInmueblePorId(long idE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPropietarioInmueble.eliminarPropietarioInmueblePorId(pm, idE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<PropietarioInmueble> darPropietariosInmueble ()
	{
		return sqlPropietarioInmueble.darPropietariosInmueble(pmf.getPersistenceManager());
	}
    public List<PropietarioInmueble> darPropietarioInmueblePorNombre (String nombreE)
	{
		return sqlPropietarioInmueble.darPropietariosInmueblePorNombre(pmf.getPersistenceManager(), nombreE);
	}
    public PropietarioInmueble darPropietarioInmueblePorId (long idE)
	{
		return sqlPropietarioInmueble.darPropietarioInmueblePorId(pmf.getPersistenceManager(), idE);
	}
    /* MANEJADOR DE HOTEL */
    public Hotel adicionarHotel(BigDecimal id, String nombre, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idPI = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, id, nombre,tipo);
            tx.commit();

            log.trace ("Inserción de Hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Hotel (id,nombre,tipo);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarHotelPorNombre (String nombreE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorNombre(pm, nombreE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarHotelPorId(long idHT) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHotel.eliminarHotelPorId(pm, idHT);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Hotel> darHoteles()
	{
		return sqlHotel.darHoteles(pmf.getPersistenceManager());
	}
    public Hotel darHotelPorNombre (String nombre)
	{
		return sqlHotel.darHotelPorNombre(pmf.getPersistenceManager(), nombre);
	}
    /* MANEJADOR DE HOSTAL */
    public Hostal adicionarHostal(BigDecimal id_hs, String nombre, int recepcion, Timestamp horaCierre, Timestamp horaApertura, String tipo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idHS = nextval ();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, id_hs, nombre,recepcion, horaCierre,horaApertura,tipo);
            tx.commit();

            log.trace ("Inserción de Hostal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Hostal (id_hs, tipo, nombre,recepcion, horaApertura,horaCierre);
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarHostalPorNombre (String nombreE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorNombre(pm, nombreE);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarHostalPorId(long idHS) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHostal.eliminarHostalPorId(pm, idHS);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Hostal> darHostales()
	{
		return sqlHostal.darHostales(pmf.getPersistenceManager());
	}
    public List<Hostal> darHostalesPorNombre (String nombre)
	{
		return sqlHostal.darHostalesPorNombre(pmf.getPersistenceManager(), nombre);
	}
    /* MANEJADOR DE CONTRATO */
    public Contrato adicionarContrato(BigDecimal id_C,String tipoContrato, Timestamp fechaInicio, int duracion, int duracionPrePaid, int precioEspecial, int precioFinal, int fechaPago, BigDecimal id_Oferta) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idC = nextval ();
            long tuplasInsertadas = sqlContrato.adicionarContrato(pm, id_C,tipoContrato,duracion,duracionPrePaid,precioEspecial,precioFinal,fechaInicio,fechaPago,id_Oferta);
            tx.commit();

            log.trace ("Inserción de Contrato: " + id_C + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Contrato(id_C,tipoContrato,duracion,duracionPrePaid,precioEspecial,precioFinal,fechaInicio,fechaPago,id_Oferta);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarContratoPorId(long idC) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlContrato.eliminarContratoPorId(pm, idC);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Contrato> darContratos()
	{
		return sqlContrato.darContratos(pmf.getPersistenceManager());
	}
    public List<Contrato> darContratoPorTipoContrato (String tipoC)
	{
		return sqlContrato.darContratosPorTipoContrato(pmf.getPersistenceManager(), tipoC);
	}
    /* MANEJADOR DE INMUEBLE */
    public Inmueble adicionarInmueble(BigDecimal id_I, String tipoI, String ubicacion, int costoAdmin, int numHabitaciones, BigDecimal id_Oferta) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idC = nextval ();
            long tuplasInsertadas = sqlInmueble.adicionarInmueble(pm, id_I,tipoI,ubicacion,costoAdmin, numHabitaciones,id_Oferta);
            tx.commit();

            log.trace ("Inserción de Inmueble: " + tipoI + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Inmueble(id_I,tipoI,ubicacion, costoAdmin,numHabitaciones,id_Oferta);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarInmueblePorId(long idI) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlInmueble.eliminarInmueblePorId(pm, idI);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Inmueble> darInmuebles()
	{
		return sqlInmueble.darInmuebles(pmf.getPersistenceManager());
	}
    public List<Inmueble> darInmueblesPorNumHabitaciones (int numHabitacion)
	{
		return sqlInmueble.darInmueblesPorNumHabitaciones(pmf.getPersistenceManager(), numHabitacion);
	}
    public List<Inmueble> darInmueblesPorUbicacion (String ubicacion)
	{
		return sqlInmueble.darInmueblesPorUbicacion(pmf.getPersistenceManager(), ubicacion);
	}
    public List<Inmueble> darInmueblesAptos ()
	{
		return sqlInmueble.darInmueblesAptos(pmf.getPersistenceManager());
	}
    public List<Inmueble> darInmueblesViviendas ()
	{
		return sqlInmueble.darInmueblesViviendas(pmf.getPersistenceManager());
	}
    /* MANEJADOR DE SEGURO */
    public Seguro adicionarSeguro(BigDecimal id_S, Timestamp fechaVence, String descripcion, BigDecimal InmuebleID) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //ong idS = nextval ();
            long tuplasInsertadas = sqlSeguro.adicionarSeguro(pm, id_S,fechaVence,descripcion,InmuebleID);
            tx.commit();

            log.trace ("Inserción de Seguro: " + id_S + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Seguro(id_S,fechaVence,descripcion,InmuebleID);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarSeguroPorId(long idI) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSeguro.eliminarSeguroPorId(pm, idI);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarSeguroPorFecha(Timestamp fechaVence) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSeguro.eliminarSeguroPorFecha(pm, fechaVence);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Seguro> darSeguros()
	{
		return sqlSeguro.darSeguros(pmf.getPersistenceManager());
	}
    public List<Seguro> darSegurosPorIdMueble (int idMueble)
	{
		return sqlSeguro.darSegurosPorIdMueble(pmf.getPersistenceManager(), idMueble);
	}
    public List<Seguro> darSegurosPorFecha (Timestamp fechaVence)
	{
		return sqlSeguro.darSegurosPorFecha(pmf.getPersistenceManager(), fechaVence);
	}
    public Seguro darSeguroPorId (long idS)
	{
		return sqlSeguro.darSeguroPorId(pmf.getPersistenceManager(), idS);
	}
    /* MANEJADOR DE HABITACION */
    public Habitacion adicionarHabitacion(BigDecimal id_h, int tamanio, String tipoH, int precioFinal, String ubicacion, BigDecimal id_Oferta, BigDecimal id_Contrato, BigDecimal id_Inmueble) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long id_H = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, id_h,tamanio,tipoH,precioFinal,ubicacion,id_Oferta, id_Contrato,id_Inmueble);
            tx.commit();

            log.trace ("Inserción de Habitacion: " + id_h + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Habitacion(id_h,tamanio,tipoH,precioFinal,ubicacion, id_Oferta,id_Contrato,id_Inmueble);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarHabitacionPorId(long idH) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlHabitacion.eliminarHabitacionPorId(pm, idH);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Habitacion> darHabitaciones()
	{
		return sqlHabitacion.darHabitaciones(pmf.getPersistenceManager());
	}
    public List<Habitacion> darHabitacionesPorUbicacion (String ubicacion)
	{
		return sqlHabitacion.darHabitacionesPorUbicacion(pmf.getPersistenceManager(), ubicacion);
	}
    public Habitacion darHabitacionPorId (long idH)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idH);
	}
    /* MANEJADOR DE SERVICIO */
    public Servicio adicionarServicio(BigDecimal id_s, int precio, int incluido, int cantidad, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idSH = nextval ();
            long tuplasInsertadas = sqlServicio.adicionarServicio(pm, id_s,precio,incluido,cantidad,nombre);
            tx.commit();

            log.trace ("Inserción de Servicio: " + id_s + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Servicio(id_s,precio,incluido,cantidad,nombre);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarServicioPorId(long idH) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorId(pm, idH);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarServicioPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicio.eliminarServicioPorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Servicio> darServicios()
	{
		return sqlServicio.darServicios(pmf.getPersistenceManager());
	}
    public List<Servicio> darServicioPorInclusion (int inclusion)
	{
		return sqlServicio.darServiciosPorInclusion(pmf.getPersistenceManager(), inclusion);
	}
    public Servicio darServicioPorId (long idSH)
	{
		return sqlServicio.darServicioPorId(pmf.getPersistenceManager(), idSH);
	}
    public Servicio darServicioPorNombre (String nombre)
	{
		return sqlServicio.darServicioPorNombre(pmf.getPersistenceManager(), nombre);
	}
    /* MANEJADOR DE OFERTA */
    public Oferta adicionarOferta(BigDecimal id_O, int reservado,BigDecimal id_Cliente,BigDecimal id_PropietarioI,BigDecimal id_Empresa,BigDecimal id_Hostal,BigDecimal id_Hotel) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idSH = nextval ();
            long tuplasInsertadas = sqlOferta.adicionarOferta(pm, id_O,reservado,id_Cliente,id_PropietarioI,id_Empresa,id_Hostal,id_Hotel);
            tx.commit();

            log.trace ("Inserción de ServicioH: " + id_O + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Oferta(id_O,reservado,id_Cliente,id_PropietarioI,id_Empresa,id_Hostal,id_Hotel);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarOfertaPorId(long idO) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlOferta.eliminarOfertaPorId(pm, idO);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public Oferta darOfertaPorId (long idO)
	{
		return sqlOferta.darOfertaPorId(pmf.getPersistenceManager(), idO);
	}
    public List<Oferta> darOfertas()
	{
		return sqlOferta.darOfertas(pmf.getPersistenceManager());
	}
    /* MANEJADOR DE CLIENTE */
    public Cliente adicionarCliente(BigDecimal id_cl, String nombre, String correo,String contrasenia) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            //long idCl = nextval ();
            long tuplasInsertadas = sqlCliente.adicionarCliente(pm, id_cl,nombre,correo,contrasenia);
            tx.commit();

            log.trace ("Inserción de ServicioH: " + id_cl + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Cliente(id_cl,nombre,correo,contrasenia);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarClientePorId(long idH) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorId(pm, idH);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarClientePorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlCliente.eliminarClientePorNombre(pm, nombre);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public List<Cliente> darClientes()
	{
		return sqlCliente.darClientes(pmf.getPersistenceManager());
	}
    public Cliente darClientePorCorreoContrasenia (String correo,String contrasenia)
	{
		return sqlCliente.darClientePorCorreoContrasenia(pmf.getPersistenceManager(), correo,contrasenia);
	}
    /* MANEJADOR DE BRINDAN */
    public Brindan adicionarBrindan(BigDecimal id_Habitacion, BigDecimal id_Servicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long tuplasInsertadas = sqlBrindan.adicionarBrindan(pm, id_Habitacion, id_Servicio);
            tx.commit();
            log.trace ("Inserción de Brindan: "+id_Habitacion+" + "+id_Servicio+ ": "+tuplasInsertadas+" tuplas insertadas");

            return new Brindan(id_Habitacion,id_Servicio);}
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return null;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public long eliminarBrindanPorIds(long id_Habitacion, long id_Servicio) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlBrindan.eliminarBrindanPorids(pm, id_Habitacion, id_Servicio);
            tx.commit();

            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
            return -1;
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }
	}
    public Brindan darBrindanPorId (long id_Habitacion, long id_Servicio)
	{
		return sqlBrindan.darBrindanPorIds(pmf.getPersistenceManager(),id_Habitacion,id_Servicio);
	}

    /* Limpiar Alohandes */
    public long [] limpiarAlohandes ()
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long [] resp = sqlUtil.limpiarParranderos (pm);
            tx.commit ();
            log.info ("Borrada la base de datos");
            return resp;
        }
        catch (Exception e)
        {
//        	e.printStackTrace();
        	log.error ("Exception : " + e.getMessage() + "\n" + darDetalleException(e));
        	return new long[] {-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1,-1};
        }
        finally
        {
            if (tx.isActive())
            {
                tx.rollback();
            }
            pm.close();
        }	
	}
}