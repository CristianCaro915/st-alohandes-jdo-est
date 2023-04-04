package uniandes.isis2304.parranderos.negocio;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.LinkedList;
import java.util.List;
import uniandes.isis2304.parranderos.persistencia.PersistenciaAlohandes;
import org.apache.log4j.Logger;
import com.google.gson.JsonObject;

public class Alohandes {

    private static Logger log = Logger.getLogger(Parranderos.class.getName());
    private PersistenciaAlohandes pp;

    public Alohandes() {

        pp = PersistenciaAlohandes.getInstance();

    } 

    public void cerrarUnidadPersistencia() {
        pp.cerrarUnidadPersistencia();
    }

    public Hotel adicionarHotel(String nombre, Long id) {
        log.info("Adicionando Hotel: " + nombre);
        Hotel tipoBebida = pp.adicionarHotel(nombre);
        log.info("Adicionando Tipo de bebida: " + tipoBebida);
        return tipoBebida;
    }

    public long eliminarHotelPorNombre(String nombre) {
        log.info("Eliminando Hotel por nombre: " + nombre);
        long resp = pp.eliminarHotelPorNombre(nombre);
        log.info("Eliminando Tipo de bebida por nombre: " + resp + " tuplas eliminadas");
        return resp;
    }

    public long eliminarHotelPorId(long idHotel) {
        log.info("Eliminando Hotel por nombre: " + idHotel);
        long resp = pp.eliminarHotelPorId(idHotel);
        log.info("Eliminando bebedor por Id: " + resp + " tuplas eliminadas");
        return resp;
    }

    public List<Hotel> darHotelesPorNombre(String nombre) {
        log.info("Dar información de Hoteles por nombre: " + nombre);
        List<Hotel> hotels = pp.darBebedoresPorNombre(nombre);
        log.info("Dar información de Bebedores por nombre: " + hotels.size() + " bebedores con ese nombre existentes");
        return hotels;
    }

    public List<VOHotel> darVOHotelPorNombre(String nombre) {
        log.info("Generando VO de bebedores por nombre: " + nombre);
        List<VOHotel> voBebedores = new LinkedList<VOHotel>();
        for (Hotel bdor : pp.darHotelesPorNombre(nombre)) {
            voBebedores.add(bdor);
        }
        log.info("Generando los VO de Bebedores: " + voBebedores.size() + " bebedores existentes");
        return voBebedores;
    }

    // Hostal

    public Hostal adicionarHostal(long id, String nombre, int recepcion, Date Apertura,
            Date Cierre) {
        log.info("Adicionando hostal: " + nombre);
        Hostal hostal = pp.adicionarHostal(id, nombre, recepcion, Apertura, Cierre);
        log.info("Adicionando bar: " + hostal);
        return hostal;
    }

    public long eliminarHostalPorNombre(String nombre) {
        log.info("Eliminando hostal por nombre: " + nombre);
        long resp = pp.eliminarHostalPorNombre(nombre);
        log.info("Eliminando hostal: " + resp + " tuplas eliminadas");
        return resp;
    }

    public long eliminarHostalPorId(long idBar) {
        log.info("Eliminando hostal por id: " + idBar);
        long resp = pp.eliminarHostalPorId(idBar);
        log.info("Eliminando hostal: " + resp);
        return resp;
    }

    public Hostal darHostalPorId(long idHostal) {
        log.info("Dar información de un hostal por id: " + idHostal);
        Hostal hostal = pp.darHostalPorId(idHostal);
        log.info("Buscando hostal por Id: " + hostal != null ? hostal : "NO EXISTE");
        return hostal;

    }

    public List<Hostal> darBebedoresPorNombre(String nombre) {
        log.info("Dar información de bebedores por nombre: " + nombre);
        List<Hostal> bebedores = pp.darHostalesPorNombre(nombre);
        log.info("Dar información de Bebedores por nombre: " + bebedores.size()
                + " bebedores con ese nombre existentes");
        return bebedores;
    }

    public List<Hostal> darBebedores() {
        log.info("Listando  hostales");
        List<Hostal> bebedores = pp.darHostal();
        log.info("Listando Bebedores: " + bebedores.size() + " bebedores existentes");
        return bebedores;
    }

}
