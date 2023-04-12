package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOHabitacion {

    public BigDecimal getIdHabitacion();

    public int getTamano();

    public String getTipoH();

    public int getPrecioFinal();

    public String getUbicacion();

    public BigDecimal getIdOferta();

    public BigDecimal getIdContrato();

    public BigDecimal getIdInmueble();

}