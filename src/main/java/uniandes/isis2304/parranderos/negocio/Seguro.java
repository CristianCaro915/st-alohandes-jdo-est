package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public class Seguro implements VOSeguro{

    private Long id;
    private DATE fechaVence;
    private String descripcion;
    private Long id_inmueble;

    public Seguro(Long id, DATE fechaVence, String descripcion, Long id_inmueble) {
        this.id = id;
        this.fechaVence = fechaVence;
        this.descripcion = descripcion;
        this.id_inmueble = id_inmueble;
    }

    public Seguro() {
        this.id = (long) 0;
        this.fechaVence = null;
        this.descripcion = "";
        this.id_inmueble = (long) 0;
        
    }

    public Long getId() {
        return id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public Long getId_inmueble() {
        return id_inmueble;
    }

    public DATE getFechaVence() {
        return fechaVence;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setId_inmueble(Long id_inmueble) {
        this.id_inmueble = id_inmueble;
    }

    public void setFechaVence(DATE fechaVence) {
        this.fechaVence = fechaVence;
    }

}