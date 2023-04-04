package uniandes.isis2304.parranderos.negocio;

public class Empresa implements VOEmpresa{
    private Long id;
    private String Nombre;

    public Empresa(Long id, String Nombre) {
        this.id = id;
        this.Nombre = Nombre;
    }

    public Empresa() {
        this.id = (long) 0;
        this.Nombre = "";
    }

    public Long getId() {
        return id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

}