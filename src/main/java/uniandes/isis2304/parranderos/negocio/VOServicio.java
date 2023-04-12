package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOServicio {

    public BigDecimal getId();

    public int getPrecio();

    public int getIncluido();

    public int getCantidad();

    public String getNombre();

}