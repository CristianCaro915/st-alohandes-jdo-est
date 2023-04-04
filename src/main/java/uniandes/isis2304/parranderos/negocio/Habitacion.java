package uniandes.isis2304.parranderos.negocio;

public class Habitacion implements VOHabitacion{

    private long numHabitacion;
    private int tamano;
    private String tipoH;
    private int precioFinal;
    private String Ubicacion;
    private long HostalID;
    private long HotelId;
    private long ContratoId;
    private long InmuebleId;

    public Habitacion() {
        this.numHabitacion = 0;
        this.tamano = 0;
        this.tipoH = "";
        this.precioFinal = 0;
        this.Ubicacion = "";
        this.HotelId = 0;
        this.ContratoId = 0;
        this.InmuebleId = 0;
        this.HostalID = 0;
    }

    public Habitacion(long numHabitacion, int tamano, String tipoH, int precioFinal, String ubicacion, long hostalID,
    long hotelId, long contratoId, long inmuebleId) {
        this.numHabitacion = numHabitacion;
        this.tamano = tamano;
        this.tipoH = tipoH;
        this.precioFinal = precioFinal;
        this.Ubicacion = ubicacion;
        this.HostalID = hostalID;
        this.HotelId = hotelId;
        this.ContratoId = contratoId;
        this.InmuebleId = inmuebleId;
    }

    public long getNumHabitacion() {
        return numHabitacion;
    }

    public void setNumHabitacion(long numHabitacion) {
        this.numHabitacion = numHabitacion;
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
        return Ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.Ubicacion = ubicacion;
    }

    public long getHostalID() {
        return HostalID;
    }

    public void setHostalID(long hostalID) {
        this.HostalID = hostalID;
    }

    public long getHotelId() {
        return HotelId;
    }

    public void setHotelId(long hotelId) {
        this.HotelId = hotelId;
    }

    public long getContratoId() {
        return ContratoId;
    }

    public void setContratoId(long contratoId) {
        this.ContratoId = contratoId;
    }

    public long getInmuebleId() {
        return InmuebleId;
    }

    public void setInmuebleId(long inmuebleId) {
        this.InmuebleId = inmuebleId;
    }

}