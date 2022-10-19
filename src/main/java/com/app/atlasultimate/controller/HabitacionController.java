package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.service.HabitacionServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("habitacion")
public class HabitacionController {
    @Autowired
    private HabitacionServiceImp servicio;

    //MUESTRA FORMULARIO CREACION DE HABITACION
    @GetMapping("nueva")
    public String crearHabitacion(Model model) {
        Habitacion hab = new Habitacion();
        model.addAttribute("habitacion", hab);
        return "/crearhabitacion.html";
    }

    //GUARDAR DATOS HABITACION EN BBDD
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
        h.setBano(h.getBano() == null ? false : true);
        h.setVistas(h.getVistas() == null ? false : true);
    }
}