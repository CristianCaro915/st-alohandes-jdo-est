package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOInmueble {

    public BigDecimal getId();

    public String getTipoI();

    public String getUbicacion();

    public int getCostoAdmin();

    public int getNumHabitaciones();

    public BigDecimal getIdOferta();
}