package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;

public interface VOContrato {

    public BigDecimal getId();

    public String getTipoContrato();

    public Timestamp getFechaInicio();

    public int getDuracion();

    public int getDuracionPrePaid();

    public int getPrecioEspecial();

    public int getPrecioFinal();

    public int getFechaPago();

    public BigDecimal getIdOferta();
}