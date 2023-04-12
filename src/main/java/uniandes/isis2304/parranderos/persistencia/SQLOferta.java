package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Oferta;

public class SQLOferta {
    private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    public SQLOferta(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarOferta (PersistenceManager pm, BigDecimal ID_O, int RESERVADO,BigDecimal ID_CLIENTE, 
    BigDecimal ID_PROPIETARIOI, BigDecimal ID_EMPRESA, BigDecimal ID_HOSTAL, BigDecimal ID_HOTEL) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOferta() + 
        "(ID_O, RESERVADO,ID_CLIENTE, ID_PROPIETARIOI,ID_EMPRESA,ID_HOSTAL,ID_HOTEL) values (?,?,?,?,?,?,?)");
        q.setParameters(ID_O, RESERVADO,ID_CLIENTE,ID_PROPIETARIOI,ID_EMPRESA,ID_HOSTAL,ID_HOTEL);
        return (long) q.executeUnique();
    }
    public long eliminarOfertaPorId (PersistenceManager pm, long ID_O)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOferta() + " WHERE ID_O = ?");
        q.setParameters(ID_O);
        return (long) q.executeUnique();
	}
    public Oferta darOfertaPorId (PersistenceManager pm, long ID_O) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOferta () + " WHERE ID_O = ?");
        q.setResultClass(Oferta.class);
        q.setParameters(ID_O);
        return (Oferta) q.executeUnique();
    }
    public List<Oferta> darOfertas (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOferta ());
        q.setResultClass(Oferta.class);
        return (List<Oferta>) q.executeList();
    }
}
