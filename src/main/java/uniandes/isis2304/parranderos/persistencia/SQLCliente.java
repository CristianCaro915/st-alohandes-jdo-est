package uniandes.isis2304.parranderos.persistencia;

import java.math.BigDecimal;
import java.util.List;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;

import uniandes.isis2304.parranderos.negocio.Cliente;

public class SQLCliente {
    private final static String SQL = PersistenciaAlohandes.SQL;
	private PersistenciaAlohandes pp;

    public SQLCliente(PersistenciaAlohandes pp){
        this.pp=pp;
    }
    public long adicionarCliente (PersistenceManager pm, BigDecimal ID_CL, String NOMBRE, String CORREO, String CONTRASENIA) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente() + "(ID_CL , NOMBRE, CORREO, CONTRASENIA) values (?,?,?,?)");
        q.setParameters(ID_CL, NOMBRE, CORREO, CONTRASENIA);
        return (long) q.executeUnique();
    }
    public long eliminarClientePorNombre (PersistenceManager pm, String NOMBRE)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE NOMBRE = ?");
        q.setParameters(NOMBRE);
        return (long) q.executeUnique();
	}
    public long eliminarClientePorId (PersistenceManager pm, long ID_CL)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE ID_CL = ?");
        q.setParameters(ID_CL);
        return (long) q.executeUnique();
	}
    public Cliente darClientePorId (PersistenceManager pm, long ID_CL) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE ID_CL = ?");
        q.setResultClass(Cliente.class);
        q.setParameters(ID_CL);
        return (Cliente) q.executeUnique();
    }
    public Cliente darClientePorCorreoContrasenia (PersistenceManager pm, String CORREO,String CONTRASENIA) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE CORREO = ? AND CONTRASENIA = ?");
        q.setResultClass(Cliente.class);
        q.setParameters(CORREO,CONTRASENIA);
        return (Cliente) q.executeList();
    }
    public List<Cliente> darClientes (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
        q.setResultClass(Cliente.class);
        return (List<Cliente>) q.executeList();
    }
}
