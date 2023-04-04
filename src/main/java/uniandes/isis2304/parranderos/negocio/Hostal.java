package uniandes.isis2304.parranderos.negocio;
import java.util.Date;

public class Hostal implements VOHostal {
    /*
     * **********************
     * Atributos
     ***********************/
    private long id;
    private String nombre;
    private int recepcion;
    private Date horaCierre;
    private Date horaApertura;

    public Hostal() {
        this.id = 0;
        this.nombre = "";
        this.recepcion = 0;
        this.horaApertura = null;
        this.horaCierre = null;
    }

    public Hostal(long id, String nombre, int recepcion, Date Apertura, Date Cierre) {
        this.id = id;
        this.nombre = nombre;
        this.recepcion = recepcion;
        this.horaApertura = Apertura;
        this.horaCierre = Cierre;
    }

    public long getId() {
        return id;
    }
    public String getNombre() {
        return nombre;
    }
    public int getRecepcion() {
        return recepcion;
    }
    public Date getApertura() {
        return horaApertura;
    }
    public Date getCierre() {
        return horaCierre;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void SetApertura(Date aper) {
        this.horaApertura = aper;
    }
    public void SetCierre(Date cierre) {
        this.horaCierre = cierre;
    }
    public void setRecepcion(int num) {
        if (num == 0 || num == 1) {
            this.recepcion = num;
        }
    }
}