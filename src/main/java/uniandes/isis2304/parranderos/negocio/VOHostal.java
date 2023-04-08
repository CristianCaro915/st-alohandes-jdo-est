package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public interface VOHostal {
    public long getId();
    public String getNombre();
    public String getTipo();
    public int getRecepcion();
    public Timestamp getCierre();
    public Timestamp getApertura();

}