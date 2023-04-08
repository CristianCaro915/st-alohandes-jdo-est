package uniandes.isis2304.parranderos.persistencia;

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

    public long adicionarHabitacion(PersistenceManager pm, long id_H, int tamano, String tipoH, int precioFinal,
            String ubicacion, long id_Oferta, long id_Contrato, long id_Inmueble) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaHabitacion()
                + "(ID_H, TAMANIO, TIPOH, PRECIOFINAL, UBICACION,ID_OFERTA,ID_CONTRATO,ID_INMUEBLE) values (?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id_H, tamano, tipoH, precioFinal, ubicacion, id_Oferta,id_Contrato,id_Inmueble);
        return (long) q.executeUnique();
    }
    public long eliminarHabitacionPorId(PersistenceManager pm, long idHabitacion) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion() + " WHERE NUMHABITACION = ?");
        q.setParameters(idHabitacion);
        return (long) q.executeUnique();
    }
    public Habitacion darHabitacionPorId (PersistenceManager pm, long idSH) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE NUMHABITACION = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(idSH);
		return (Habitacion) q.executeUnique();
	}
    public List<Habitacion> darHabitacionesPorUbicacion (PersistenceManager pm, String ubicacion) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion () + " WHERE UBICACION = ?");
		q.setResultClass(Habitacion.class);
		q.setParameters(ubicacion);
		return (List<Habitacion>) q.executeList();
	}
    public List<Habitacion> darHabitaciones (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaHabitacion ());
		q.setResultClass(Habitacion.class);
		return (List<Habitacion>) q.executeList();
	}
}