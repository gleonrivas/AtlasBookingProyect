package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller("")
public class MyController {

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;


    @ModelAttribute("hotel")
    public HotelBusquedaDTO devolverNuevoHotelDTO(){
        return new HotelBusquedaDTO();

    }

    @GetMapping("")
    public String index(HttpSession session){
        return "/index.html";
    }
    @PostMapping("")
    public String filtrarHotel(@ModelAttribute("hotel") HotelBusquedaDTO busquedaDTO, Model model, Model hotelciudad) {

        List<Hotel> hoteles = hotelRepository.findAllByReservas(busquedaDTO.getFecha_inicio(), busquedaDTO.getFecha_fin(),busquedaDTO.getCiudad(),busquedaDTO.getN_max_personas());
        model.addAttribute("hoteles", hoteles);
        model.addAttribute("ciudadhotel", hoteles.get(0).getCiudad());

        return "/hotelesBusqueda.html";

    }
    

}

