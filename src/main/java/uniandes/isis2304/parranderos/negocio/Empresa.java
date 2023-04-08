package uniandes.isis2304.parranderos.negocio;

public class Empresa implements VOEmpresa {
    private long id_E;
    private String nombre;
    private String tipo;

    public Empresa(long id_E, String nombre, String tipo) {
        this.id_E = id_E;
        this.nombre = nombre;
        this.tipo = tipo;
    }
    public Empresa() {
        this.id_E = (long) 0;
        this.nombre = "";
        this.tipo = "";
    }
    public String getTipo() {
        return tipo;
    }
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    public long getId() {
        return id_E;
    }
    public String getNombre() {
        return nombre;
    }
    public void setId(long id_E) {
        this.id_E = id_E;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}