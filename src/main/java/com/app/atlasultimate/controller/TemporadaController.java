package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.TemporadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("temporada")
public class TemporadaController {

    @Autowired
    HabitacionService habitacionService;

    @Autowired
    TemporadaService temporadaService;

    @GetMapping("crear/{id_habitacion}")
    public String crearTemporada (@PathVariable Integer id_habitacion, Model model){
        Habitacion hab = habitacionService.obtenerHabitacionporId(id_habitacion);
        Temporada temporada = new Temporada();

        model.addAttribute("habitacion", hab);
        model.addAttribute("temporada", temporada);
        model.addAttribute("id_habitacion", id_habitacion);
        return "/crearTemporada.html";
    }

    @PostMapping("crear/{id_habitacion}")
    public String guardarTemporada(
                                   @ModelAttribute("habitacion") Habitacion habitacion,
                                   @ModelAttribute("temporada") Temporada temporada){
       temporadaService.guardarTemporada(temporada);
       Temporada temp = temporadaService.traerUltimaTemporada();
       habitacionService.cambiarIdTemporada(temp.getId(), habitacion.getId());


       return "redirect:/hotel/habitacion/{id_habitacion}";
    }
}
