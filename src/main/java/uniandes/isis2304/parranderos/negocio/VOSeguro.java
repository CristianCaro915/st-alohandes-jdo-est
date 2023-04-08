package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public interface VOSeguro {

    public Long getId();

    public String getDescripcion();

    public Long getIdInmueble();

    public Timestamp getFechaVence();



}