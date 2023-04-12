package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Seguro implements VOSeguro{

    private BigDecimal id_Sg;
    private Timestamp fechaVence;
    private String descripcion;
    private BigDecimal id_inmueble;

    public Seguro(BigDecimal id_Sg, Timestamp fechaVence, String descripcion, BigDecimal id_inmueble) {
        this.id_Sg = id_Sg;
        this.fechaVence = fechaVence;
        this.descripcion = descripcion;
        this.id_inmueble = id_inmueble;
    }
    public Seguro() {
        this.id_Sg = new BigDecimal(0);
        this.fechaVence = null;
        this.descripcion = "";
        this.id_inmueble = new BigDecimal(0);   
    }
    public BigDecimal getId() {
        return id_Sg;
    }
    public String getDescripcion() {
        return descripcion;
    }
    public BigDecimal getIdInmueble() {
        return id_inmueble;
    }
    public Timestamp getFechaVence() {
        return fechaVence;
    }
    public void setId(BigDecimal id_Sg) {
        this.id_Sg = id_Sg;
    }
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    public void setId_inmueble(BigDecimal id_inmueble) {
        this.id_inmueble = id_inmueble;
    }
    public void setFechaVence(Timestamp fechaVence) {
        this.fechaVence = fechaVence;
    }
}