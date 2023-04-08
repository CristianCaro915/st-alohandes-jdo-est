package uniandes.isis2304.parranderos.negocio;

public class Hotel implements VOHotel{

    private long id_Ht;
    private String nombre;
    private String Tipo;

    public Hotel() {
        this.id_Ht = 0;
        this.nombre = "";
        this.Tipo = "";
    }

    public Hotel(long id_Ht, String nombre, String tipo) {
        this.id_Ht = id_Ht;
        this.nombre = nombre;
        this.Tipo = tipo;
    }

    public String getTipo() {
        return Tipo;
    }

    public void setTipo(String tipo) {
        this.Tipo = tipo;
    }

    public long getId() {
        return id_Ht;
    }

    public void setId(long id_Ht) {
        this.id_Ht = id_Ht;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}