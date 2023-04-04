package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import oracle.sql.DATE;
import uniandes.isis2304.parranderos.negocio.Contrato;

public class SQLContrato {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    public SQLContrato(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarContrato(PersistenceManager pm, Long id, String tipoContrato, DATE fechaInicio, int duracion,
            int duracionPrePaid,int precioEspecial, int precioFinal, DATE fechaPagoMensualidad,
            Long EmpresaID, Long PersonaNaturalID, Long PropietarioId_inmueble) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaContrato()
                + "(ID_C, TIPOCONTRATO, DURACION,  DURACIONPREPAID,  PRECIOESPECIAL, PRECIOFINAL,FECHAINICIO,  FECHAPAGOMENSUAL, ID_EMPRESA,  ID_PERSONANATUAL,  ID_PROPIETARIOI)values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(id, tipoContrato, duracion, duracionPrePaid,precioEspecial, precioFinal, fechaInicio,
         fechaPagoMensualidad, EmpresaID, PersonaNaturalID, PropietarioId_inmueble);
        return (long) q.executeUnique();
    }
    public long eliminarContratoPorId(PersistenceManager pm, long idContrato) {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaContrato() + " WHERE ID_C = ?");
        q.setParameters(idContrato);
        return (long) q.executeUnique();
    }
    public Contrato darContratoPorId(PersistenceManager pm, long idContrato) {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato() + " WHERE ID_C = ?");
        q.setResultClass(Contrato.class);
        q.setParameters(idContrato);
        return (Contrato) q.executeUnique();
    }
    public List<Contrato> darContratosPorTipoContrato (PersistenceManager pm, String tipoContrato) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato () + " WHERE TIPOCONTRATO = ?");
		q.setResultClass(Contrato.class);
		q.setParameters(tipoContrato);
		return (List<Contrato>) q.executeList();
	}
    public List<Contrato> darContratos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato ());
		q.setResultClass(Contrato.class);
		return (List<Contrato>) q.executeList();
	}
}
