package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.PropietarioInmueble;

class SQLPropietarioInmueble{
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLPropietarioInmueble(PersistenciaAlohandes pp){
        this.pp=pp;
    }
        public long adicionarPropietarioInmueble (PersistenceManager pm, long id, String nombre, String vinculo, String tipo) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPropietarioInmueble () + "(ID_PI, NOMBRE, VINCULO, TIPO) values (?, ?, ?, ?)");
        q.setParameters(id, nombre, vinculo, tipo);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueble (PersistenceManager pm, String nombrePI)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setParameters(nombrePI);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueblePorId (PersistenceManager pm, long idPI)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE ID_PI = ?");
        q.setParameters(idPI);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueblePorNombre (PersistenceManager pm, String nombre)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setParameters(nombre);
        return (long) q.executeUnique();
    }
    public PropietarioInmueble darPropietarioInmueblePorId (PersistenceManager pm, long idPI) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble () + " WHERE ID_PI = ?");
        q.setResultClass(PropietarioInmueble.class);
        q.setParameters(idPI);
        return (PropietarioInmueble) q.executeUnique();
    }
    public List<PropietarioInmueble> darPropietariosInmueblePorNombre (PersistenceManager pm, String nombrePN) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setResultClass(PropietarioInmueble.class);
        q.setParameters(nombrePN);
        return (List<PropietarioInmueble>) q.executeList();
    }
    public List<PropietarioInmueble> darPropietariosInmueble (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble ());
        q.setResultClass(PropietarioInmueble.class);
        return (List<PropietarioInmueble>) q.executeList();
    }
}