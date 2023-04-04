package uniandes.isis2304.parranderos.negocio;

public class PropietarioInmueble implements VOPropietarioInmueble {
    private long id_PI;
    private String nombre; 
    private String vinculo;

    public PropietarioInmueble() {
        this.id_PI = 0;
        this.nombre = "";
        this.vinculo= "";
    }
    public PropietarioInmueble(long id, String nombre,String vinculo) {
        this.id_PI = id;
        this.nombre = nombre;
    }
    public long getId_PN(){
        return this.id_PI;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getVinculo(){
        return this.vinculo;
    }
    public void setId_PN(long id){
        this.id_PI=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setVinculo(String vinculo){
        this.vinculo=vinculo;
    }

}
