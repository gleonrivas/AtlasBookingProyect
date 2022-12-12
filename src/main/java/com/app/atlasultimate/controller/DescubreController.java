package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.DescubreDTO;
import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.time.LocalDate;
import java.util.List;
import java.util.Random;

@Controller
public class DescubreController {

    @Autowired
    HotelRepository hotelRepository;

    @Autowired
    HotelService service;

    @ModelAttribute("hotelSinCiudad")
    public DescubreDTO devolverNuevoHotelDTO() {
        return new DescubreDTO();
    }

    @GetMapping("descubre")
    public String mostrarDescubre(Model model){

        String fecha = LocalDate.now().toString();
        String fecha2 = LocalDate.now().plusDays(1).toString();

        model.addAttribute("fecha", fecha);
        model.addAttribute("fecha2", fecha2);
        model.addAttribute("hoteles", hotelRepository.mejoresValorados());
        model.addAttribute("hotelesLujosos", hotelRepository.mejoresValoradosPorHotel());
        List<Hotel> hotelList = hotelRepository.findAll();
        Random rand = new Random();
        Hotel hotelRandom = hotelList.get(rand.nextInt(hotelList.size()));
        model.addAttribute("hotelesCiudad", hotelRepository.findAllByCiudad(hotelRandom.getCiudad()));
        model.addAttribute("ciudadRandom" , hotelRandom.getCiudad());


        return "/descubre.html";
    }

    @PostMapping("descubre")
    public String filtrarDescubre(@ModelAttribute("hotelSinCiudad") DescubreDTO busquedaDTO, Model model,
                                  @ModelAttribute("fecha_inicio") String fechaInicio,
                                  @ModelAttribute("fecha_fin") String fechaFin) {

        String fecha1 = fechaInicio;
        String fecha2 = fechaFin;
        List<Hotel> hotelesReview = service.buscadorcompletoReview(busquedaDTO.getFecha_inicio(),
                busquedaDTO.getFecha_fin(), busquedaDTO.getN_max_personas());
        model.addAttribute("hoteles", hotelesReview);
        List<Hotel> hotelesPorHotel = service.buscadorcompletoHotel(busquedaDTO.getFecha_inicio(),
                busquedaDTO.getFecha_fin(), busquedaDTO.getN_max_personas());
        model.addAttribute("hotelesLujosos", hotelesPorHotel);
        List<Hotel> hotelList = hotelRepository.findAll();

        Random rand = new Random();
        Hotel hotelRandom = hotelList.get(rand.nextInt(hotelList.size()));
        model.addAttribute("hotelesCiudad", hotelRepository.findAllByCiudad(hotelRandom.getCiudad()));
        model.addAttribute("ciudadRandom" , hotelRandom.getCiudad());

        DescubreDTO hotelDTO = new DescubreDTO(busquedaDTO.getFecha_inicio(),
                busquedaDTO.getFecha_fin(), busquedaDTO.getN_max_personas());
        model.addAttribute("hotelDTO", hotelDTO);
        model.addAttribute("fecha_inicio", busquedaDTO.getFecha_inicio());
        model.addAttribute("fecha_fin", busquedaDTO.getFecha_fin());

        if (busquedaDTO.getFecha_inicio().equals("") || busquedaDTO.getFecha_fin().equals("")){
            return "redirect:/descubre?falloFecha";
        }else if(LocalDate.parse(busquedaDTO.getFecha_inicio()).equals(LocalDate.parse(busquedaDTO.getFecha_fin()))){
            return "redirect:/descubre?fallo2";
        }else if(LocalDate.parse(busquedaDTO.getFecha_inicio()).isAfter(LocalDate.parse(busquedaDTO.getFecha_fin()))){
            return "redirect:/descubre?fallo";
        }else if (hotelesReview.isEmpty() && hotelesPorHotel.isEmpty()){
            return "redirect:/fallo";
        }else {
            return "/descubreBusqueda.html";
        }


    }

}
