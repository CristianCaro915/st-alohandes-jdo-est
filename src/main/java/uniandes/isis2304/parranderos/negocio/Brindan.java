package uniandes.isis2304.parranderos.negocio;

public class Brindan implements VOBrindan{
    private long id_Habitacion;
    private long id_Servicio;
    //SE DEBE EVALUAR QUE UNO SE PUEDA NULL Y OTRO NO NULL. AUNQUE NO AL TIEMPO NULL AMBOS
    public Brindan(long idHabitacion, long idServicio){
        this.id_Habitacion=idHabitacion;
        this.id_Servicio=idServicio;
    }
    public long getIdHabitacion(){
        return this.id_Habitacion;
    }
    public long getIdServicioH(){
        return this.id_Servicio;
    }
    public void setIdHabitacioon(long id_Habitacion){
        this.id_Habitacion=id_Habitacion;
    }
    public void setIdServicioH(long id_Servicio){
        this.id_Servicio=id_Servicio;
    }
}