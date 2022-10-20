package com.app.atlasultimate.controller;

import com.app.atlasultimate.service.HotelService;
import com.app.atlasultimate.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RequestMapping("hotel")
public class HotelController {

    @Autowired
    private HotelRepository hotelRepository;

    @ModelAttribute("hotel")
    public HotelBusquedaDTO devolverNuevoHotelDTO(){
        return new HotelBusquedaDTO();
    }

    @GetMapping("")
    public String index() {
        return "/index.html";
    }

    @PostMapping("")
    public String filtrarHotel(@ModelAttribute("hotel") HotelBusquedaDTO busquedaDTO, Model model) {

        List<Hotel> hoteles = hotelRepository.findAllByReservas(busquedaDTO.getFecha_inicio(), busquedaDTO.getFecha_fin(),busquedaDTO.getCiudad(),busquedaDTO.getTipo_cama());
        model.addAttribute("hoteles", hoteles);
        return "/hotelesBusqueda.html";

    }



    @Autowired
    private HabitacionServiceImp servicio;

    @Autowired
    private HotelServiceImp servicioHotel;

    @Autowired
    private HabitacionController habcontroller;


    @GetMapping("habitacion")
    public String leerHabitaciones(Model model){
        model.addAttribute("habitaciones", servicio.listarHabitacion());
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
    chequearBoolean(hotelexistente);
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
        habitacionexistente.setNum_camas(hab.getNum_camas());
        habitacionexistente.setNum_camas(hab.getNum_camas());
        habitacionexistente.setTipo_cama(hab.getTipo_cama());
        habitacionexistente.setBano(hab.getBano());
        habitacionexistente.setWifi(hab.getWifi());
        habitacionexistente.setPrecio_base(hab.getPrecio_base());
        habitacionexistente.setVistas(hab.getVistas());
        habcontroller.chequearBooleanHabitacion(habitacionexistente);
        servicio.actualizarHabitacion(habitacionexistente);

        return "redirect:/hotel/habitacion";
    }

    //Eliminar habitacion
    @GetMapping("/habitacion/{id}")
    public String eliminarHab(@PathVariable Integer id){
        servicio.eliminarHabitacion(id);
        return "redirect:/hotel/habitacion";
    }

    //convertir boolean en true o false
    private void chequearBoolean(Hotel h){
        h.setTerraza(h.getTerraza()==null? false:true);
        h.setPiscina(h.getPiscina()==null? false:true);
        h.setPatioInterior(h.getPatioInterior()==null? false:true);
        h.setEspectaculos(h.getEspectaculos()==null? false:true);
        h.setComedor(h.getComedor()==null? false:true);
        h.setTours(h.getTours()==null? false:true);
        h.setAparcamiento(h.getAparcamiento()==null? false:true);
        h.setServicioTransporte(h.getServicioTransporte()==null? false:true);
        h.setRecepcion(h.getRecepcion()==null? false:true);
        h.setServicioLimpieza(h.getServicioLimpieza()==null? false:true);
        h.setServicioHabitaciones(h.getServicioHabitaciones()==null? false:true);
        h.setMascotas(h.getMascotas()==null? false:true);
        h.setAccesibilidad(h.getAccesibilidad()==null? false:true);
        h.setWifi(h.getWifi()==null? false:true);
        h.setCancelacionGratuita(h.getCancelacionGratuita()==null? false:true);
    }

}
