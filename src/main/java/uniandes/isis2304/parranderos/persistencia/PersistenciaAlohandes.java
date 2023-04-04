package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.sql.Date;
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

import oracle.sql.DATE;
import uniandes.isis2304.parranderos.negocio.PersonaNatural;
import uniandes.isis2304.parranderos.negocio.Empresa;
import uniandes.isis2304.parranderos.negocio.PropietarioInmueble;
import uniandes.isis2304.parranderos.negocio.SujetoComunidad;
import uniandes.isis2304.parranderos.negocio.Hotel;
import uniandes.isis2304.parranderos.negocio.Hostal;
import uniandes.isis2304.parranderos.negocio.Contrato;
import uniandes.isis2304.parranderos.negocio.Inmueble;
import uniandes.isis2304.parranderos.negocio.Seguro;
import uniandes.isis2304.parranderos.negocio.Habitacion;
import uniandes.isis2304.parranderos.negocio.ServicioH;
import uniandes.isis2304.parranderos.negocio.Brinda;// EDITAR NOMBRE

public class PersistenciaAlohandes {

    private static Logger log = Logger.getLogger(PersistenciaAlohandes.class.getName());
    public final static String SQL = "javax.jdo.query.SQL";
    private static PersistenciaAlohandes instance;
    private PersistenceManagerFactory pmf;
    private List <String> tablas;
    private SQLUtil sqlUtil;

    private SQLPersonaNatural sqlPersonaNatural;
    private SQLEmpresa sqlEmpresa;
    private SQLPropietarioInmueble sqlPropietarioInmueble;
    private SQLSujetoComunidad sqlSujetoComunidad;
    private SQLHotel sqlHotel;
    private SQLHostal sqlHostal;
    private SQLInmueble sqlInmueble;
    private SQLContrato sqlContrato;
    private SQLSeguro sqlSeguro;
    private SQLHabitacion sqlHabitacion;
    private SQLServicioH sqlServicioH;

    private PersistenciaAlohandes(){
        pmf = JDOHelper.getPersistenceManagerFactory("Alohandes");		
		crearClasesSQL ();

        tablas = new LinkedList<String> ();
		tablas.add ("Alohandes_sequence");//revisar
		tablas.add ("PERSONANATURAL");//0
		tablas.add ("EMPRESA");//1
		tablas.add ("PROPIETARIOINMUEBLE");//2
		tablas.add ("SUJETOCOMUNIDAD");//3
		tablas.add ("HOTEL");//4
		tablas.add ("HOSTAL");//5
		tablas.add ("CONTRATO");//6
        tablas.add ("INMUEBLE");//7
        tablas.add ("SEGURO");//8
        tablas.add ("HABITACION");//9
        tablas.add ("SERVICIOH");//10
        tablas.add ("BRINDAN");//11
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
        sqlPersonaNatural = new SQLPersonaNatural(this);
        sqlEmpresa = new SQLEmpresa(this);
        sqlPropietarioInmueble = new SQLPropietarioInmueble(this);
        sqlSujetoComunidad = new SQLSujetoComunidad(this);
        sqlHotel = new SQLHotel(this);
        sqlHostal = new SQLHostal(this);
        sqlContrato = new SQLContrato(this);
        sqlInmueble = new SQLInmueble(this);
        sqlHabitacion = new SQLHabitacion(this);
        sqlServicioH = new SQLServicioH(this);
        sqlSeguro = new SQLSeguro(this);
	}
    public String darSeqAlohandes ()
	{
		return tablas.get (0);
	}
    public String darTablaPersonaNatural ()
	{
		return tablas.get (1);
	}
    public String darTablaEmpresa ()
	{
		return tablas.get (2);
	}
    public String darTablaPropietarioInmueble ()
	{
		return tablas.get (3);
	}
    public String darTablaSujetoComunidad ()
	{
		return tablas.get (4);
	}
    public String darTablaHotel ()
	{
		return tablas.get (5);
	}
    public String darTablaHostal ()
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
    public String darTablaServicioH ()
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
    /*
     *  MANEJADOR DE PERSONA NATURAL  
     */
    public PersonaNatural adicionarPersonaNatural(String nombre, String vinculo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPN = nextval ();
            long tuplasInsertadas = sqlPersonaNatural.adicionarPersonaNatural(pm, idPN, nombre, vinculo);
            tx.commit();

            log.trace ("Inserción de PersonaNatural: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new PersonaNatural (idPN, nombre, vinculo);
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
    public long eliminarPersonaNaturalPorNombre (String nombrePN) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPersonaNatural.eliminarPersonaNaturalPorNombre(pm, nombrePN);
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
    public long eliminarPersonaNaturalPorId (long idPN) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlPersonaNatural.eliminarPersonaNaturalPorId(pm, idPN);
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
    public List<PersonaNatural> darPersonasNatural ()
	{
		return sqlPersonaNatural.darPersonasNaturales(pmf.getPersistenceManager());
	}
    public List<PersonaNatural> darPersonasNaturalPorNombre (String nombreBar)
	{
		return sqlPersonaNatural.darPersonasNaturalesPorNombre(pmf.getPersistenceManager(), nombreBar);
	}
    public PersonaNatural darPersonaNaturalPorId (long idPN)
	{
		return sqlPersonaNatural.darPersonaNaturalPorId(pmf.getPersistenceManager(), idPN);
	}
    /*
     *  MANEJADOR DE EMPRESA
     */
    public Empresa adicionarEmpresa(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPN = nextval ();
            long tuplasInsertadas = sqlEmpresa.adicionarEmpresa(pm, idPN, nombre);
            tx.commit();

            log.trace ("Inserción de Empresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Empresa (idPN, nombre);
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
    public PropietarioInmueble adicionarPropietarioInmueble(String nombre, String vinculo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPI = nextval ();
            long tuplasInsertadas = sqlPropietarioInmueble.adicionarPropietarioInmueble(pm, idPI, nombre, vinculo);
            tx.commit();

            log.trace ("Inserción de Empresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new PropietarioInmueble (idPI,nombre,vinculo);
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
    public List<PropietarioInmueble> darPropietarioInmueble ()
	{
		return sqlPropietarioInmueble.darPropietariosInmueble(pmf.getPersistenceManager());
	}
    public List<PropietarioInmueble> darPorNombre (String nombreE)
	{
		return sqlPropietarioInmueble.darPropietariosInmueblePorNombre(pmf.getPersistenceManager(), nombreE);
	}
    public PropietarioInmueble darPropietarioInmueblePorId (long idE)
	{
		return sqlPropietarioInmueble.darPropietarioInmueblePorId(pmf.getPersistenceManager(), idE);
	}
    public SujetoComunidad adicionarSujetoComunidad(String nombre, String vinculo) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPI = nextval ();
            long tuplasInsertadas = sqlSujetoComunidad.adicionarSujetoComunidad(pm, idPI, nombre, vinculo);
            tx.commit();

            log.trace ("Inserción de Empresa: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new SujetoComunidad (idPI,nombre,vinculo);
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
    public long eliminarSujetoComunidadPorNombre (String nombreE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSujetoComunidad.eliminarSujetoComunidadPorNombre(pm, nombreE);
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
    public long eliminarSujetoComunidadPorId(long idE) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlSujetoComunidad.eliminarSeguroPorId(pm, idE);
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
    public List<SujetoComunidad> darSujetosComunidad ()
	{
		return sqlSujetoComunidad.darSujetosComunidad(pmf.getPersistenceManager());
	}
    public List<SujetoComunidad> darSujetoComunidadPorVinculo (String vinculo)
	{
		return sqlSujetoComunidad.darSujetosComunidadPorVinculo(pmf.getPersistenceManager(), vinculo);
	}
    public Hotel adicionarHotel(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idPI = nextval ();
            long tuplasInsertadas = sqlHotel.adicionarHotel(pm, idPI, nombre);
            tx.commit();

            log.trace ("Inserción de Hotel: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Hotel (idPI,nombre);
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
    public List<Hotel> darHotelPorNombre (String nombre)
	{
		return sqlHotel.darHotelPorNombre(pmf.getPersistenceManager(), nombre);
	}
    public Hostal adicionarHostal(String nombre, int recepcion, Date horaCierre, Date horaApertura) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idHS = nextval ();
            long tuplasInsertadas = sqlHostal.adicionarHostal(pm, idHS, nombre,recepcion, horaCierre,horaApertura);
            tx.commit();

            log.trace ("Inserción de Hostal: " + nombre + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Hostal (idHS,nombre,recepcion, horaCierre,horaApertura);
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
    public List<Hostal> darHostalPorNombre (String nombre)
	{
		return sqlHostal.darHostalesPorNombre(pmf.getPersistenceManager(), nombre);
	}
    public Contrato adicionarContrato(String tipoContrato, DATE fechaInicio, int duracion, int duracionPrePaid, int precioEspecial, int precioFinal, DATE fechaPagoMensual, long EmpresaID, long PersonaNaturalID, long PropietarioID) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idC = nextval ();
            long tuplasInsertadas = sqlContrato.adicionarContrato(pm, idC,tipoContrato,fechaInicio,duracion,duracionPrePaid,precioEspecial,precioFinal,fechaPagoMensual,EmpresaID,PersonaNaturalID,PropietarioID);
            tx.commit();

            log.trace ("Inserción de Contrato: " + idC + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Contrato(idC,tipoContrato,fechaInicio,duracion,duracionPrePaid,precioEspecial,precioFinal,fechaPagoMensual,EmpresaID,PersonaNaturalID,PropietarioID);}
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
    public Inmueble adicionarInmueble(String tipoI, String ubicacion, int costoAdmin, int disponibilidad, int numHabitaciones, long PropietarioID, long SujetoCID) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idC = nextval ();
            long tuplasInsertadas = sqlInmueble.adicionarInmueble(pm, idC,tipoI,ubicacion,costoAdmin, disponibilidad, numHabitaciones,PropietarioID,SujetoCID);
            tx.commit();

            log.trace ("Inserción de Inmueble: " + tipoI + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Inmueble(idC,tipoI,ubicacion, costoAdmin, disponibilidad,numHabitaciones,PropietarioID,SujetoCID);}
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
    public Seguro adicionarSeguro(DATE fechaVence, String descripcion, long InmuebleID) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idS = nextval ();
            long tuplasInsertadas = sqlSeguro.adicionarSeguro(pm, idS,fechaVence,descripcion,InmuebleID);
            tx.commit();

            log.trace ("Inserción de Seguro: " + idS + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Seguro(idS,fechaVence,descripcion,InmuebleID);}
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
    public long eliminarSeguroPorFecha(DATE fechaVence) 
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
    public List<Seguro> darSegurosPorFecha (DATE fechaVence)
	{
		return sqlSeguro.darSegurosPorFecha(pmf.getPersistenceManager(), fechaVence);
	}
    public Seguro darSeguroPorId (long idS)
	{
		return sqlSeguro.darSeguroPorId(pmf.getPersistenceManager(), idS);
	}
    public Habitacion adicionarHabitacion(long numHabitacion, int tamanio, String tipoH, int precioFinal, String ubicacion, long HostalID, long HotelID, long ContratoID, long InmuebleID) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idC = nextval ();
            long tuplasInsertadas = sqlHabitacion.adicionarHabitacion(pm, idC,tamanio,tipoH,precioFinal,ubicacion, HostalID, HotelID,ContratoID, InmuebleID);
            tx.commit();

            log.trace ("Inserción de Habitacion: " + numHabitacion + ": " + tuplasInsertadas + " tuplas insertadas");

            return new Habitacion(idC,tamanio,tipoH,precioFinal,ubicacion, HostalID, HotelID,ContratoID, InmuebleID);}
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
    public Habitacion darHabitacionPorId (long idSH)
	{
		return sqlHabitacion.darHabitacionPorId(pmf.getPersistenceManager(), idSH);
	}
    public ServicioH adicionarServicioH(int precio, int incluido, int cantidad, String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long idSH = nextval ();
            long tuplasInsertadas = sqlServicioH.adicionarServicioH(pm, idSH,precio,incluido,cantidad,nombre);
            tx.commit();

            log.trace ("Inserción de ServicioH: " + idSH + ": " + tuplasInsertadas + " tuplas insertadas");

            return new ServicioH(idSH,precio,incluido,cantidad,nombre);}
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
    public long eliminarServicioHPorId(long idH) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioH.eliminarServicioHPorId(pm, idH);
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
    public long eliminarServicioHPorNombre(String nombre) 
	{
		PersistenceManager pm = pmf.getPersistenceManager();
        Transaction tx=pm.currentTransaction();
        try
        {
            tx.begin();
            long resp = sqlServicioH.eliminarServicioHPorNombre(pm, nombre);
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
    public List<ServicioH> darServiciosH()
	{
		return sqlServicioH.darServiciosH(pmf.getPersistenceManager());
	}
    public List<ServicioH> darServicioHPorInclusion (int inclusion)
	{
		return sqlServicioH.darServiciosHPorInclusion(pmf.getPersistenceManager(), inclusion);
	}
    public ServicioH darServicioHPorId (long idSH)
	{
		return sqlServicioH.darServicioHPorId(pmf.getPersistenceManager(), idSH);
	}
    public ServicioH darServicioHPorId (String nombre)
	{
		return sqlServicioH.darServicioHPorNombre(pmf.getPersistenceManager(), nombre);
	}
     
}