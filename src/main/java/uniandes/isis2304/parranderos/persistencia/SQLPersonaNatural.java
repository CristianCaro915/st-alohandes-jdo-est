package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.PersonaNatural;

class SQLPersonaNatural{
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLPersonaNatural(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarPersonaNatural (PersistenceManager pm, long id, String nombre, String vinculo) 
	{
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPersonaNatural () + "(ID_PN, NOMBRE, VINCULO) values (?, ?, ?)");
        q.setParameters(id, nombre, vinculo);
        return (long) q.executeUnique();
	}
    public long eliminarPersonaNaturalPorNombre (PersistenceManager pm, String nombrePN)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaNatural () + " WHERE NOMBRE = ?");
        q.setParameters(nombrePN);
        return (long) q.executeUnique();
	}
    public long eliminarPersonaNaturalPorId (PersistenceManager pm, long idPN)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPersonaNatural () + " WHERE ID_PN = ?");
        q.setParameters(idPN);
        return (long) q.executeUnique();
	}
    public PersonaNatural darPersonaNaturalPorId (PersistenceManager pm, long idPN) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural () + " WHERE ID_PN = ?");
		q.setResultClass(PersonaNatural.class);
		q.setParameters(idPN);
		return (PersonaNatural) q.executeUnique();
	}
    public List<PersonaNatural> darPersonasNaturalesPorNombre (PersistenceManager pm, String nombrePN) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural () + " WHERE NOMBRE = ?");
		q.setResultClass(PersonaNatural.class);
		q.setParameters(nombrePN);
		return (List<PersonaNatural>) q.executeList();
	}
    public List<PersonaNatural> darPersonasNaturales (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPersonaNatural ());
		q.setResultClass(PersonaNatural.class);
		return (List<PersonaNatural>) q.executeList();
	}
}
