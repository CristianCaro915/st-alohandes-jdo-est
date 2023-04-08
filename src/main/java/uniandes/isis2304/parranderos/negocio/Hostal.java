package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public class Hostal implements VOHostal {
    /*
     * ********
     * Atributos
     *********/
    private long id_Hs;
    private String nombre;
    private String tipo;
    private int recepcion;
    private Timestamp horaCierre;
    private Timestamp horaApertura;

    public Hostal() {
        this.id_Hs = 0;
        this.nombre = "";
        this.tipo = "";
        this.recepcion = 0;
        this.horaApertura = null;
        this.horaCierre = null;
    }

    public Hostal(long id_Hs, String tipo, String nombre, int recepcion, Timestamp Apertura, Timestamp Cierre) {
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
    public long getId() {
        return id_Hs;
    }
    public String getNombre() {
        return nombre;
    }
    public int getRecepcion() {
        return recepcion;
    }
    public Timestamp getApertura() {
        return horaApertura;
    }
    public Timestamp getCierre() {
        return horaCierre;
    }
    public void setId(long id_Hs) {
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
    public void setRecepcion(int num) {
        if (num == 0 || num == 1) {
            this.recepcion = num;
        }
    }
}