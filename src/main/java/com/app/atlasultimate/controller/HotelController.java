package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Date;
import java.sql.Time;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HabitacionServiceImp servicio;

    @Autowired
    private HotelServiceImp servicioHotel;


    @GetMapping("habitacion")
    public String inicio(Model model){
        model.addAttribute("habitaciones", servicio.listarHabitacion());
        return "/AdminHabitaciones.html";
    }

    @GetMapping("nuevo")
    public String nuevoHotel(Model modelo){
        Hotel hotel= new Hotel();
        modelo.addAttribute("hotel", hotel);
        return "/crearhotel.html";
    }

    @PostMapping("nuevo")
    public String guardarHotel(@ModelAttribute("hotel") Hotel hotel){
        servicioHotel.guardarHotel(hotel);
        return "redirect:/hotel/nuevo?exito";
    }
    //cargar hotel en formulario para editarlo
    @GetMapping("/editar/{id}")
    public String editarHotel(@PathVariable Integer id, Model model) {
        model.addAttribute("hotel", servicioHotel.obtenerHotelporId(id));

        return"/editarHotel.html";}

    //Peticionpost para enviar la info cambiada del hotel
    @PostMapping("/editar/{id}")
    public String actualizarHotel(@PathVariable Integer id, @ModelAttribute("hotel") Hotel hotel, Model modelo){
    Hotel hotelexistente = servicioHotel.obtenerHotelporId(id);
    hotelexistente.setId(id);
    hotelexistente.setNombre(hotel.getNombre());
    hotelexistente.setPais(hotel.getPais());
    hotelexistente.setCiudad(hotel.getCiudad());
    hotelexistente.setNumHabitaciones(hotel.getNumHabitaciones());
    hotelexistente.setUbicacion((hotel.getUbicacion()));
    hotelexistente.setEstrellas(hotel.getEstrellas());
    hotelexistente.setListaIdiomas(hotel.getListaIdiomas());
    hotelexistente.setTerraza(hotel.getTerraza());
    hotelexistente.setPiscina(hotel.getPiscina());
    hotelexistente.setPatioInterior(hotel.getPatioInterior());
    hotelexistente.setEspectaculos(hotel.getEspectaculos());
    hotelexistente.setComedor(hotel.getComedor());
    hotelexistente.setTours(hotel.getTours());
    hotelexistente.setAparcamiento((hotel.getAparcamiento()));
    hotelexistente.setServicioTransporte(hotel.getServicioTransporte());
    hotelexistente.setRecepcion(hotel.getRecepcion());
    hotelexistente.setServicioLimpieza(hotel.getServicioLimpieza());
    hotelexistente.setServicioHabitaciones(hotel.getServicioHabitaciones());
    hotelexistente.setMascotas(hotel.getMascotas());
    hotelexistente.setWeb(hotel.getWeb());
    hotelexistente.setTelefono(hotel.getTelefono());
    hotelexistente.setCodigo_postal(hotel.getCodigo_postal());
    hotelexistente.setAccesibilidad(hotel.getAccesibilidad());
    hotelexistente.setFecha_cierre(hotel.getFecha_cierre());
    hotelexistente.setFecha_apertura(hotel.getFecha_apertura());
    hotelexistente.setWifi(hotel.getWifi());
    hotelexistente.setCancelacionGratuita(hotel.getCancelacionGratuita());
    hotelexistente.setPreferenciaPago(hotel.getPreferenciaPago());
    servicioHotel.actualizarHotel(hotelexistente);
    return "redirect:/admin/inicio";

    }


    //cargar habitacion en formulario para editarla

    @GetMapping("/editarhabitacion/{id}")
    public String editarHabitacion(@PathVariable Integer id, Model model) {
        model.addAttribute("habitacion", servicio.obtenerHabitacionporId(id));

        return"/editarHabitacion.html";}

    //Peticionpost para enviar la info cambiada de la habitacion
    @PostMapping("/editarhabitacion/{id}")
    public String actualizarHabitacion (@PathVariable Integer id, @ModelAttribute("habitacion") Habitacion hab, Model modelo){
        Habitacion habitacionexistente = servicio.obtenerHabitacionporId(id);
        habitacionexistente.setId(id);
        habitacionexistente.setDisponible(hab.getDisponible());
        habitacionexistente.setTipo_habitacion(hab.getTipo_habitacion());
        habitacionexistente.setNum_habitacion(hab.getNum_habitacion());
        habitacionexistente.setNum_camas(hab.getNum_camas());
        habitacionexistente.setNum_camas(hab.getNum_camas());
        habitacionexistente.setTipo_cama(hab.getTipo_cama());
        habitacionexistente.setBano(hab.getBano());
        habitacionexistente.setWifi(hab.getWifi());
        habitacionexistente.setPrecio_base(hab.getPrecio_base());
        habitacionexistente.setVistas(hab.getVistas());
        servicio.actualizarHabitacion(habitacionexistente);

        return "redirect:/hotel/habitacion";
    }

    //Eliminar habitacion
    @GetMapping("/habitacion/{id}")
    public String eliminarHab(@PathVariable Integer id){
        servicio.eliminarHabitacion(id);
        return "redirect:/habitacion";
    }

}
