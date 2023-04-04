package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.parranderos.negocio.Seguro;

public class SQLSeguro {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLSeguro(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarSeguro (PersistenceManager pm, long id, DATE fechaVence, String descripcion,long id_inmueble) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSeguro () + "(ID_S, FECHAVENCE, DESCRIPCION, ID_INMUEBLE) values (?, ?, ?, ?)");
        q.setParameters(id, fechaVence, descripcion, id_inmueble);
        return (long) q.executeUnique();
	}
    public long eliminarSeguroPorFecha (PersistenceManager pm, DATE fechaVence) //REVISARR !!!!!!!!!!!!!
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSeguro () + " WHERE FECHAVENCE = ?");
        q.setParameters(fechaVence);
        return (long) q.executeUnique();
	}
    public long eliminarSeguroPorId (PersistenceManager pm, long idI)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSeguro () + " WHERE ID_S = ?");
        q.setParameters(idI);
        return (long) q.executeUnique();
	}
    public Seguro darSeguroPorId (PersistenceManager pm, long idS) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE ID_S = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(idS);
		return (Seguro) q.executeUnique();
	}
    public List<Seguro> darSegurosPorFecha (PersistenceManager pm, DATE fechaVence) //REVISAR !!!!!!!
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE FECHAVENCE = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(fechaVence);
		return (List<Seguro>) q.executeList();
	}
    public List<Seguro> darSegurosPorIdMueble (PersistenceManager pm, long idMueble) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE ID_INMUEBLE = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(idMueble);
		return (List<Seguro>) q.executeList();
	}
    public List<Seguro> darSeguros (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro ());
		q.setResultClass(Seguro.class);
		return (List<Seguro>) q.executeList();
	}
}