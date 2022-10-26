package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HabitacionServiceImp servicio;

    @Autowired
    private HotelServiceImp servicioHotel;

    @Autowired
    private HabitacionController habcontroller;


    @GetMapping("/habitacion/{id}")
    public String leerHabitaciones(@PathVariable Integer id,Model model, Model model2){
        model.addAttribute("habitaciones", servicio.listarHabitacionbyIdHotel(id));
        model2.addAttribute("hotel", servicioHotel.obtenerHotelporId(id));
        return "/AdminHabitaciones.html";
    }

    @GetMapping("nuevo")
    public String nuevoHotel(Model modelo){
        Hotel hotel= new Hotel();
        modelo.addAttribute("hotel", hotel);
        return "/crearhotel.html";
    }
    //crear hoteles
    @PostMapping("nuevo")
    public String guardarHotel(@ModelAttribute("hotel") Hotel hotel){
        chequearBoolean(hotel);
        servicioHotel.guardarHotel(hotel);
        return "redirect:/hotel/nuevo?exito";
    }

    //cargar hotel en formulario para editarlo
    @GetMapping("/editar/{id}")
    public String editarHotel(@PathVariable Integer id, Model model) {
        model.addAttribute("hotel", servicioHotel.obtenerHotelporId(id));
        return"/editarHotel.html";}

    //Peticion post para enviar la info cambiada del hotel
    @PostMapping("/editar/{id}")
    public String actualizarHotel(@PathVariable Integer id, @ModelAttribute("hotel") Hotel hotel, Model modelo){
        Hotel hotelexistente = servicioHotel.obtenerHotelporId(id);
        hotelexistente.setId(id);
        hotelexistente.setNombre(hotel.getNombre());
        hotelexistente.setPais(hotel.getPais());
        hotelexistente.setCiudad(hotel.getCiudad());
        hotelexistente.setDireccion((hotel.getDireccion()));
        hotelexistente.setEstrellas(hotel.getEstrellas());
        hotelexistente.setMultilengua(hotel.getMultilengua());
        hotelexistente.setTerraza(hotel.getTerraza());
        hotelexistente.setPiscina(hotel.getPiscina());
        hotelexistente.setPatio(hotel.getPatio());
        hotelexistente.setEspectaculos(hotel.getEspectaculos());
        hotelexistente.setComedor(hotel.getComedor());
        hotelexistente.setTours(hotel.getTours());
        hotelexistente.setParking((hotel.getParking()));
        hotelexistente.setS_transporte(hotel.getS_transporte());
        hotelexistente.setS_habitacion(hotel.getS_habitacion());
        hotelexistente.setMascotas(hotel.getMascotas());
        hotelexistente.setTelefono(hotel.getTelefono());
        hotelexistente.setAccesibilidad(hotel.getAccesibilidad());
        hotelexistente.setHc_recepcion(hotel.getHc_recepcion());
        hotelexistente.setHf_recepcion(hotel.getHf_recepcion());
        hotelexistente.setWifi(hotel.getWifi());
        hotelexistente.setCancelacion_g(hotel.getCancelacion_g());
        servicioHotel.actualizarHotel(hotelexistente);
        chequearBoolean(hotelexistente);
        return "redirect:/usuario/inicio";

    }

    private void chequearBoolean(Hotel h){
        h.setTerraza(h.getTerraza()==null? false:true);
        h.setPiscina(h.getPiscina()==null? false:true);
        h.setPatio(h.getPatio()==null? false:true);
        h.setEspectaculos(h.getEspectaculos()==null? false:true);
        h.setComedor(h.getComedor()==null? false:true);
        h.setTours(h.getTours()==null? false:true);
        h.setParking(h.getParking()==null? false:true);
        h.setS_transporte(h.getS_transporte()==null? false:true);
        h.setS_habitacion(h.getS_habitacion()==null? false:true);
        h.setMascotas(h.getMascotas()==null? false:true);
        h.setAccesibilidad(h.getAccesibilidad()==null? false:true);
        h.setWifi(h.getWifi()==null? false:true);
        h.setCancelacion_g(h.getCancelacion_g()==null? false:true);
        h.setMultilengua(h.getMultilengua()==null? false:true);
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
        habitacionexistente.setC_individual(hab.getC_individual());
        habitacionexistente.setC_doble(hab.getC_doble());
        habitacionexistente.setPrecio_base(hab.getPrecio_base());
        habitacionexistente.setBano(hab.getBano());
        habitacionexistente.setVistas(hab.getVistas());
        habcontroller.chequearBooleanHabitacion(habitacionexistente);
        servicio.actualizarHabitacion(habitacionexistente);

        return "redirect:/hotel/habitacion";
    }

    //Eliminar habitacion
    @DeleteMapping("/habitacion/{id}")
    public String eliminarHab(@PathVariable Integer id){
        servicio.eliminarHabitacion(id);
        return "redirect:/hotel/habitacion";
    }

    @Autowired
    private HabitacionRepository repository;

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HabitacionServiceImp servicioHab;

    @GetMapping("/{id}")
    public String filtrarHabitaciones(@PathVariable(value = "id") Integer id, Model model){

        Hotel hotel = hotelRepository.findHotelById(id);
        model.addAttribute("hotel", hotel);

        List<Habitacion> habitaciones = repository.findAllById(id);
        model.addAttribute("habitaciones", habitaciones);



        return "/hotel.html";
    }

    @PostMapping("/{id_hab}")
    public String filtrarHotel(@PathVariable(value = "id_hab") Integer id_hab, Model modelo) {
        Habitacion h = servicioHab.obtenerHabitacionporId(id_hab);
        modelo.addAttribute("h", h);

        return "/hotelesBusqueda.html";

    }
}
