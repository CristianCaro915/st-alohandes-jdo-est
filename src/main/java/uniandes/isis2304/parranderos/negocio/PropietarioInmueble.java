package uniandes.isis2304.parranderos.negocio;

public class PropietarioInmueble implements VOPropietarioInmueble {
    private long id_Pi;
    private String nombre;
    private String tipo;
    private String vinculo;

    public PropietarioInmueble() {
        this.id_Pi = 0;
        this.nombre = "";
        this.vinculo = "";
        this.tipo = "";
    }
    public PropietarioInmueble(long id_Pi, String tipo, String nombre, String vinculo) {
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
    public long getIdPi() {
        return this.id_Pi;
    }
    public String getNombre() {
        return this.nombre;
    }
    public String getVinculo() {
        return this.vinculo;
    }
    public void setIdPi(long id_Pi) {
        this.id_Pi = id_Pi;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setVinculo(String vinculo) {
        this.vinculo = vinculo;
    }
}
