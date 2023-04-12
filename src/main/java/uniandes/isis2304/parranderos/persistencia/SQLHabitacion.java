package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;
import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import uniandes.isis2304.parranderos.negocio.Habitacion;

class SQLHabitacion {

    private final static String SQL = PersistenciaAlohandes.SQL;
    private PersistenciaAlohandes pp;

    public SQLHabitacion(PersistenciaAlohandes pp) {
        this.pp = pp;
    }

    public long adicionarHabitacion(PersistenceManager pm, BigDecimal ID_H, int TAMANIO, String TIPOH, int PRECIOFINAL,
            String UBICACION, BigDecimal ID_OFERTA, BigDecimal ID_CONTRATO, BigDecimal ID_INMUEBLE) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion()
                + "(ID_H, TAMANIO, TIPOH, PRECIOFINAL, UBICACION,ID_OFERTA,ID_CONTRATO,ID_INMUEBLE) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(ID_H, TAMANIO, TIPOH, PRECIOFINAL, UBICACION, ID_OFERTA,ID_CONTRATO,ID_INMUEBLE);
        return (long) q.executeUnique();
    }
    public long eliminarHabitacionPorId(PersistenceManager pm, long ID_H) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion() + " WHERE ID_H = ?");
        q.setParameters(ID_H);
        return (long) q.executeUnique();
    }
    public Habitacion darHabitacionPorId (PersistenceManager pm, long ID_H) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE ID_H = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(ID_H);
		return (Habitacion) q.executeUnique();
	}
    public List<Habitacion> darHabitacionesPorUbicacion (PersistenceManager pm, String UBICACION) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE UBICACION = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(UBICACION);
		return (List<Habitacion>) q.executeList();
	}
    public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
}