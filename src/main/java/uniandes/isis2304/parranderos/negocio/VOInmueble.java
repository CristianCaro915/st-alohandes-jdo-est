package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOInmueble {

    public BigDecimal getId();

    public String getTipoI();

    public String getUbicacion();

    public BigDecimal getCostoAdmin();

    public BigDecimal getNumHabitaciones();

    public BigDecimal getIdOferta();
}