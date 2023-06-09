package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Cliente implements VOCliente{

    private BigDecimal id_Cl;
    private String nombre;
    private String correo;
    private String contrasenia;

    public Cliente(BigDecimal id_Cl, String nombre, String correo, String contrasenia) {
        this.id_Cl = id_Cl;
        this.nombre = nombre;
        this.correo=correo;
        this.contrasenia=contrasenia;
    }

    public Cliente() {
        this.id_Cl=new BigDecimal(0);
        this.nombre="";
        this.correo="";
        this.contrasenia="";
    }

    public BigDecimal getId() {
        return this.id_Cl;
    }
    public void setId(BigDecimal id_Cl) {
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