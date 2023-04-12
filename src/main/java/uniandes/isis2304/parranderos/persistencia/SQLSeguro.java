package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import uniandes.isis2304.parranderos.negocio.Seguro;

public class SQLSeguro {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLSeguro(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarSeguro (PersistenceManager pm, BigDecimal ID_S, Timestamp FECHAVENCE, String DESCRIPCION,long ID_INMUEBLE) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSeguro () + "(ID_S, FECHAVENCE, DESCRIPCION, ID_INMUEBLE) values (?, ?, ?, ?)");
        q.setParameters(ID_S, FECHAVENCE, DESCRIPCION, ID_INMUEBLE);
        return (long) q.executeUnique();
	}
    public long eliminarSeguroPorFecha (PersistenceManager pm, Timestamp fechaVence) //REVISARR !!!!!!!!!!!!!
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSeguro () + " WHERE FECHAVENCE = ?");
        q.setParameters(fechaVence);
        return (long) q.executeUnique();
	}
    public long eliminarSeguroPorId (PersistenceManager pm, long ID_S)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSeguro () + " WHERE ID_S = ?");
        q.setParameters(ID_S);
        return (long) q.executeUnique();
	}
    public Seguro darSeguroPorId (PersistenceManager pm, long ID_S) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE ID_S = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(ID_S);
		return (Seguro) q.executeUnique();
	}
    public List<Seguro> darSegurosPorFecha (PersistenceManager pm, Timestamp FECHAVENCE) //REVISAR !!!!!!!
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE FECHAVENCE = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(FECHAVENCE);
		return (List<Seguro>) q.executeList();
	}
    public List<Seguro> darSegurosPorIdMueble (PersistenceManager pm, long ID_INMUEBLE) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro () + " WHERE ID_INMUEBLE = ?");
		q.setResultClass(Seguro.class);
		q.setParameters(ID_INMUEBLE);
		return (List<Seguro>) q.executeList();
	}
    public List<Seguro> darSeguros (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSeguro ());
		q.setResultClass(Seguro.class);
		return (List<Seguro>) q.executeList();
	}
}