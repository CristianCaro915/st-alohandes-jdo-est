package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public interface VOContrato {

    public long getId();

    public String getTipoContrato();

    public Timestamp getFechaInicio();

    public int getDuracion();

    public int getDuracionPrePaid();

    public int getPrecioEspecial();

    public int getPrecioFinal();

    public int getFechaPago();

    public long getIdOferta();
}