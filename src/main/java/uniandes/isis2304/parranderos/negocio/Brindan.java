package uniandes.isis2304.parranderos.negocio;

public class Brindan implements VOBrindan {
    private Long id_habitacion;
    private Long ServicioH_id;

    public Brindan(Long id_habitacion, Long ServicioH_id) {
        this.id_habitacion = id_habitacion;
        this.ServicioH_id = ServicioH_id;
    }
    public Long getId_habitacion() {
        return id_habitacion;
    }
    public void setId_habitacion(Long id_habitacion) {
        this.id_habitacion = id_habitacion;
    }
    public Long getServicioH_id() {
        return ServicioH_id;
    }
    public void setServicioH_id(Long ServicioH_id) {
        this.ServicioH_id = ServicioH_id;
    }
}