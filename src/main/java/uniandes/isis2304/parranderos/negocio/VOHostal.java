package uniandes.isis2304.parranderos.negocio;

import java.util.Date;

public interface VOHostal {
    public long getId();
    public String getNombre();
    public int getRecepcion();
    public Date getCierre();
    public Date getApertura();

}