package uniandes.isis2304.parranderos.negocio;

public class Brinda implements VOBrinda{
    private long idHabitacion;
    private long idServicioH;
    //SE DEBE EVALUAR QUE UNO SE PUEDA NULL Y OTRO NO NULL. AUNQUE NO AL TIEMPO NULL AMBOS
    public Brinda(long idHabitacion, long idServicioH){
        this.idHabitacion=idHabitacion;
        this.idServicioH=idServicioH;
    }
    public long getIdHabitacion(){
        return this.idHabitacion;
    }
    public long getIdServicioH(){
        return this.idServicioH;
    }
    public void setIdHabitacioon(long idHabitacion){
        this.idHabitacion=idHabitacion;
    }
    public void setIdServicioH(long idServicioH){
        this.idServicioH=idServicioH;
    }
}