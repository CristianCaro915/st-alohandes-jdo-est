package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;

public interface VOSeguro {

    public BigDecimal getId();

    public String getDescripcion();

    public BigDecimal getIdInmueble();

    public Timestamp getFechaVence();



}