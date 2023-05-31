package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Habitacion implements VOHabitacion{

    private BigDecimal id_H;
    private BigDecimal tamano;
    private String tipoH;
    private BigDecimal precioFinal;
    private String ubicacion;
    private BigDecimal id_Oferta;
    private BigDecimal id_Contrato;
    private BigDecimal id_Inmueble;

    public Habitacion() {
        this.id_H = new BigDecimal(0);
        this.tamano = new BigDecimal(0);
        this.tipoH = "";
        this.precioFinal = new BigDecimal(0);
        this.ubicacion = "";
        this.id_Oferta = new BigDecimal(0);
        this.id_Contrato = new BigDecimal(0);
        this.id_Inmueble = new BigDecimal(0);
    }

    public Habitacion(BigDecimal id_H, BigDecimal tamano, String tipoH, BigDecimal precioFinal, String ubicacion, 
    BigDecimal id_Oferta, BigDecimal id_Contrato, BigDecimal id_Inmueble) {
        this.id_H = id_H;
        this.tamano = tamano;
        this.tipoH = tipoH;
        this.precioFinal = precioFinal;
        this.ubicacion = ubicacion;
        this.id_Oferta = id_Oferta;
        this.id_Contrato = id_Contrato;
        this.id_Inmueble = id_Inmueble;
    }
    public BigDecimal getIdHabitacion() {
        return id_H;
    }
    public void seIdHabiacion(BigDecimal id_H) {
        this.id_H = id_H;
    }
    public BigDecimal getTamano() {
        return tamano;
    }
    public void setTamano(BigDecimal tamano) {
        this.tamano = tamano;
    }
    public String getTipoH() {
        return tipoH;
    }
    public void setTipoH(String tipoH) {
        this.tipoH = tipoH;
    }
    public BigDecimal getPrecioFinal() {
        return precioFinal;
    }
    public void setPrecioFinal(BigDecimal precioFinal) {
        this.precioFinal = precioFinal;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public BigDecimal getIdOferta() {
        return id_Oferta;
    }
    public void setIdOferta(BigDecimal id_Oferta) {
        this.id_Oferta = id_Oferta;
    }
    public BigDecimal getIdContrato() {
        return id_Contrato;
    }
    public void setIdContrato(BigDecimal id_Contrato) {
        this.id_Contrato = id_Contrato;
    }
    public BigDecimal getIdInmueble() {
        return id_Inmueble;
    }
    public void setIdInmueble(BigDecimal id_Inmueble) {
        this.id_Inmueble=id_Inmueble;
    }

}