package uniandes.isis2304.parranderos.persistencia;

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
    public long adicionarOferta (PersistenceManager pm, long id, int reservado,long id_Cliente, 
    long id_PropietarioI, long id_Empresa, long id_Hostal, long id_Hotel) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaOferta() + 
        "(ID_O, RESERVADO,ID_CLIENTE, ID_PROPIETARIOI,ID_EMPRESA,ID_HOSTAL,ID_HOTEL) values (?,?,?,?,?,?,?)");
        q.setParameters(id, reservado);
        return (long) q.executeUnique();
    }
    public long eliminarOfertaPorId (PersistenceManager pm, long idO)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaOferta() + " WHERE ID_O = ?");
        q.setParameters(idO);
        return (long) q.executeUnique();
	}
    public Oferta darOfertaPorId (PersistenceManager pm, long idE) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOferta () + " WHERE ID_O = ?");
        q.setResultClass(Oferta.class);
        q.setParameters(idE);
        return (Oferta) q.executeUnique();
    }
    public List<Oferta> darOfertas (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaOferta ());
        q.setResultClass(Oferta.class);
        return (List<Oferta>) q.executeList();
    }
}
