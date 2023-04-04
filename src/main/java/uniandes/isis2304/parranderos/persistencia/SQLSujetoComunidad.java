package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.SujetoComunidad;

public class SQLSujetoComunidad {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLSujetoComunidad(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarSujetoComunidad (PersistenceManager pm, long id, String nombre,String vinculo ) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaSujetoComunidad () + "(IS_SC, NOMBRE, VINCULO) values (?,?,?)");
        q.setParameters(id, nombre, vinculo);
        return (long) q.executeUnique();
	}
    public long eliminarSujetoComunidadPorNombre (PersistenceManager pm, String nombreSC) 
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSujetoComunidad() + " WHERE NOMBRE = ?");
        q.setParameters(nombreSC);
        return (long) q.executeUnique();
	}
    public long eliminarSeguroPorId (PersistenceManager pm, long idSC)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSujetoComunidad() + " WHERE ID_S = ?");
        q.setParameters(idSC);
        return (long) q.executeUnique();
	}
    public List<SujetoComunidad> darSujetosComunidadPorVinculo (PersistenceManager pm, String vinculo)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSujetoComunidad());
		q.setResultClass(SujetoComunidad.class);
		return (List<SujetoComunidad>) q.executeList();
	}
    public List<SujetoComunidad> darSujetosComunidad (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaSujetoComunidad());
		q.setResultClass(SujetoComunidad.class);
		return (List<SujetoComunidad>) q.executeList();
	}
}
