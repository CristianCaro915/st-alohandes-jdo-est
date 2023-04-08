package uniandes.isis2304.parranderos.negocio;

public class Cliente implements VOCliente{

    private Long id_Cl;
    private String nombre;
    private String correo;
    private String contrasenia;

    public Cliente(Long id_Cl, String nombre, String correo, String contrasenia) {
        this.id_Cl = id_Cl;
        this.nombre = nombre;
        this.correo=correo;
        this.contrasenia=contrasenia;
    }

    public Cliente() {
        this.id_Cl=0L;
        this.nombre="";
        this.correo="";
        this.contrasenia="";
    }

    public Long getId() {
        return this.id_Cl;
    }
    public void setId(Long id_Cl) {
        this.id_Cl = id_Cl;
    }
    public String getNombre() {
        return this.nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public void setCorreo(String correo){
        this.correo=correo;
    }
    public String getCorreo(){
        return this.correo;
    }
    public void setContrasenia(String contrasenia){
        this.contrasenia=contrasenia;
    }
    public String getContrasenia(){
        return this.contrasenia;
    }
    
}