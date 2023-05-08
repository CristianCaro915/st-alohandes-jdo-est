package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public interface VOOferta {

    public BigDecimal getId();
    public int getReservado();
    public BigDecimal getIdCliente();
    public BigDecimal getIdPropietario();
    public BigDecimal getIdEmpresa();
    public BigDecimal getIdHostal();
    public BigDecimal getIdHotel();
    public int getDisponibilidad();
}