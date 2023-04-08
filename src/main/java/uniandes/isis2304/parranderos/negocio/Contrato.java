package uniandes.isis2304.parranderos.negocio;
import java.sql.Timestamp;

public class Contrato implements VOContrato {

    private long id_C;
    private String tipoContrato;
    private Timestamp fechaInicio;
    private int duracion;
    private int duracionPrePaid;
    private int precioEspecial;
    private int precioFinal;
    private int fechaPago;
    private long id_Oferta;

    public Contrato(long id_C, String tipoContrato, int duracion, int duracionPrePaid,
            int precioEspecial, int precioFinal, Timestamp fechaInicio,int fechaPago, long id_Oferta) {

        this.id_C = id_C;
        this.tipoContrato = tipoContrato;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.duracionPrePaid = duracionPrePaid;
        this.precioEspecial = precioEspecial;
        this.precioFinal = precioFinal;
        this.fechaPago = fechaPago;
        this.id_Oferta=id_Oferta;
    }

    public Contrato() {

        this.id_C = 0L;
        this.tipoContrato = "";
        this.fechaInicio = null;
        this.duracion = 0;
        this.duracionPrePaid = 0;
        this.precioEspecial = 0;
        this.precioFinal = 0;
        this.fechaPago = 0;
        this.id_Oferta = (long) 0;
    }

    public void setId(long id_C) {
        this.id_C = id_C;
    }
    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }
    public void setFechaInicio(Timestamp fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public void setDuracion(int duracion) {
        this.duracion = duracion;
    }
    public void setDuracionPrePaid(int duracionPrePaid) {
        this.duracionPrePaid = duracionPrePaid;
    }
    public void setPrecioEspecial(int precioEspecial) {
        this.precioEspecial = precioEspecial;
    }
    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }
    public void setFechaPago(int fechaPago) {
        this.fechaPago = fechaPago;
    }
    public void setIdOferta(long id_Oferta){

    }
    public long getId() {
        return id_C;
    }
    public String getTipoContrato() {
        return tipoContrato;
    }
    public Timestamp getFechaInicio() {
        return fechaInicio;
    }
    public int getDuracion() {
        return duracion;
    }
    public int getDuracionPrePaid() {
        return duracionPrePaid;
    }
    public int getPrecioEspecial() {
        return precioEspecial;
    }
    public int getPrecioFinal() {
        return precioFinal;
    }
    public int getFechaPago() {
        return fechaPago;
    }
    public long getIdOferta() {
        return id_Oferta;
    }
}