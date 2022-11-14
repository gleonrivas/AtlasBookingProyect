package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.repository.HotelRepository;
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

    @Autowired
    HotelRepository hotelRepository;

    @GetMapping("crear/")
    public String crearReserva (@RequestParam (value="id") Integer id, Model model){
        Pension pension = new Pension();

        model.addAttribute("pension", pension);
        model.addAttribute("id", id);

        return "/crearPension.html";
    }

    @PostMapping("crear/")
    public String guardarPension(@ModelAttribute (value="id") Integer id,
                                 @ModelAttribute(value="pension") Pension pension){

        if (!pension.getBooleanSa()) {
            pension.setSa(0.0);
        }
        if (!pension.getBooleanAd()){
            pension.setAd(0.0);
        }
        if (!pension.getBooleanMp()){
            pension.setMp(0.0);
        }
        if (!pension.getBooleanPc()){
            pension.setPc(0.0);
        }

        Hotel hotel = hotelRepository.findTopById(id);
        Integer id_pension = pensionRepository.idUltimaPension() +1;
        pension.setId(id_pension);
        pension.setId_hotel(hotel);
        pensionRepository.save(pension);
        return "/crearPension.html";
    }


}

