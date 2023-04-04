package uniandes.isis2304.parranderos.persistencia;

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
    public long adicionarInmueble (PersistenceManager pm, long id, String tipoI, String ubicacion, int costoAdmin, int disponibilidad, int numHabitaciones, long idPropietarioI, long idSujetoC) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaInmueble () + "(ID_I, TIPOI, UBICACION, COSTOADMIN, DISPONIBILIDAD, NUMHABITACIONES, ID_PROPIETARIOI,ID_SUJETOC) values (?,?,?,?,?,?,?)");
        q.setParameters(id, tipoI, ubicacion, costoAdmin, disponibilidad, numHabitaciones, idPropietarioI,idSujetoC);
        return (long) q.executeUnique();
	}
    public long eliminarInmueblePorId (PersistenceManager pm, long idI)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInmueble () + " WHERE ID_I = ?");
        q.setParameters(idI);
        return (long) q.executeUnique();
	}
    public Inmueble darInmueblePorId (PersistenceManager pm, long idI) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE ID_I = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(idI);
		return (Inmueble) q.executeUnique();
	}
    public List<Inmueble> darInmueblesPorUbicacion (PersistenceManager pm, String ubicacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE UBICACION = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(ubicacion);
		return (List<Inmueble>) q.executeList();
	}
    public List<Inmueble> darInmueblesPorNumHabitaciones (PersistenceManager pm, long numHabitaciones) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble () + " WHERE NUMHABITACIONES = ?");
		q.setResultClass(Inmueble.class);
		q.setParameters(numHabitaciones);
		return (List<Inmueble>) q.executeList();
	}
    public List<Inmueble> darInmuebles (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaInmueble ());
		q.setResultClass(Inmueble.class);
		return (List<Inmueble>) q.executeList();
	}
}