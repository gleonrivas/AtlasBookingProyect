package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Habitacion;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Pension;
import com.app.atlasultimate.repository.PensionRepository;
import com.app.atlasultimate.service.HabitacionServiceImp;
import com.app.atlasultimate.service.HotelServiceImp;
import com.app.atlasultimate.service.PensionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("reserva")
public class ReservaController {
    @Autowired
    private HotelServiceImp servicioHotel;
    @Autowired
    private HabitacionServiceImp servicioHab;

    @Autowired
    private PensionService servicioPension;


    @GetMapping("datos/{id_hab}")
    public String registroreserva(@PathVariable(value = "id_hab") Integer idHab,
                                  Model modelHo, Model modelhab, Model modelPension) {
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        Integer id_pension = servicioPension.pensionporIdHotel(hotel.getId());
        Pension pension = servicioPension.pensionporId(id_pension);
        Habitacion habitacion = servicioHab.obtenerHabitacionporId(idHab);

        modelHo.addAttribute("hotel", hotel);
        modelhab.addAttribute("habitacion", habitacion);
        modelPension.addAttribute("pension", pension);


        return "/reservas.html";
    }


}
