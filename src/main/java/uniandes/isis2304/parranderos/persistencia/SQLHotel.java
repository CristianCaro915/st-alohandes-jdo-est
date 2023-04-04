package uniandes.isis2304.parranderos.persistencia;

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
    public long adicionarHotel(PersistenceManager pm, long idH, String nombre) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHotel() + "(ID_HT, NOMBRE) values (?,?)");
        q.setParameters(idH, nombre);
        return (long) q.executeUnique();
    }
    public long eliminarHotelPorNombre(PersistenceManager pm, String nombreHotel) {

        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE NOMBRE = ?");
        q.setParameters(nombreHotel);
        return (long) q.executeUnique();
    }
    public long eliminarHotelPorId(PersistenceManager pm, long idHotel) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel() + " WHERE ID_HT = ?");
        q.setParameters(idHotel);
        return (long) q.executeUnique();
    }
    public List<Hotel> darHotelPorNombre(PersistenceManager pm, String nombreHotel) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel() + " WHERE NOMBRE = ?");
        q.setResultClass(Hotel.class);
        q.setParameters(nombreHotel);
        return (List<Hotel>) q.executeList();
    }
    public List<Hotel> darHoteles(PersistenceManager pm) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHotel());
        q.setResultClass(Hotel.class);
        return (List<Hotel>) q.executeList();
    }

}