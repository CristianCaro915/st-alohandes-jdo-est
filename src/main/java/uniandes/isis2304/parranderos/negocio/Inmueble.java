package uniandes.isis2304.parranderos.negocio;

public class Inmueble implements VOInmueble{

    private Long id_I;
    private String tipoI;
    private String ubicacion;
    private int costoAdmin;
    private int numHabitaciones;
    private Long id_Oferta;

    public Inmueble(Long id_I, String tipoI, String ubicacion, int costoAdmin,
            int numHabitaciones, Long id_Oferta) {
        this.id_I = id_I;
        this.tipoI = tipoI;
        this.ubicacion = ubicacion;
        this.costoAdmin = costoAdmin;
        this.numHabitaciones = numHabitaciones;
        this.id_Oferta = id_Oferta;
    }
    public Long getId() {
        return id_I;
    }
    public void setId(Long id_I) {
        this.id_I = id_I;
    }
    public String getTipoI() {
        return tipoI;
    }
    public void setTipoI(String tipoI) {
        this.tipoI = tipoI;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public int getCostoAdmin() {
        return costoAdmin;
    }
    public void setCostoAdmin(int costoAdmin) {
        this.costoAdmin = costoAdmin;
    }
    public int getNumHabitaciones() {
        return numHabitaciones;
    }
    public void setNumHabitaciones(int numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }
    public Long getIdOferta() {
        return id_Oferta;
    }
    public void setIdOferta(Long id_Oferta) {
        this.id_Oferta = id_Oferta;
    }
}