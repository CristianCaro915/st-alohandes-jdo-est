package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Inmueble;

public class SQLInmueble {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLInmueble(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarInmueble (PersistenceManager pm, BigDecimal ID_I, String TIPOI, String UBICACION, int COSTOADMIN, int NUMHABITACIONES, BigDecimal ID_OFERTA) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaInmueble () + "(ID_I, TIPOI, UBICACION, COSTOADMIN, NUMHABITACIONES, ID_OFERTA) values (?,?,?,?,?)");
        q.setParameters(ID_I, TIPOI, UBICACION, COSTOADMIN, NUMHABITACIONES, ID_OFERTA);
        return (long) q.executeUnique();
	}
    public long eliminarInmueblePorId (PersistenceManager pm, BigDecimal ID_I)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInmueble () + " WHERE ID_I = ?");
        q.setParameters(ID_I);
        return (long) q.executeUnique();
	}
    public Inmueble darInmueblePorId (PersistenceManager pm, long ID_I) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE ID_I = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(ID_I);
		return (Inmueble) q.executeUnique();
	}
    public List<Inmueble> darInmueblesPorUbicacion (PersistenceManager pm, String UBICACION) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE UBICACION = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(UBICACION);
		return (List<Inmueble>) q.executeList();
	}
    public List<Inmueble> darInmueblesPorNumHabitaciones (PersistenceManager pm, int NUMHABITACIONES) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE NUMHABITACIONES = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(NUMHABITACIONES);
		return (List<Inmueble>) q.executeList();
	}
    public List<Inmueble> darInmuebles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble ());
		q.setResultClass(Inmueble.class);
		return (List<Inmueble>) q.executeList();
	}
	public List<Inmueble> darInmueblesAptos (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE NUMHABITACIONES = APARTAMENTO");
		q.setResultClass(Inmueble.class);
		return (List<Inmueble>) q.executeList();
	}
	public List<Inmueble> darInmueblesViviendas (PersistenceManager pm) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE NUMHABITACIONES = VIVIENDA");
		q.setResultClass(Inmueble.class);
		return (List<Inmueble>) q.executeList();
	}
	public List<Object[]> darTop20Inmuebles(PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT i.TIPOI AS CATEGORIA, COUNT(i.ID_I) AS CANTIDAD FROM "+pp.darTablaHabitacion()+" i WHERE ROWNUM<20 GROUP BY i.TIPOI");
		return (List<Object[]>) q.executeList();
    }
}