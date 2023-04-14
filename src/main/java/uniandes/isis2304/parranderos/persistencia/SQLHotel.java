package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Hotel;

class SQLHotel {

    private final static String SQL = PersistenciaAlohandes.SQL;
    private PersistenciaAlohandes pp;

    public SQLHotel(PersistenciaAlohandes pp) {
        this.pp = pp;
    }
    public long adicionarHotel(PersistenceManager pm, BigDecimal ID_HT, String NOMBRE, String TIPO) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel() + "(ID_HT, NOMBRE, TIPO)values (?,?,?)");
        q.setParameters(ID_HT, NOMBRE,TIPO);
        return (long) q.executeUnique();
    }
    public long eliminarHotelPorNombre(PersistenceManager pm, String NOMBRE) {

        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
    }
    public long eliminarHotelPorId(PersistenceManager pm, BigDecimal ID_HT) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE ID_HT = ?");
        q.setParameters(ID_HT);
        return (long) q.executeUnique();
    }
    public Hotel darHotelPorNombre(PersistenceManager pm, String NOMBRE) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel() + " WHERE NOMBRE = ?");
        q.setResultClass(Hotel.class);
        q.setParameters(NOMBRE);
        return (Hotel) q.executeList();
    }
    public List<Hotel> darHoteles(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel());
        q.setResultClass(Hotel.class);
        return (List<Hotel>) q.executeList();
    }

}