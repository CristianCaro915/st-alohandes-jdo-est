package uniandes.isis2304.parranderos.negocio;


import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

public class Alohandes {

    private static Logger log = Logger.getLogger(Alohandes.class.getName());
    private PersistenciaAlohandes pp;

    public Alohandes() {

        pp = PersistenciaAlohandes.getInstance();

    } 
    public Alohandes (JsonObject tableConfig)
	{
		pp = PersistenciaAlohandes.getInstance (tableConfig);
	}

    public void cerrarUnidadPersistencia() {
        pp.cerrarUnidadPersistencia();
    }
                                                    /* HOTEL */
    public Hotel adicionarHotel(String nombre, String tipo) {
        log.info("Adicionando Hotel: " + nombre);
        Hotel hotel = pp.adicionarHotel(nombre, tipo);
        log.info("Adicionando Hotel: " + hotel);
        return hotel;
    }
    public long eliminarHotelPorNombre(String nombre) {
        log.info("Eliminando Hotel por nombre: " + nombre);
        long resp = pp.eliminarHotelPorNombre(nombre);
        log.info("Eliminando Hotel por nombre: " + resp + " tuplas eliminadas");
        return resp;
    }
    public long eliminarHotelPorId(long idHotel) {
        log.info("Eliminando Hotel por nombre: " + idHotel);
        long resp = pp.eliminarHotelPorId(idHotel);
        log.info("Eliminando Hotel por Id: " + resp + " tuplas eliminadas");
        return resp;
    }
    /*   REVISAR
    public List<VOHotel> darVOHotel ()
	{
		log.info ("Generando los VO de los Hoteles");       
        List<VOHotel> vohotel = new LinkedList<VOHotel> ();
        for (Hotel ht : pp.darHoteles())
        {
        	vohotel.add(ht);
        }
        log.info ("Generando los VO de los Hoteles: " + vohotel.size() + " existentes");
        return vohotel;
	} */
    public Hotel darHotelPorNombre(String nombre) {
        log.info("Dar información de Hotel por nombre: " + nombre);
        Hotel hotel = pp.darHotelPorNombre(nombre);
        log.info("Dar información de Hotel por nombre: " + hotel
                + " Hotel con ese nombre existentes");
        return hotel;
    }
    public List<Hotel> darHoteles() {
        log.info("Listando  Hoteles");
        List<Hotel> hoteles = pp.darHoteles();
        log.info("Listando Hoteles: " + hoteles.size() + " Hoteles existentes");
        return hoteles;
    }
                                                /* HOSTAL */
    public Hostal adicionarHostal(String nombre, int recepcion, Timestamp Apertura,
            Timestamp Cierre, String tipo) {
        log.info("Adicionando hostal: " + nombre);
        Hostal hostal = pp.adicionarHostal(nombre, recepcion, Cierre, Apertura, tipo);
        log.info("Adicionando hostal: " + hostal);
        return hostal;
    }
    public long eliminarHostalPorNombre(String nombre) {
        log.info("Eliminando hostal por nombre: " + nombre);
        long resp = pp.eliminarHostalPorNombre(nombre);
        log.info("Eliminando hostal: " + resp + " tuplas eliminadas");
        return resp;
    }
    public long eliminarHostalPorId(long idH) {
        log.info("Eliminando hostal por id: " + idH);
        long resp = pp.eliminarHostalPorId(idH);
        log.info("Eliminando hostal: " + resp);
        return resp;
    }
    public List<VOHostal> darVOHostales()
	{
		log.info ("Generando los VO de las Hostales");       
        List<VOHostal> voHostales = new LinkedList<VOHostal> ();
        for (Hostal hst : pp.darHostales())
        {
        	voHostales.add (hst);
        }
        log.info ("Generando los VO de las Hostales: " + voHostales.size() + " existentes");
        return voHostales;
	}
    public List<Hostal> darHostalesPorNombre(String nombre) {
        log.info("Dar información de Hostales por nombre: " + nombre);
        List<Hostal> hostales = pp.darHostalesPorNombre(nombre);
        log.info("Dar información de Hostales por nombre: " + hostales.size()
                + " Hostal con ese nombre existentes");
        return hostales;
    }
    public List<Hostal> darHostales() {
        log.info("Listando  hostales");
        List<Hostal> hostales = pp.darHostales();
        log.info("Listando Hostales: " + hostales.size() + " hostales existentes");
        return hostales;
    }
                                /* HABITACION */
    public Habitacion adicionarHabitacion(int tamano, String tipoH, int precioFinal,
            String ubicacion, long id_Oferta, long id_Contrato, long id_Inmueble) {
        log.info("Adicionando habitacion de tipo " + tipoH);
        Habitacion resp = pp.adicionarHabitacion(tamano, tipoH, precioFinal, ubicacion,id_Oferta,id_Contrato,id_Inmueble );
        log.info("adicionando habitacion por nombre: " + resp);
        return resp;
    }
    public long eliminarHabitacionId(Long id) {

        log.info("Eliminando habitacion por id: " + id);
        long resp = pp.eliminarHabitacionPorId(id);
        log.info("Eliminando habitacion por id: " + id + " tuplas eliminadas");
        return resp;
    }
    public List<Habitacion> darHabitaciones() {
        log.info("Consultando Habitaciones");
        List<Habitacion> Habitacion = pp.darHabitaciones();
        log.info("Consultando Habitaciones: " + Habitacion.size() + " bebidas existentes");
        return Habitacion;
    }
    public List<Habitacion> darHabitacionesporUbicacion(String ubicacion) {
        log.info("Consultando Habitaciones por ubicaciones");
        List<Habitacion> Habitacion = pp.darHabitacionesPorUbicacion(ubicacion);
        log.info("Consultando Habitaciones: " + Habitacion.size() + " bebidas existentes");
        return Habitacion;
    }
    public Habitacion darHabitacionPorId(Long id) {

        log.info("Dar información de una habitacion por id: " + id);
        Habitacion hab = pp.darHabitacionPorId(id);
        log.info("Buscando habitacion por Id: " + hab != null ? hab : "NO EXISTE");
        return hab;
    }
                                        /* SERVICIO */
    public Servicio adicionarservicio(int precio, int incluido, int cantidad, String nombre) {
        log.info("Adicionando servicio " + nombre);
        Servicio resp = pp.adicionarServicio(precio, incluido, cantidad, nombre);
        log.info("Adicionando servicio por nombre: " + resp);
        return resp;
    }
    public long eliminarServicPorID(long idH) {
        log.info("Eliminando servicio por id: " + idH);
        long resp = pp.eliminarServicioPorId(idH);
        log.info("Eliminando servicio por id: " + idH + " tuplas eliminadas");
        return resp;
    }
    public long eliminarServicioPorNombre(String nombre) {
        log.info("Eliminando servicio por nombre: " + nombre);
        long resp = pp.eliminarServicioPorNombre(nombre);
        log.info("Eliminando servicio por nombre: " + resp + " tuplas eliminadas");
        return resp;
    }
    public List<Servicio> darServicios() {
        log.info("Consultando servicios");
        List<Servicio> servicios = pp.darServicios();
        log.info("Consultando servicios: " + servicios.size() + " servicios existentes");
        return servicios;
    }
    public List<Servicio> darServiciosporInclusion(int inclusion) {
        log.info("Consultando servicios por inclusion");
        List<Servicio> servicios = pp.darServicioPorInclusion(inclusion);
        log.info("Consultando servicios incluidos: " + servicios.size() + " servicios existentes");
        return servicios;
    }
    public Servicio darServicioporId(long idSH) {
        log.info("Dar información de servicio por id: " + idSH);
        Servicio hab = pp.darServicioPorId(idSH);
        log.info("Buscando servicio por Id: " + hab != null ? hab : "NO EXISTE");
        return hab;
    }
    public Servicio darServiciosporNombre(String nombre) {
        log.info("Consultando servicios por nombre" + nombre);
        Servicio servicios = pp.darServicioPorNombre(nombre);
        log.info("Buscando servicio por Id: " + servicios != null ? servicios : "NO EXISTE");
        return servicios;
    }
                                    /* CONTRATO */
    public Contrato adicionarContrato(String tipoContrato, Timestamp fechaInicio, int duracion, int duracionPrePaid, int precioEspecial, int precioFinal, int fechaPago, long id_Oferta)
    {
        log.info ("Adicionando contrato con fecha de inicio" + fechaInicio);
        Contrato resp = pp.adicionarContrato(tipoContrato,fechaInicio,duracion,duracionPrePaid,precioEspecial,precioFinal,fechaPago, id_Oferta);
        log.info ("Adicionando contrato con fecha de inicio: " + resp );
        return resp;
    }
    public long eliminarContratoPorID(long idC) {
        log.info("Eliminando contrato por id: " + idC);
        long resp = pp.eliminarContratoPorId(idC);
        log.info("Eliminando contrato por id: " + idC + " tuplas eliminadas");
        return resp;
    }
    public List<Contrato> darContratos() {
        log.info("Consultando Contrato");
        List<Contrato> servicios = pp.darContratos();
        log.info("Consultando Contrato: " + servicios.size() + " servicios existentes");
        return servicios;
    }
    public List<Contrato> darContratosportipo(String tipoC) {
        log.info("Consultando Contraton por  tipo");
        List<Contrato> servicios = pp.darContratoPorTipoContrato(tipoC);
        log.info("Consultando Contrato: " + servicios.size() + " servicios existentes");
        return servicios;
    }
                                        /* EMPRESA */
    public Empresa adicionarEmpresa(String nombre, String tipo) 
    {
    log.info("Adicionando empresa: " + nombre);
    Empresa empresa = pp.adicionarEmpresa(nombre, tipo);
    log.info("Adicionando empresa: " + empresa);
    return empresa;
    }
    public long eliminarEmpresaPorNombre (String nombre)
	{
        log.info ("Eliminando Empresa por nombre: " + nombre);
        long resp = pp.eliminarEmpresaPorNombre(nombre);
        log.info ("Eliminando Empresa por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
    public long eliminarEmpresaPorId (long idE)
	{
        log.info ("Eliminando Empresa por id: " + idE);
        long resp = pp.eliminarEmpresaPorId(idE);
        log.info ("Eliminando Empresa por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    public List<Empresa> darEmpresas ()
	{
        log.info ("Consultando empresas");
        List<Empresa> empresas = pp.darEmpresas();	
        log.info ("Consultando empresas: " + empresas.size() + " existentes");
        return empresas;
	}
    public List<VOEmpresa> darVOEmpresas ()
	{
		log.info ("Generando los VO de las Empresa");       
        List<VOEmpresa> voEmpresas = new LinkedList<VOEmpresa> ();
        for (Empresa pe : pp.darEmpresas())
        {
        	voEmpresas.add (pe);
        }
        log.info ("Generando los VO de las empresas: " + voEmpresas.size() + " existentes");
        return voEmpresas;
	}
    public Empresa darEmpresaPorId (long idE)
	{
        log.info ("Dar información de una PersonaNatural por id: " + idE);
        Empresa empresa = pp.darEmpresaPorId(idE);
        log.info ("Buscando PersonaNatural por Id: " + empresa != null ? empresa : "NO EXISTE");
        return empresa;
	}
	public List<Empresa> darEmpresasPorNombre (String nombre)
	{
        log.info ("Dar información de empresas por nombre: " + nombre);
        List<Empresa> empresa = pp.darEmpresasPorNombre(nombre);
        log.info ("Dar información de empresas por nombre: " + empresa.size() + " empresas con ese nombre existentes");
        return empresa;
 	}
                                    /* Propietario Inmueble */
    public PropietarioInmueble adicionarPropietarioInmueble(String nombre, String vinculo,String tipo) 
    {
    log.info("Adicionando PropietarioInmueble: " + nombre);
    PropietarioInmueble propietarioP = pp.adicionarPropietarioInmueble(nombre,vinculo,tipo);
    log.info("Adicionando PropietarioInmueble: " + propietarioP);
    return propietarioP;
    }
    public long eliminarpropietarioPPorNombre (String nombre)
	{
        log.info ("Eliminando PropietarioInmueble por nombre: " + nombre);
        long resp = pp.eliminarPropietarioInmueblePorNombre(nombre);
        log.info ("Eliminando PropietarioInmueble por nombre: " + resp + " tuplas eliminadas");
        return resp;
	}
    public long eliminarPropietarioInmueblePorId (long idPI)
	{
        log.info ("Eliminando Empresa por id: " + idPI);
        long resp = pp.eliminarPropietarioInmueblePorId(idPI);
        log.info ("Eliminando Empresa por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    public List<PropietarioInmueble> darPropietariosInmueble ()
	{
        log.info ("Consultando Propietarios Inmueble");
        List<PropietarioInmueble> propietariosInmueble = pp.darPropietariosInmueble();	
        log.info ("Consultando Propietarios Inmueble: " + propietariosInmueble.size() + " existentes");
        return propietariosInmueble;
	}
    public List<VOPropietarioInmueble> darVOPropietarioInmueble ()
	{
		log.info ("Generando los VO de las PropietarioInmueble");       
        List<VOPropietarioInmueble> voPropietarioInmueble = new LinkedList<VOPropietarioInmueble> ();
        for (PropietarioInmueble pI : pp.darPropietariosInmueble())
        {
        	voPropietarioInmueble.add (pI);
        }
        log.info ("Generando los VO de las empresas: " + voPropietarioInmueble.size() + " existentes");
        return voPropietarioInmueble;
	}
    public PropietarioInmueble darPropietarioInmueblePorId (long idPI)
	{
        log.info ("Dar información de una PropietarioInmueble por id: " + idPI);
        PropietarioInmueble propietarioInmueble = pp.darPropietarioInmueblePorId(idPI);
        log.info ("Buscando PropietarioInmueble por Id: " + propietarioInmueble != null ? propietarioInmueble : "NO EXISTE");
        return propietarioInmueble;
	}
	public List<PropietarioInmueble> darPropietarioInmueblePorNombre (String nombre)
	{
        log.info ("Dar información de empresas por nombre: " + nombre);
        List<PropietarioInmueble> propietarioInmueble = pp.darPropietarioInmueblePorNombre(nombre);
        log.info ("Dar información de empresas por nombre: " + propietarioInmueble.size() + " empresas con ese nombre existentes");
        return propietarioInmueble;
 	}
                                    /* Inmueble */
    public Inmueble adicionarInmueble(String tipoI, String ubicacion, int costoAdmin,int numHabitaciones,long id_Oferta) 
    {
    log.info("Adicionando Contrato de tipo: " + tipoI);
    Inmueble inmueble = pp.adicionarInmueble(tipoI,ubicacion,costoAdmin,numHabitaciones,id_Oferta);
    log.info("Adicionando Contrato: " + inmueble);
    return inmueble;
    }
    public long eliminarInmueblePorId (long idI)
	{
        log.info ("Eliminando Inmueble por id: " + idI);
        long resp = pp.eliminarInmueblePorId(idI);
        log.info ("Eliminando Inmueble por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    public List<Inmueble> darInmuebles ()
	{
        log.info ("Consultando Inmuebles");
        List<Inmueble> inmuebles = pp.darInmuebles();	
        log.info ("Consultando Inmuebles: " + inmuebles.size() + " existentes");
        return inmuebles;
	}
    public List<VOInmueble> darVOInmueble ()
	{
		log.info ("Generando los VO de los Inmuebles");       
        List<VOInmueble> voInmueble = new LinkedList<VOInmueble> ();
        for (Inmueble I : pp.darInmuebles())
        {
        	voInmueble.add (I);
        }
        log.info ("Generando los VO de las empresas: " + voInmueble.size() + " existentes");
        return voInmueble;
	}
	public List<Inmueble> darInmueblesPorNombre (int numHabitaciones)
	{
        log.info ("Dar información de Inmuebles por numHabitaciones: " + numHabitaciones);
        List<Inmueble> inmuebles = pp.darInmueblesPorNumHabitaciones(numHabitaciones);
        log.info ("Dar información de Inmuebles por numHabitaciones: " + inmuebles.size() + " empresas con ese nombre existentes");
        return inmuebles;
 	}
     public List<Inmueble> darInmueblesPorUbicacion (String ubicacion)
     {
         log.info ("Dar información de Inmuebles por ubicacion: " + ubicacion);
         List<Inmueble> inmuebles = pp.darInmueblesPorUbicacion(ubicacion);
         log.info ("Dar información de Inmuebles por ubicacion: " + inmuebles.size() + " empresas con ese nombre existentes");
         return inmuebles;
      }

    /* SEGURO */
    public Seguro adicionarSeguro(Timestamp fechaVence, String descripcion, long id_Inmueble) 
    {
    log.info("Adicionando seguro : " + fechaVence);
    Seguro seguro = pp.adicionarSeguro(fechaVence, descripcion, id_Inmueble);
    log.info("Adicionando seguro: " + seguro);
    return seguro;
    }
    public long eliminarSeguroPorId (long idI)
	{
        log.info ("Eliminando seguro por id: " + idI);
        long resp = pp.eliminarSeguroPorId(idI);
        log.info ("Eliminando seguro por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    /* CLIENTE */
    public Cliente adicionarCliente(String nombre, String correo, String contrasenia) 
    {
    log.info("Adicionando Cliente: " + nombre);
    Cliente cliente = pp.adicionarCliente(nombre, correo, contrasenia);
    log.info("Adicionando Cliente: " + cliente);
    return cliente;
    }
    public Cliente darClientePorCorreoContrasenia (String correo, String contrasenia)
	{
        log.info ("Dar información de una Cliente por correo: " + correo);
        Cliente cliente = pp.darClientePorCorreoContrasenia(correo, contrasenia);
        log.info ("Buscando Cliente por correo: " + correo != null ? correo : "NO EXISTE");
        return cliente;
	}
    public long eliminarClientePorId (long idI)
	{
        log.info ("Eliminando Cliente por id: " + idI);
        long resp = pp.eliminarClientePorId(idI);
        log.info ("Eliminando Cliente por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    /* OFERTA */
    public Oferta adicionarOferta(int reservado, long id_Cliente, long id_PropietarioI, long id_Empresa, long id_Hostal, long id_Hotel) 
    {
    log.info("Adicionando oferta : ");
    Oferta oferta = pp.adicionarOferta(reservado, id_Cliente, id_PropietarioI, id_Empresa, id_Hostal, id_Hotel);
    log.info("Adicionando oferta: " + oferta);
    return oferta;
    }
    public long eliminarOfertaPorId (long idI)
	{
        log.info ("Eliminando oferta por id: " + idI);
        long resp = pp.eliminarOfertaPorId(idI);
        log.info ("Eliminando oferta por id: " + resp + " tuplas eliminadas");
        return resp;
	}
    /* BRINDAN */
    public Brindan adicionarBrindan(long id_Habitacion, long id_Servicio) 
    {
    log.info("Adicionando brindan : " + id_Habitacion +" "+id_Servicio);
    Brindan brindan = pp.adicionarBrindan(id_Habitacion, id_Servicio);
    log.info("Adicionando brindan: " + brindan);
    return brindan;
    }
    public long eliminarBrindanPorIds (long id_Habitacion, long id_Servicio)
	{
        log.info ("Eliminando Brindan por id: " + id_Habitacion +" "+id_Servicio);
        long resp = pp.eliminarBrindanPorIds(id_Habitacion, id_Servicio);
        log.info ("Eliminando Brindan por id: " + resp + " tuplas eliminadas");
        return resp;
	}

    public long [] limpiarAlohandes ()
	{
        log.info ("Limpiando la BD de Parranderos");
        long [] borrrados = pp.limpiarAlohandes();	
        log.info ("Limpiando la BD de Parranderos: Listo!");
        return borrrados;
	}
}
