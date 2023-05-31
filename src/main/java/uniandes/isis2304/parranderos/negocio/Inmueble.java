package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Inmueble implements VOInmueble{

    private BigDecimal id_I;
    private String tipoI;
    private String ubicacion;
    private BigDecimal costoAdmin;
    private BigDecimal numHabitaciones;
    private BigDecimal id_Oferta;

    public Inmueble(BigDecimal id_I, String tipoI, String ubicacion, BigDecimal costoAdmin,
    BigDecimal numHabitaciones, BigDecimal id_Oferta) {
        this.id_I = id_I;
        this.tipoI = tipoI;
        this.ubicacion = ubicacion;
        this.costoAdmin = costoAdmin;
        this.numHabitaciones = numHabitaciones;
        this.id_Oferta = id_Oferta;
    }
    public BigDecimal getId() {
        return id_I;
    }
    public void setId(BigDecimal id_I) {
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
    public BigDecimal getCostoAdmin() {
        return costoAdmin;
    }
    public void setCostoAdmin(BigDecimal costoAdmin) {
        this.costoAdmin = costoAdmin;
    }
    public BigDecimal getNumHabitaciones() {
        return numHabitaciones;
    }
    public void setNumHabitaciones(BigDecimal numHabitaciones) {
        this.numHabitaciones = numHabitaciones;
    }
    public BigDecimal getIdOferta() {
        return id_Oferta;
    }
    public void setIdOferta(BigDecimal id_Oferta) {
        this.id_Oferta = id_Oferta;
    }
}