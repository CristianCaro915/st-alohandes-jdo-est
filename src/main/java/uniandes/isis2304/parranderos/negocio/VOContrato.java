package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public interface VOContrato {

    public Long getId();

    public String getTipoContrato();

    public DATE getFechaInicio();

    public int getDuracion();

    public int getDuracionPrePaid();

    public int getPrecioEspecial();

    public int getPrecioFinal();

    public DATE getFechaPagoMensualidad();

    public Long getEmpresaID();

    public Long getPersonaNaturalID();

    public Long getPropietarioId_inmueble();

}