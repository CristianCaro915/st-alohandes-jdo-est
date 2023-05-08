package uniandes.isis2304.parranderos.negocio;

import java.math.BigDecimal;

public class Oferta implements VOOferta {
    private BigDecimal id_O;
    private int reservado;
    private BigDecimal id_Cliente;
    private BigDecimal id_Propietario;
    private BigDecimal id_Empresa;
    private BigDecimal id_Hostal;
    private BigDecimal id_Hotel;
    private int disponibilidad;

    public Oferta() {
        this.id_O = new BigDecimal(0);
        this.reservado = 0;
        this.id_Cliente = new BigDecimal(0);
        this.id_Propietario = new BigDecimal(0);
        this.id_Empresa = new BigDecimal(0);
        this.id_Hostal = new BigDecimal(0);
        this.id_Hotel = new BigDecimal(0);
        this.disponibilidad = 0;
    }

    public Oferta(BigDecimal id_O, int reservado, BigDecimal id_Cliente, BigDecimal id_Propietario, 
    BigDecimal id_Empresa, BigDecimal id_Hostal, BigDecimal id_Hotel,int disponibilidad) {
        this.id_O = id_O;
        this.reservado = reservado;
        this.id_Cliente = id_Cliente;
        this.id_Propietario = id_Propietario;
        this.id_Empresa = id_Empresa;
        this.id_Hostal = id_Hostal;
        this.id_Hotel=id_Hotel;
        this.disponibilidad=disponibilidad;
    }
    public BigDecimal getId() {
        return id_O;
    }
    public void setId(BigDecimal id_O) {
        this.id_O = id_O;
    }
    public int getReservado() {
        return reservado;
    }
    public void setReservado(int reservado) {
        this.reservado = reservado;
    }
    public BigDecimal getIdCliente() {
        return id_Cliente;
    }
    public void setIdCliente(BigDecimal id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    public BigDecimal getIdPropietario() {
        return id_Propietario;
    }
    public void setIdPropietario(BigDecimal id_Propietario) {
        this.id_Propietario = id_Propietario;
    }
    public BigDecimal getIdEmpresa() {
        return id_Empresa;
    }
    public void setIdEmpresa(BigDecimal id_Empresa) {
        this.id_Empresa = id_Empresa;
    }
    public BigDecimal getIdHostal() {
        return id_Hostal;
    }
    public void setIdHostal(BigDecimal id_Hostal) {
        this.id_Hostal = id_Hostal;
    }
    public BigDecimal getIdHotel() {
        return id_Hotel;
    }
    public void setIdHotel(BigDecimal id_Hotel) {
        this.id_Hotel = id_Hotel;
    }
    public int getDisponibilidad() {
        return disponibilidad;
    }
    public void setDisponibilidad(int disponibilidad) {
        this.disponibilidad = disponibilidad;
    }
}