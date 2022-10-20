package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.registro.ClienteRegistroDTO;
import com.app.atlasultimate.repository.HabitacionRepository;
import com.app.atlasultimate.repository.HotelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.websocket.server.PathParam;
import java.util.List;



@Controller
public class HabitacionController {

    @Autowired
    private HabitacionRepository repository;

    @Autowired
    private HotelRepository hotelRepository;


    @GetMapping("/hotel/{id}")
    public String filtrarHabitaciones(@PathVariable(value = "id") Integer id, Model model){
        List<Habitacion> habitaciones = repository.findAllById(id);
        model.addAttribute("habitaciones", habitaciones);
        return "/hotel.html" ;
    }

    @PostMapping("/hotel/{id}")
    public String filtrarHotel(@PathVariable(value = "id") Integer id, Model model) {

        Hotel hotel = hotelRepository.findHotelById(id);
        model.addAttribute("hotel", hotel);
        return "/hotelesBusqueda.html";

    }
}
