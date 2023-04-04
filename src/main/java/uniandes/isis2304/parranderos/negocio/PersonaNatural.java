package uniandes.isis2304.parranderos.negocio;

public class PersonaNatural implements VOPersonaNatural{
    private long id_PN;
    private String nombre; 
    private String vinculo;

    public PersonaNatural() {
        this.id_PN = 0;
        this.nombre = "";
        this.vinculo= "";
    }
    public PersonaNatural(long id, String nombre,String vinculo) {
        this.id_PN = id;
        this.nombre = nombre;
    }
    public long getId_PN(){
        return this.id_PN;
    }
    public String getNombre(){
        return this.nombre;
    }
    public String getVinculo(){
        return this.vinculo;
    }
    public void setId_PN(long id){
        this.id_PN=id;
    }
    public void setNombre(String nombre){
        this.nombre=nombre;
    }
    public void setVinculo(String vinculo){
        this.vinculo=vinculo;
    }

}
