package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.registro.ClienteRegistroDTO;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HabitacionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;



@Controller
@RequestMapping("habitacion")
public class HabitacionController {

    @Autowired
    private HabitacionServiceImp servicio;

    @GetMapping("nueva")
    public String crearHabitacion(Model model) {
        Habitacion hab = new Habitacion();
        model.addAttribute("habitacion", hab);
        return "/crearhabitacion.html";
    }

    //crear habitacion
    @PostMapping("nueva")
    public String guardarHabitacion(@ModelAttribute("habitacion") Habitacion hab) {
        chequearBooleanHabitacion(hab);
        for (int i = 0; i <= hab.getNum_habitaciones_iguales(); i++) {
            servicio.guardarhab(hab);
        }
        return "redirect:/hotel/habitacion";
    }

    //convertir boolean en true o false
    public void chequearBooleanHabitacion(Habitacion h) {
        h.setDisponible(h.getDisponible() == null ? true : false);
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
        h.setWifi(h.getWifi() == null ? false : true);}



        @Autowired
        private HabitacionRepository repository;

    @Autowired
    private HotelRepository hotelRepository;


    @RequestMapping("/hotel/{id}")
    public String filtrarHabitaciones(@PathVariable(value = "id") Integer id, Model model){
        List<Habitacion> habitaciones = repository.findAllById(id);
        model.addAttribute("habitaciones", habitaciones);
        return "/hotel.html";
    }

    @PostMapping("/hotel/{id}")
    public String filtrarHotel(@PathVariable(value = "id") Integer id, Model model) {

        Hotel hotel = hotelRepository.findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "/hotelesBusqueda.html";

    }
}
