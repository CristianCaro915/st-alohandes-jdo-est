package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
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
    public long adicionarEmpresa (PersistenceManager pm, BigDecimal ID_E, String NOMBRE, String TIPO) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaEmpresa() + "(ID_E, NOMBRE, TIPO) values (?, ?, ?)");
        q.setParameters(ID_E, NOMBRE, TIPO);
        return (long) q.executeUnique();
    }
    public long eliminarEmpresaPorNombre (PersistenceManager pm, String NOMBRE)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa() + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
	}
    public long eliminarEmpresaPorId (PersistenceManager pm, BigDecimal ID_E)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaEmpresa() + " WHERE ID_E = ?");
        q.setParameters(ID_E);
        return (long) q.executeUnique();
	}
    public Empresa darEmpresaPorId (PersistenceManager pm, long ID_E) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE ID_E = ?");
        q.setResultClass(Empresa.class);
        q.setParameters(ID_E);
        return (Empresa) q.executeUnique();
    }
    public List<Empresa> darEmpresasPorNombre (PersistenceManager pm, String NOMBRE) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa () + " WHERE NOMBRE = ?");
        q.setResultClass(Empresa.class);
        q.setParameters(NOMBRE);
        return (List<Empresa>) q.executeList();
    }
    public List<Empresa> darEmpresas (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaEmpresa ());
        q.setResultClass(Empresa.class);
        return (List<Empresa>) q.executeList();
    }
}