package uniandes.isis2304.parranderos.negocio;

public class Inmueble implements VOInmueble{

    private Long id;
    private String TipoI;
    private String Ubicacion;
    private int costoAdmin;
    private int disponibilidad;
    private int numHabitaciones;
    private Long id_propietario;
    private Long id_sujetoComunidad;

    public Inmueble(Long id, String TipoI, String Ubicacion, int costoAdmin, int disponibilidad,
            int numHabitaciones, Long id_propietario, Long id_sujetoComunidad) {
        this.id = id;
        this.TipoI = TipoI;
        this.Ubicacion = Ubicacion;
        this.costoAdmin = costoAdmin;
        this.disponibilidad = disponibilidad;
        this.numHabitaciones = numHabitaciones;
        this.id_propietario = id_propietario;
        this.id_sujetoComunidad = id_sujetoComunidad;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoI() {
        return TipoI;
    }

    public void setTipoI(String TipoI) {
        this.TipoI = TipoI;
    }

    public String getUbicacion() {
        return Ubicacion;
    }

    public void setUbicacion(String Ubicacion) {
        this.Ubicacion = Ubicacion;
    }

    public int getCostoAdmin() {
        return costoAdmin;
    }

    public void setCostoAdmin(int costoAdmin) {
        this.costoAdmin = costoAdmin;
    }

    public int getDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    public int getNumHabitaciones() {
        return numHabitaciones;
    }

    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }

    public Long getId_propietario() {
        return id_propietario;
    }

    public void setId_propietario(Long id_propietario) {
        this.id_propietario = id_propietario;
    }

    public Long Get_id_sujetoComunidad() {
        return id_sujetoComunidad;
    }

    public void Get_id_sujetoComunidad(Long id_com) {
        this.id_sujetoComunidad = id_com;

    }

}