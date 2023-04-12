package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Servicio;

public class SQLServicio {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLServicio(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarServicio (PersistenceManager pm, BigDecimal ID_SH, int PRECIO, int INCLUIDO, int CANTIDAD, String NOMBRE) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicio () + "(ID_SH, PRECIO, INCLUIDO, CANTIDAD,NOMBRE) values (?,?,?,?,?)");
        q.setParameters(ID_SH, PRECIO, INCLUIDO, CANTIDAD,NOMBRE);
        return (long) q.executeUnique();
	}
    public long eliminarServicioPorNombre (PersistenceManager pm, String NOMBRE)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio () + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
	}
    public long eliminarServicioPorId (PersistenceManager pm, long ID_SH)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio () + " WHERE ID_SH = ?");
        q.setParameters(ID_SH);
        return (long) q.executeUnique();
	}
    public Servicio darServicioPorId (PersistenceManager pm, long ID_SH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE ID_SH = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(ID_SH);
		return (Servicio) q.executeUnique();
	}
    public Servicio darServicioPorNombre (PersistenceManager pm, String NOMBRE) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE NOMBRE = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(NOMBRE);
		return (Servicio) q.executeUnique();
	}
    public List<Servicio> darServiciosPorInclusion (PersistenceManager pm, int INCLUIDO)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio () + " WHERE INCLUIDO = ?");
		q.setResultClass(Servicio.class);
		q.setParameters(INCLUIDO);
		return (List<Servicio>) q.executeList();
	}
    public List<Servicio> darServicios (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicio());
		q.setResultClass(Servicio.class);
		return (List<Servicio>) q.executeList();
	}   
}
