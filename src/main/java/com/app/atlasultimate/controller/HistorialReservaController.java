package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.Pago;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Registro;
import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.ReservaRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.security.Oauth2User;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class HistorialReservaController {
    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    ReservaRepository reservaRepository;

    @Autowired
    private HotelService servicioHotel;

    @ModelAttribute
    private Pago pago(){
        return new Pago();
    }

    @GetMapping("historial")
    public String obtenerHistorialReserva(@RequestParam(value = "id_hab") Integer idHab,
                                          Model model){

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        Rol rol = Rol.anonimo;
        Oauth2User oauth2User = null;
        try {
            if (usuario == null) {
                oauth2User = (Oauth2User) auth.getPrincipal();
                if (oauth2User != null) {
                    if (usuarioRepository.findTopByEmail(oauth2User.getEmail()) == null) {
                        Usuario insertUser = new Usuario();
                        insertUser.setNombre(oauth2User.getFullName());
                        insertUser.setEmail(oauth2User.getEmail());
                        insertUser.setRol(Rol.usuario);
                        usuario = usuarioRepository.save(insertUser);
                        rol = usuario.getRol();

                    } else {
                        usuario = usuarioRepository.findTopByEmail(oauth2User.getEmail());
                        rol = usuario.getRol();

                    }
                }


            }else {
                rol = usuario.getRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        Registro registro = reservaRepository.findFirstByUsuarioOrderByIdDesc(usuario);
        Integer idHotel = servicioHotel.obtenerIdHotel(idHab);
        Hotel hotel = servicioHotel.obtenerHotelporId(idHotel);
        model.addAttribute("usuario", usuario);
        model.addAttribute("hotel", hotel);
        model.addAttribute("registro" ,registro);
        return "/HistorialReserva.html";

    }



    @PostMapping("historial")
    public String aceptarReserva(@ModelAttribute("pago") Pago pago){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Usuario usuario = usuarioRepository.findTopByEmail(auth.getName());
        Rol rol = Rol.anonimo;
        Oauth2User oauth2User = null;
        try {
            if (usuario == null) {
                oauth2User = (Oauth2User) auth.getPrincipal();
                if (oauth2User != null) {
                    if (usuarioRepository.findTopByEmail(oauth2User.getEmail()) == null) {
                        Usuario insertUser = new Usuario();
                        insertUser.setNombre(oauth2User.getFullName());
                        insertUser.setEmail(oauth2User.getEmail());
                        insertUser.setRol(Rol.usuario);
                        usuario = usuarioRepository.save(insertUser);
                        rol = usuario.getRol();

                    } else {
                        usuario = usuarioRepository.findTopByEmail(oauth2User.getEmail());
                        rol = usuario.getRol();

                    }
                }


            }else {
                rol = usuario.getRol();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        Registro registro = reservaRepository.findFirstByUsuarioOrderByIdDesc(usuario);


        if (pago.getDetalles().equals("COMPLETED")){
            reservaRepository.updateById(registro.getId());
            return "/exitoReserva.html";

        }else {
            return "falloReserva.html";
        }


    }

}
