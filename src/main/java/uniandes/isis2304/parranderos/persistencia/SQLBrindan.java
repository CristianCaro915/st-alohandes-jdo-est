package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Brindan;

public class SQLBrindan {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;
    //bob the builder
    public SQLBrindan(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarBrindan (PersistenceManager pm, long idHabitacion, long idServicio) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaBrindan () + "(ID_HABITACION, ID_SERVICIO) values (?, ?)");
        q.setParameters(idHabitacion, idServicio);
        return (long) q.executeUnique();
	}
    public long eliminarBrindanPorids (PersistenceManager pm, long idHabitacion, long idServicio) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ? AND ID_SERVICIO = ?");
        q.setParameters(idHabitacion,idServicio);
        return (long) q.executeUnique();
	}
    public Brindan darBrindanPorIds (PersistenceManager pm, long idHabitacion, long idServicioH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ? AND ID_SERVICIOH = ?");
		q.setResultClass(Brindan.class);
		q.setParameters(idHabitacion,idServicioH);
		return (Brindan) q.executeUnique();
	}
    public List<Brindan> darBrindanPoridHabitacion (PersistenceManager pm, long idHabitacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_HABITACION = ?");
		q.setResultClass(Brindan.class);
		q.setParameters(idHabitacion);
		return (List<Brindan>) q.executeList();
	}
    public List<Brindan> darBrindanPoridServicioH (PersistenceManager pm, long idServicioH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan () + " WHERE ID_SERVICIO = ?");
		q.setResultClass(Brindan.class);
		q.setParameters(idServicioH);
		return (List<Brindan>) q.executeList();
	}
    public List<Brindan> darBrindan (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaBrindan ());
		q.setResultClass(Brindan.class);
		return (List<Brindan>) q.executeList();
	}
}

