package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.repository.PensionRepository;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("pension")
public class PensionesController {
    @Autowired
    HotelService hotelService;
    @Autowired
    PensionRepository pensionRepository;

    @GetMapping("crear/")
    public String crearReserva (@RequestParam (value="id") Integer id, Model model){
        Pension pension = new Pension();
        Hotel hotel = hotelService.buscarHotel(id);
        pension.setId_hotel(hotel);
        model.addAttribute("pension", pension);

        return "/crearPension.html";
    }

    @PostMapping("crear/")
    public String guardarPension(@ModelAttribute (value="id") Integer id,
                                 @ModelAttribute(value="pension") Pension pension){
        chequearBoolean(pension);
        pensionRepository.save(pension);
        return "/crearPension.html";
    }

    private void chequearBoolean(Pension pension) {
        pension.setBooleanAd(pension.getBooleanAd()== null ? false : true);
        pension.setBooleanMp(pension.getBooleanMp()== null ? false : true);
        pension.setBooleanPc(pension.getBooleanPc()== null ? false : true);
        pension.setBooleanSa(pension.getBooleanSa()== null ? false : true);
    }

}

