package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Brindan implements VOBrindan{
    private BigDecimal id_Habitacion;
    private BigDecimal id_Servicio;
    //SE DEBE EVALUAR QUE UNO SE PUEDA NULL Y OTRO NO NULL. AUNQUE NO AL TIEMPO NULL AMBOS
    public Brindan(BigDecimal idHabitacion, BigDecimal idServicio){
        this.id_Habitacion=idHabitacion;
        this.id_Servicio=idServicio;
    }
    public BigDecimal getIdHabitacion(){
        return this.id_Habitacion;
    }
    public BigDecimal getIdServicioH(){
        return this.id_Servicio;
    }
    public void setIdHabitacioon(BigDecimal id_Habitacion){
        this.id_Habitacion=id_Habitacion;
    }
    public void setIdServicioH(BigDecimal id_Servicio){
        this.id_Servicio=id_Servicio;
    }
}