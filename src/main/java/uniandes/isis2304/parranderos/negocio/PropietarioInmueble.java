package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class PropietarioInmueble implements VOPropietarioInmueble {
    private BigDecimal id_Pi;
    private String nombre;
    private String tipo;
    private String vinculo;

    public PropietarioInmueble() {
        this.id_Pi = new BigDecimal(0);
        this.nombre = "";
        this.vinculo = "";
        this.tipo = "";
    }
    public PropietarioInmueble(BigDecimal id_Pi, String tipo, String nombre, String vinculo) {
        this.id_Pi = id_Pi;
        this.nombre = nombre;
        this.vinculo = vinculo;
        this.tipo = tipo;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getIdPi() {
        return this.id_Pi;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getVinculo() {
        return this.vinculo;
    }
    public void setIdPi(BigDecimal id_Pi) {
        this.id_Pi = id_Pi;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
}
