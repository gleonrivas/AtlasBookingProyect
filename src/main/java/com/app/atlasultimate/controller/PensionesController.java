package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pension")
public class PensionesController {
    @Autowired
    HotelService hotelService;

    @GetMapping("crear/{id}")
    public String crearReserva (@PathVariable Integer id, Model model){
        Pension pension = new Pension();
        Hotel hotel = hotelService.buscarHotel(id);
        pension.setId_hotel(hotel);
        model.addAttribute("pension", pension);


        return "/crearPension.html";
    }

}
