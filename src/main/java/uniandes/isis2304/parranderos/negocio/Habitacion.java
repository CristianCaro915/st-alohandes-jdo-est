package uniandes.isis2304.parranderos.negocio;

public class Habitacion implements VOHabitacion{

    private long id_H;
    private int tamano;
    private String tipoH;
    private int precioFinal;
    private String ubicacion;
    private long id_Oferta;
    private long id_Contrato;
    private long id_Inmueble;

    public Habitacion() {
        this.id_H = 0;
        this.tamano = 0;
        this.tipoH = "";
        this.precioFinal = 0;
        this.ubicacion = "";
        this.id_Oferta = 0;
        this.id_Contrato = 0;
        this.id_Inmueble = 0;
    }

    public Habitacion(long id_H, int tamano, String tipoH, int precioFinal, String ubicacion, 
    long id_Oferta, long id_Contrato, long id_Inmueble) {
        this.id_H = id_H;
        this.tamano = tamano;
        this.tipoH = tipoH;
        this.precioFinal = precioFinal;
        this.ubicacion = ubicacion;
        this.id_Oferta = id_Oferta;
        this.id_Contrato = id_Contrato;
        this.id_Inmueble = id_Inmueble;
    }
    public long getIdHabitacion() {
        return id_H;
    }
    public void seIdHabiacion(long id_H) {
        this.id_H = id_H;
    }
    public int getTamano() {
        return tamano;
    }
    public void setTamano(int tamano) {
        this.tamano = tamano;
    }
    public String getTipoH() {
        return tipoH;
    }
    public void setTipoH(String tipoH) {
        this.tipoH = tipoH;
    }
    public int getPrecioFinal() {
        return precioFinal;
    }
    public void setPrecioFinal(int precioFinal) {
        this.precioFinal = precioFinal;
    }
    public String getUbicacion() {
        return ubicacion;
    }
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    public long getIdOferta() {
        return id_Oferta;
    }
    public void setIdOferta(long id_Oferta) {
        this.id_Oferta = id_Oferta;
    }
    public long getIdContrato() {
        return id_Contrato;
    }
    public void setIdContrato(long id_Contrato) {
        this.id_Contrato = id_Contrato;
    }
    public long getIdInmueble() {
        return id_Inmueble;
    }
    public void setIdInmueble(long id_Inmueble) {
        this.id_Inmueble=id_Inmueble;
    }

}