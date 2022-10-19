package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.registro.ClienteRegistroDTO;
import com.app.atlasultimate.repository.HabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;



@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepository repository;



    @RequestMapping("/hotel/{id}")
    public String filtrarHabitaciones(@PathVariable(value = "id") Integer id, Model model){
        List<Habitacion> habitaciones = repository.findAllById(id);
        model.addAttribute("habitaciones", habitaciones);
        return "/hotel.html" ;

    }
}
