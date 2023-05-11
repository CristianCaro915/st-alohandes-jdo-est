package uniandes.isis2304.parranderos.negocio;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class Hostal implements VOHostal {
    /*
     * ********
     * Atributos
     *********/
    private BigDecimal id_Hs;
    private String nombre;
    private String tipo;
    private BigDecimal recepcion;
    private Timestamp horaCierre;
    private Timestamp horaApertura;

    public Hostal() {
        this.id_Hs = new BigDecimal(0);
        this.nombre = "";
        this.tipo = "";
        this.recepcion = new BigDecimal(0);
        this.horaApertura = null;
        this.horaCierre = null;
    }

    public Hostal(BigDecimal id_Hs, String nombre, BigDecimal recepcion, Timestamp Apertura, Timestamp Cierre, String tipo) {
        this.id_Hs = id_Hs;
        this.nombre = nombre;
        this.tipo = tipo;
        this.recepcion = recepcion;
        this.horaApertura = Apertura;
        this.horaCierre = Cierre;
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public BigDecimal getId() {
        return id_Hs;
    }
    public String getNombre() {
        return nombre;
    }
    public BigDecimal getRecepcion() {
        return recepcion;
    }
    public Timestamp getApertura() {
        return horaApertura;
    }
    public Timestamp getCierre() {
        return horaCierre;
    }
    public void setId(BigDecimal id_Hs) {
        this.id_Hs = id_Hs;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void SetApertura(Timestamp aper) {
        this.horaApertura = aper;
    }
    public void SetCierre(Timestamp cierre) {
        this.horaCierre = cierre;
    }
    public void setRecepcion(BigDecimal num) {
        BigDecimal num1=BigDecimal.valueOf(1);
        BigDecimal num0=BigDecimal.valueOf(0);
        if (num == num1 || num == num0) {
            this.recepcion = num;
        }
    }
}