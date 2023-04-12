package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Empresa implements VOEmpresa {
    private BigDecimal id_E;
    private String nombre;
    private String tipo;

    public Empresa(BigDecimal id_E, String nombre, String tipo) {
        this.id_E = id_E;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public Empresa() {
        this.id_E = new BigDecimal(0);
        this.nombre = "";
        this.tipo = "";
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getId() {
        return id_E;
    }
    public String getNombre() {
        return nombre;
    }
    public void setId(BigDecimal id_E) {
        this.id_E = id_E;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}