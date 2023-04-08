package uniandes.isis2304.parranderos.persistencia;

import java.sql.Timestamp;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hostal;

class SQLHostal {
    private final static String SQL = PersistenciaAlohandes.SQL;
    private PersistenciaAlohandes pp;

    public SQLHostal(PersistenciaAlohandes pp) {
        this.pp = pp;
    }
    public long adicionarHostal(PersistenceManager pm, long id, String nombre, int recepcion, Timestamp Apertura,Timestamp Cierre,String tipo) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal()
                + "(ID_S, NOMBRE, RECEPCION, HORACIERRE, HORAAPERTURA,TIPO) values (?, ?, ?, ?, ?,?)");
        q.setParameters(id, nombre, recepcion, Cierre, Apertura, tipo);
        return (long) q.executeUnique();
    }
    public long eliminarHostalPorNombre(PersistenceManager pm, String nombreHostal) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE NOMBRE = ?");
        q.setParameters(nombreHostal);
        return (long) q.executeUnique();
    }
    public long eliminarHostalPorId(PersistenceManager pm, long idHostal) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE ID_S = ?");
        q.setParameters(idHostal);
        return (long) q.executeUnique();
    }
    public Hostal darHostalPorId(PersistenceManager pm, long idHostal) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE ID_S = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(idHostal);
        return (Hostal) q.executeUnique();
    }
    public List<Hostal> darHostalesPorNombre(PersistenceManager pm, String nombreHostal) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE NOMBRE = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(nombreHostal);
        return (List<Hostal>) q.executeList();
    }
    public List<Hostal> darHostales(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal());
        q.setResultClass(Hostal.class);
        return (List<Hostal>) q.executeList();
    }
}