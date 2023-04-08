package uniandes.isis2304.parranderos.persistencia;

import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Empresa;

class SQLEmpresa{
    
	private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    //bob the builder
    public SQLEmpresa(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarEmpresa (PersistenceManager pm, long id, String nombreE, String tipo) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpresa() + "(ID_E, NOMBRE, TIPO) values (?, ?, ?)");
        q.setParameters(id, nombreE, tipo);
        return (long) q.executeUnique();
    }
    public long eliminarEmpresaPorNombre (PersistenceManager pm, String nombreE)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa() + " WHERE NOMBRE = ?");
        q.setParameters(nombreE);
        return (long) q.executeUnique();
	}
    public long eliminarEmpresaPorId (PersistenceManager pm, long idE)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa() + " WHERE ID_E = ?");
        q.setParameters(idE);
        return (long) q.executeUnique();
	}
    public Empresa darEmpresaPorId (PersistenceManager pm, long idE) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE ID_E = ?");
        q.setResultClass(Empresa.class);
        q.setParameters(idE);
        return (Empresa) q.executeUnique();
    }
    public List<Empresa> darEmpresasPorNombre (PersistenceManager pm, String nombrePN) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE NOMBRE = ?");
        q.setResultClass(Empresa.class);
        q.setParameters(nombrePN);
        return (List<Empresa>) q.executeList();
    }
    public List<Empresa> darEmpresas (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa ());
        q.setResultClass(Empresa.class);
        return (List<Empresa>) q.executeList();
    }
}