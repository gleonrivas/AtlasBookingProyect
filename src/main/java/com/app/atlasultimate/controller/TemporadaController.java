package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Temporada;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HabitacionService;
import com.app.atlasultimate.service.TemporadaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("temporada")
public class TemporadaController {

    @Autowired
    HabitacionService habitacionService;

    @Autowired
    TemporadaService temporadaService;

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("crear/")
    public String crearTemporada(@RequestParam(value="id_habitacion") Integer id_habitacion, Model model){
        Habitacion hab = habitacionService.obtenerHabitacionporId(id_habitacion);
        Temporada temporada = new Temporada();
        Integer id_hotel = hotelRepository.findHotelByIdHab(id_habitacion);
        Hotel hotel = hotelRepository.findHotelById(id_hotel);

        model.addAttribute("habitacion", hab);
        model.addAttribute("temporada", temporada);
        model.addAttribute("id_habitacion", id_habitacion);
        model.addAttribute("id_hotel", id_hotel);
        return "/crearTemporada.html";
    }

    @PostMapping("crear/")
    public String guardarTemporada(@ModelAttribute(value="id_habitacion") Integer id_habitacion,
                                   @ModelAttribute(value="habitacion") Habitacion habitacion,
                                   @ModelAttribute(value= "temporada") Temporada temporada,
                                   @ModelAttribute (value= "hotel") Hotel hotel){

        temporadaService.guardarTemporada(temporada);
        Temporada temp = temporadaService.traerUltimaTemporada();
        habitacionService.cambiarIdTemporada(temp.getId(), id_habitacion);


        return "/crearTemporada.html";
    }
}
