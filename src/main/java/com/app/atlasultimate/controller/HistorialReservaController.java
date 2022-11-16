package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistorialReservaController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    private HotelService servicioHotel;

    @GetMapping("historial")
    public String obtenerHistorialReserva(@RequestParam(value = "id_hab") Integer idHab,
                                          Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        Registro registro = reservaRepository.findFirstByUsuarioOrderByIdDesc(usuario);
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        model.addAttribute("hotel", hotel);
        model.addAttribute("registro" ,registro);
        return "/HistorialReserva.html";
    }

}
