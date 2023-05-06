package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
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
        public long adicionarPropietarioInmueble (PersistenceManager pm, BigDecimal ID_PI, String NOMBRE, String VINCULO, String TIPO) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaPropietarioInmueble () + "(ID_PI, NOMBRE, VINCULO, TIPO) values (?, ?, ?, ?)");
        q.setParameters(ID_PI, NOMBRE, VINCULO, TIPO);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueble (PersistenceManager pm, String NOMBRE)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueblePorId (PersistenceManager pm, BigDecimal ID_PI)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE ID_PI = ?");
        q.setParameters(ID_PI);
        return (long) q.executeUnique();
    }
    public long eliminarPropietarioInmueblePorNombre (PersistenceManager pm, String NOMBRE)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
    }
    public PropietarioInmueble darPropietarioInmueblePorId (PersistenceManager pm, long ID_PI) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble () + " WHERE ID_PI = ?");
        q.setResultClass(PropietarioInmueble.class);
        q.setParameters(ID_PI);
        return (PropietarioInmueble) q.executeUnique();
    }
    public List<PropietarioInmueble> darPropietariosInmueblePorNombre (PersistenceManager pm, String NOMBRE) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble () + " WHERE NOMBRE = ?");
        q.setResultClass(PropietarioInmueble.class);
        q.setParameters(NOMBRE);
        return (List<PropietarioInmueble>) q.executeList();
    }
    public List<PropietarioInmueble> darPropietariosInmueble (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaPropietarioInmueble ());
        q.setResultClass(PropietarioInmueble.class);
        return (List<PropietarioInmueble>) q.executeList();
    }
    public List<Object[]> darPropietarioGananciaActual (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL,"SELECT pi.NOMBRE,pi.TIPO,c.TIPOCONTRATO,c.DURACION,o.RESERVADO,c.PRECIOESPECIAL,c.PRECIOFINAL AS PRECIO FROM" + pp.darTablaPropietarioInmueble ()+
        " pi INNER JOIN "+pp.darTablaOferta()+"o ON o.ID_PROPIETARIOI=pi.ID_PI INNER JOIN "+pp.darTablaContrato()+
        " c ON c.ID_OFERTA=o.ID_O WHERE o.reservado=1 AND c.FECHAINICIO <= '12/03/2023' AND CONTRATO.FECHAINICIO >= '01/01/2023'");
        return (List<Object[]>) q.executeList();
    }
}