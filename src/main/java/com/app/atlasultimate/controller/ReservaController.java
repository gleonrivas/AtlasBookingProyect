package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
@Controller
@RequestMapping("reserva")
public class ReservaController {
    @Autowired
    private HotelServiceImp servicioHotel;
    @Autowired
    private HabitacionServiceImp servicioHab;

    @GetMapping("datos/{id_hab}")
    public String registroreserva(@PathVariable (value = "id_hab") Integer idHab,
                                  Model modelHo, Model modelhab){
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        Habitacion habitacion = servicioHab.obtenerHabitacionporId(idHab);
        modelHo.addAttribute("hotel",hotel);
        modelhab.addAttribute("habitacion", habitacion);

        return "/reservas.html";
    }
}
