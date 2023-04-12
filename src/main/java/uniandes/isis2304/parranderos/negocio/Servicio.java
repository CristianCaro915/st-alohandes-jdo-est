package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Servicio implements VOServicio {
    private BigDecimal id_Sv;
    private int precio;
    private int incluido;
    private int cantidad;
    private String nombre;

    public Servicio(BigDecimal id_Sv, int precio, int incluido, int cantidad, String nombre) {
        this.id_Sv = id_Sv;
        this.precio = precio;
        this.incluido = incluido;
        this.cantidad = cantidad;
        this.nombre = nombre;
    }

    public Servicio() {
        this.id_Sv = new BigDecimal(0);
        this.precio = 0;
        this.incluido = 0;
        this.cantidad = 0;
        this.nombre = "";
    }

    public BigDecimal getId() {
        return id_Sv;
    }
    public void setId(BigDecimal id) {
        this.id_Sv = id;
    }
    public int getPrecio() {
        return precio;
    }
    public void setPrecio(int precio) {
        this.precio = precio;
    }
    public int getIncluido() {
        return incluido;
    }
    public void setIncluido(int incluido) {
        this.incluido = incluido;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}