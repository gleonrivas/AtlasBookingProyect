package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("habitacion")
public class HabitacionController {
    @Autowired
    private HabitacionServiceImp servicio;
    @Autowired
    private HotelServiceImp hotelServicio;

    //MUESTRA FORMULARIO CREACION DE HABITACION
    @GetMapping("nueva/{id}")
    public String crearHabitacion( @PathVariable Integer id, Model model, Model mod2) {
        Habitacion habitacion = new Habitacion();
        Hotel hotel = hotelServicio.buscarHotel(id);
        model.addAttribute("habitacion", habitacion);
        mod2.addAttribute("hotel", hotel);

        return "/crearhabitacion.html";
    }

    //GUARDAR DATOS HABITACION EN BBDD
    @PostMapping("nueva/{id}")
    public String guardarHabitacion( @ModelAttribute("habitacion") Habitacion hab, @ModelAttribute("hotel") Hotel hot ) {
        chequearBooleanHabitacion(hab);
        hab.setHotel(hot);
        servicio.guardarHabMultiple(hab.getNum_habitaciones_iguales(), hab);
        return "redirect:/hotel/habitacion/{id}";
    }

    //convertir boolean en true o false
    public void chequearBooleanHabitacion(Habitacion h) {
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
    }
}