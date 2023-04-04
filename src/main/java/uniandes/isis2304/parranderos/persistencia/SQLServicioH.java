package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.ServicioH;

public class SQLServicioH {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLServicioH(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarServicioH (PersistenceManager pm, long id, int precio, int incluido, int cantidad, String nombre) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaServicioH () + "(ID_SH, PRECIO, INCLUIDO, CANTIDAD,NOMBRE) values (?,?,?,?,?)");
        q.setParameters(id, precio, incluido, cantidad,nombre);
        return (long) q.executeUnique();
	}
    public long eliminarServicioHPorNombre (PersistenceManager pm, String nombreSH)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioH () + " WHERE NOMBRE = ?");
        q.setParameters(nombreSH);
        return (long) q.executeUnique();
	}
    public long eliminarServicioHPorId (PersistenceManager pm, long idSH)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicioH () + " WHERE ID_SH = ?");
        q.setParameters(idSH);
        return (long) q.executeUnique();
	}
    public ServicioH darServicioHPorId (PersistenceManager pm, long idSH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioH () + " WHERE ID_S = ?");
		q.setResultClass(ServicioH.class);
		q.setParameters(idSH);
		return (ServicioH) q.executeUnique();
	}
    public ServicioH darServicioHPorNombre (PersistenceManager pm, String nombreSH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioH () + " WHERE NOMBRE = ?");
		q.setResultClass(ServicioH.class);
		q.setParameters(nombreSH);
		return (ServicioH) q.executeUnique();
	}
    public List<ServicioH> darServiciosHPorInclusion (PersistenceManager pm, int incluido)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioH () + " WHERE INCLUIDO = ?");
		q.setResultClass(ServicioH.class);
		q.setParameters(incluido);
		return (List<ServicioH>) q.executeList();
	}
    public List<ServicioH> darServiciosH (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaServicioH());
		q.setResultClass(ServicioH.class);
		return (List<ServicioH>) q.executeList();
	}   
}
