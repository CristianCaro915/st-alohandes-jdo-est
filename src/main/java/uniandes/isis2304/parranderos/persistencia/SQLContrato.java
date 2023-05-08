package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import java.math.BigDecimal;
import java.sql.Timestamp;
import uniandes.isis2304.parranderos.negocio.Contrato;

public class SQLContrato {
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    public SQLContrato(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarContrato(PersistenceManager pm, BigDecimal ID_C, String TIPOCONTRATO, int DURACION, int DURACIONPREPAID,
            int PRECIOESPECIAL, int PRECIOFINAL, Timestamp FECHAINICIO, int FECHAPAGO,BigDecimal ID_OFERTA) {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaContrato()
                + "(ID_C, TIPOCONTRATO, FECHAINICIO,  DURACION,  DURACIONPREPAID, PRECIOESPECIAL,PRECIOFINAL,  FECHAPAGO,  ID_OFERTA)values (?, ?, ?, ?, ?, ?, ?, ?, ?)");
        q.setParameters(ID_C, TIPOCONTRATO, FECHAINICIO, DURACION,DURACIONPREPAID, PRECIOESPECIAL, PRECIOFINAL,FECHAPAGO, ID_OFERTA);
        return (long) q.executeUnique();
    }
    public long eliminarContratoPorId(PersistenceManager pm, BigDecimal ID_C) 
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaContrato() + " WHERE ID_C = ?");
        q.setParameters(ID_C);
        return (long) q.executeUnique();
    }
    public Contrato darContratoPorId(PersistenceManager pm, long ID_C) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato() + " WHERE ID_C = ?");
        q.setResultClass(Contrato.class);
        q.setParameters(ID_C);
        return (Contrato) q.executeUnique();
    }
    public List<Contrato> darContratosPorTipoContrato (PersistenceManager pm, String TIPOCONTRATO) 
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato () + " WHERE TIPOCONTRATO = ?");
		q.setResultClass(Contrato.class);
		q.setParameters(TIPOCONTRATO);
		return (List<Contrato>) q.executeList();
	}
    public List<Contrato> darContratos (PersistenceManager pm)
	{
		Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaContrato ());
		q.setResultClass(Contrato.class);
		return (List<Contrato>) q.executeList();
	}
    public List<Object[]> darTop20Contratos(PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT c.TIPOCONTRATO AS CATEGORIA, COUNT(c.ID_C) AS CANTIDAD FROM "+pp.darTablaContrato()+" c WHERE ROWNUM<20 GROUP BY c.TIPOCONTRATO");
		return (List<Object[]>) q.executeList();
    }
}
