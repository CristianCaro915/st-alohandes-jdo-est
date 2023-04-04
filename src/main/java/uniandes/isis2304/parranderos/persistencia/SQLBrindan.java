package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Brinda;

public class SQLBrindan {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;
    //bob the builder
    public SQLBrindan(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarBrindan (PersistenceManager pm, long idHabitacion, long idServicioH) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBrindan () + "(ID_HABITACION, ID_SERVICIOH) values (?, ?)");
        q.setParameters(idHabitacion, idServicioH);
        return (long) q.executeUnique();
	}
    public long eliminarBrindanPorids (PersistenceManager pm, long idHabitacion, long idServicioH) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ? AND ID_SERVICIOH = ?");
        q.setParameters(idHabitacion,idServicioH);
        return (long) q.executeUnique();
	}
    public Brinda darBrindanPorIds (PersistenceManager pm, long idHabitacion, long idServicioH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ? AND ID_SERVICIOH = ?");
		q.setResultClass(Brinda.class);
		q.setParameters(idHabitacion,idServicioH);
		return (Brinda) q.executeUnique();
	}
    public List<Brinda> darBrindanPoridHabitacion (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ?");
		q.setResultClass(Brinda.class);
		q.setParameters(idHabitacion);
		return (List<Brinda>) q.executeList();
	}
    public List<Brinda> darBrindanPoridServicioH (PersistenceManager pm, long idServicioH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_SERVICIOH = ?");
		q.setResultClass(Brinda.class);
		q.setParameters(idServicioH);
		return (List<Brinda>) q.executeList();
	}
    public List<Brinda> darBrindanPorIdMueble (PersistenceManager pm, long idMueble) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan() + " WHERE ID_INMUEBLE = ?");
		q.setResultClass(Brinda.class);
		q.setParameters(idMueble);
		return (List<Brinda>) q.executeList();
	}
    public List<Brinda> darBrindan (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan ());
		q.setResultClass(Brinda.class);
		return (List<Brinda>) q.executeList();
	}
}

