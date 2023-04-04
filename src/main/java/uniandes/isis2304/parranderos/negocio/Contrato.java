package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public class Contrato implements VOContrato {

    private Long id;
    private String tipoContrato;
    private DATE fechaInicio;
    private int duracion;
    private int duracionPrePaid;
    private int precioEspecial;
    private int precioFinal;
    private DATE fechaPagoMensualidad;
    private Long EmpresaID;
    private Long PersonaNaturalID;
    private Long PropietarioId_inmueble;

    public Contrato(Long id, String tipoContrato, DATE fechaInicio, int duracion, int duracionPrePaid,
            int precioEspecial, int precioFinal, DATE fechaPagoMensualidad,
            Long EmpresaID, Long PersonaNaturalID, Long PropietarioId_inmueble) {

        this.id = id;
        this.tipoContrato = tipoContrato;
        this.fechaInicio = fechaInicio;
        this.duracion = duracion;
        this.duracionPrePaid = duracionPrePaid;
        this.precioEspecial = precioEspecial;
        this.precioFinal = precioFinal;
        this.fechaPagoMensualidad = fechaPagoMensualidad;
        this.EmpresaID = EmpresaID;
        this.PersonaNaturalID = PersonaNaturalID;
        this.PropietarioId_inmueble = PropietarioId_inmueble;
    }

    public Contrato() {

        this.id = 0L;
        this.tipoContrato = "";
        this.fechaInicio = null;
        this.duracion = 0;
        this.duracionPrePaid = 0;
        this.precioEspecial = 0;
        this.precioFinal = 0;
        this.fechaPagoMensualidad = null;
        this.EmpresaID = (long) 0;
        this.PersonaNaturalID = (long) 0;
        this.PropietarioId_inmueble = (long) 0;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTipoContrato(String tipoContrato) {
        this.tipoContrato = tipoContrato;
    }

    public void setFechaInicio(DATE fechaInicio) {
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

    public void setFechaPagoMensualidad(DATE fechaPagoMensualidad) {
        this.fechaPagoMensualidad = fechaPagoMensualidad;
    }

    public void setEmpresaID(Long empresaID) {
        EmpresaID = empresaID;
    }

    public void setPersonaNaturalID(Long personaNaturalID) {
        PersonaNaturalID = personaNaturalID;
    }

    public void setPropietarioId_inmueble(Long propietarioId_inmueble) {
        PropietarioId_inmueble = propietarioId_inmueble;
    }

    public Long getId() {
        return id;
    }

    public String getTipoContrato() {
        return tipoContrato;
    }

    public DATE getFechaInicio() {
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

    public DATE getFechaPagoMensualidad() {
        return fechaPagoMensualidad;
    }

    public Long getEmpresaID() {
        return EmpresaID;
    }

    public Long getPersonaNaturalID() {
        return PersonaNaturalID;
    }

    public Long getPropietarioId_inmueble() {
        return PropietarioId_inmueble;
    }

}