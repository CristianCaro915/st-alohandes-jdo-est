package uniandes.isis2304.parranderos.negocio;

public class SujetoComunidad implements VOSujetoComunidad{

    private Long id;
    private String nombre;
    private String Vinculo;

    public SujetoComunidad(Long id, String nombre, String Vinculo) {
        this.id = id;
        this.nombre = nombre;
        this.Vinculo = Vinculo;
    }
    public SujetoComunidad() {
        this.id = 0L;
        this.nombre = "";
        this.Vinculo = "";
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getVinculo() {
        return Vinculo;
    }

    public void setVinculo(String Vinculo) {
        this.Vinculo = Vinculo;
    }

}