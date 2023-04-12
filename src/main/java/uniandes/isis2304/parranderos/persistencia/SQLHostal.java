package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
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
    public long adicionarHostal(PersistenceManager pm, BigDecimal ID_HS, String NOMBRE, int RECEPCION, Timestamp HORAAPERTURA,Timestamp HORACIERRE,String TIPO) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHostal()
                + "(ID_HS, NOMBRE, RECEPCION, HORACIERRE, HORAAPERTURA,TIPO) values (?, ?, ?, ?, ?,?)");
        q.setParameters(ID_HS, NOMBRE, RECEPCION, HORACIERRE, HORAAPERTURA, TIPO);
        return (long) q.executeUnique();
    }
    public long eliminarHostalPorNombre(PersistenceManager pm, String NOMBRE) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
    }
    public long eliminarHostalPorId(PersistenceManager pm, long ID_HS) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal() + " WHERE ID_HS = ?");
        q.setParameters(ID_HS);
        return (long) q.executeUnique();
    }
    public Hostal darHostalPorId(PersistenceManager pm, long ID_HS) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE ID_HS = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(ID_HS);
        return (Hostal) q.executeUnique();
    }
    public List<Hostal> darHostalesPorNombre(PersistenceManager pm, String NOMBRE) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal() + " WHERE NOMBRE = ?");
        q.setResultClass(Hostal.class);
        q.setParameters(NOMBRE);
        return (List<Hostal>) q.executeList();
    }
    public List<Hostal> darHostales(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHostal());
        q.setResultClass(Hostal.class);
        return (List<Hostal>) q.executeList();
    }
}