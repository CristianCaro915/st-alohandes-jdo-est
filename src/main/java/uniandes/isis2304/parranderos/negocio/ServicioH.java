package uniandes.isis2304.parranderos.negocio;

public class ServicioH implements VOServicioH {
    private long id;
    private int precio;
    private int incluido;
    private int cantidad;
    private String nombreS;

    public ServicioH(long id, int precio, int incluido, int cantidad, String nombreS) {
        this.id = id;
        this.precio = precio;
        this.incluido = incluido;
        this.cantidad = cantidad;
        this.nombreS = nombreS;
    }

    public ServicioH() {
        this.id = 0;
        this.precio = 0;
        this.incluido = 0;
        this.cantidad = 0;
        this.nombreS = "";
    }

    public long getId() {
        return id;
    }
    public void setId(long id) {
        this.id = id;
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
    public String getNombreS() {
        return nombreS;
    }
    public void setNombreS(String nombreS) {
        this.nombreS = nombreS;
    }
}