package uniandes.isis2304.parranderos.persistencia;

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
    public long adicionarCliente (PersistenceManager pm, long id, String nombre, String correo, String contrasenia) 
    {
        Query q = pm.newQuery(SQL, "INSERT INTO " + pp.darTablaCliente() + "(ID_CL , NOMBRE, CORREO, CONTRASEÑA) values (?,?,?,?)");
        q.setParameters(id, nombre, correo, contrasenia);
        return (long) q.executeUnique();
    }
    public long eliminarClientePorNombre (PersistenceManager pm, String nombreC)
    {
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE NOMBRE = ?");
        q.setParameters(nombreC);
        return (long) q.executeUnique();
	}
    public long eliminarClientePorId (PersistenceManager pm, long idCL)
	{
        Query q = pm.newQuery(SQL, "DELETE FROM " + pp.darTablaCliente() + " WHERE ID_CL = ?");
        q.setParameters(idCL);
        return (long) q.executeUnique();
	}
    public Cliente darClientePorId (PersistenceManager pm, long idCL) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE ID_CL = ?");
        q.setResultClass(Cliente.class);
        q.setParameters(idCL);
        return (Cliente) q.executeUnique();
    }
    public Cliente darClientePorCorreoContrasenia (PersistenceManager pm, String correo,String contrasenia) 
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente () + " WHERE CORREO = ? AND CONTRASENIA = ?");
        q.setResultClass(Cliente.class);
        q.setParameters(correo,contrasenia);
        return (Cliente) q.executeList();
    }
    public List<Cliente> darClientes (PersistenceManager pm)
    {
        Query q = pm.newQuery(SQL, "SELECT * FROM " + pp.darTablaCliente ());
        q.setResultClass(Cliente.class);
        return (List<Cliente>) q.executeList();
    }
}
