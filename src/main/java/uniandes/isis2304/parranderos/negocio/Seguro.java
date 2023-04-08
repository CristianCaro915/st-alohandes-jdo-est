package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public class Seguro implements VOSeguro{

    private Long id_Sg;
    private Timestamp fechaVence;
    private String descripcion;
    private Long id_inmueble;

    public Seguro(Long id_Sg, Timestamp fechaVence, String descripcion, Long id_inmueble) {
        this.id_Sg = id_Sg;
        this.fechaVence = fechaVence;
        this.descripcion = descripcion;
        this.id_inmueble = id_inmueble;
    }
    public Seguro() {
        this.id_Sg = (long) 0;
        this.fechaVence = null;
        this.descripcion = "";
        this.id_inmueble = (long) 0;   
    }
    public Long getId() {
        return id_Sg;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public Long getIdInmueble() {
        return id_inmueble;
    }
    public Timestamp getFechaVence() {
        return fechaVence;
    }
    public void setId(Long id_Sg) {
        this.id_Sg = id_Sg;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setId_inmueble(Long id_inmueble) {
        this.id_inmueble = id_inmueble;
    }
    public void setFechaVence(Timestamp fechaVence) {
        this.fechaVence = fechaVence;
    }
}