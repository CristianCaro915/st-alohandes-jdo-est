package uniandes.isis2304.parranderos.negocio;

import oracle.sql.DATE;

public interface VOSeguro {

    public Long getId();

    public String getDescripcion();

    public Long getId_inmueble();

    public DATE getFechaVence();



}