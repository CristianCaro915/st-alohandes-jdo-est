/**~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 * Universidad	de	los	Andes	(Bogotá	- Colombia)
 * Departamento	de	Ingeniería	de	Sistemas	y	Computación
 * Licenciado	bajo	el	esquema	Academic Free License versión 2.1
 * 		
 * Curso: isis2304 - Sistemas Transaccionales
 * Proyecto: Parranderos Uniandes
 * @version 1.0
 * @author Germán Bravo
 * Julio de 2018
 * 
 * Revisado por: Claudia Jiménez, Christian Ariza
 * ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
 */

package uniandes.isis2304.parranderos.persistencia;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

/**
 * Clase que encapsula los métodos que hacen acceso a la base de datos para el concepto BAR de Parranderos
 * Nótese que es una clase que es sólo conocida en el paquete de persistencia
 * 
 * @author Germán Bravo
 */
class SQLUtil
{
	/* ****************************************************************
	 * 			Constantes
	 *****************************************************************/
	/**
	 * Cadena que representa el tipo de consulta que se va a realizar en las sentencias de acceso a la base de datos
	 * Se renombra acá para facilitar la escritura de las sentencias
	 */
	private final static String SQL = PersistenciaAlohandes.SQL;

	/* ****************************************************************
	 * 			Atributos
	 *****************************************************************/
	/**
	 * El manejador de persistencia general de la aplicación
	 */
	private PersistenciaAlohandes pp;

	/* ****************************************************************
	 * 			Métodos
	 *****************************************************************/

	/**
	 * Constructor
	 * @param pp - El Manejador de persistencia de la aplicación
	 */
	public SQLUtil (PersistenciaAlohandes pp)
	{
		this.pp = pp;
	}
	
	/**
	 * Crea y ejecuta la sentencia SQL para obtener un nuevo número de secuencia
	 * @param pm - El manejador de persistencia
	 * @return El número de secuencia generado
	 */
	public long nextval (PersistenceManager pm)
	{
        Query q = pm.newQuery(SQL, "SELECT "+ pp.darSeqAlohandes() + ".nextval FROM DUAL");
        q.setResultClass(Long.class);
        long resp = (long) q.executeUnique();
        return resp;
	}

	/**
	 * Crea y ejecuta las sentencias SQL para cada tabla de la base de datos - EL ORDEN ES IMPORTANTE 
	 * @param pm - El manejador de persistencia
	 * @return Un arreglo con 12 números que indican el número de tuplas borradas en las tablas GUSTAN, SIRVEN, VISITAN, BEBIDA,
	 * TIPOBEBIDA, BEBEDOR y BAR, respectivamente
	 */
	public long [] limpiarParranderos (PersistenceManager pm)
	{
        Query qHotel = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHotel());          
        Query qHostal = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHostal());
        Query qPropietarioI = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaPropietarioInmueble());
        Query qEmpresa = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa());
        Query qCliente = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente());
        Query qOferta = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOferta());
        Query qContrato = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaContrato());
		Query qInmueble = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaInmueble());
		Query qSeguro = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaSeguro());
		Query qHabitacion = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaHabitacion());
		Query qServicio = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaServicio());
		Query qBrindan = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaBrindan());

        long hotelEliminados = (long) qHotel.executeUnique ();
        long hostalEliminados = (long) qHostal.executeUnique ();
        long propietarioIEliminadas = (long) qPropietarioI.executeUnique ();
        long empresaEliminadas = (long) qEmpresa.executeUnique ();
        long clienteEliminados = (long) qCliente.executeUnique ();
        long ofertaEliminados = (long) qOferta.executeUnique ();
        long contratoEliminados = (long) qContrato.executeUnique ();
		long inmuebleEliminados = (long) qInmueble.executeUnique ();
		long seguroEliminados = (long) qSeguro.executeUnique ();
		long habitacionEliminados = (long) qHabitacion.executeUnique ();
		long servicioEliminados = (long) qServicio.executeUnique ();
		long brindanEliminados = (long) qBrindan.executeUnique ();
        return new long[] {hotelEliminados, hostalEliminados, propietarioIEliminadas, empresaEliminadas, 
			clienteEliminados, ofertaEliminados, contratoEliminados,inmuebleEliminados,seguroEliminados,
			habitacionEliminados,servicioEliminados,brindanEliminados};
	}

}
