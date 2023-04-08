package uniandes.isis2304.parranderos.negocio;

public class Oferta implements VOOferta {
    private long id_O;
    private int reservado;
    private long id_Cliente;
    private long id_Propietario;
    private long id_Empresa;
    private long id_Hostal;
    private long id_Hotel;

    public Oferta() {
        this.id_O = 0L;
        this.reservado = 0;
        this.id_Cliente = 0L;
        this.id_Propietario = 0L;
        this.id_Empresa = 0L;
        this.id_Hostal = 0L;
        this.id_Hotel = 0L;
    }

    public Oferta(Long id_O, int reservado, long id_Cliente, long id_Propietario, 
    long id_Empresa, long id_Hostal, long id_Hotel) {
        this.id_O = id_O;
        this.reservado = reservado;
        this.id_Cliente = id_Cliente;
        this.id_Propietario = id_Propietario;
        this.id_Empresa = id_Empresa;
        this.id_Hostal = id_Hostal;
        this.id_Hotel=id_Hotel;
    }
    public Long getId() {
        return id_O;
    }
    public void setId(Long id_O) {
        this.id_O = id_O;
    }
    public int getReservado() {
        return reservado;
    }
    public void setReservado(int reservado) {
        this.reservado = reservado;
    }
    public long getIdCliente() {
        return id_Cliente;
    }
    public void setIdCliente(long id_Cliente) {
        this.id_Cliente = id_Cliente;
    }
    public long getIdPropietario() {
        return id_Propietario;
    }
    public void setIdPropietario(Long id_Propietario) {
        this.id_Propietario = id_Propietario;
    }
    public long getIdEmpresa() {
        return id_Empresa;
    }
    public void setIdEmpresa(Long id_Empresa) {
        this.id_Empresa = id_Empresa;
    }
    public long getIdHostal() {
        return id_Hostal;
    }
    public void setIdHostal(Long id_Hostal) {
        this.id_Hostal = id_Hostal;
    }
    public long getIdHotel() {
        return id_Hotel;
    }
    public void setIdHotel(Long id_Hotel) {
        this.id_Hotel = id_Hotel;
    }
}