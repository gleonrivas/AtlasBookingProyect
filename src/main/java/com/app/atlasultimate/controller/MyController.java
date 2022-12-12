package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.HotelBusquedaDTO;
import com.app.atlasultimate.model.Hotel;
import com.app.atlasultimate.model.Rol;
import com.app.atlasultimate.model.Usuario;
import com.app.atlasultimate.repository.HotelRepository;
import com.app.atlasultimate.repository.UsuarioRepository;
import com.app.atlasultimate.security.Oauth2User;
import com.app.atlasultimate.service.HotelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.SchemaMapping;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


import javax.servlet.http.HttpSession;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

@Controller("")
public class MyController {


    @GetMapping("fallo")
    public String falloBusqueda(){
        return "/errorBusqueda.html";
    }

    @Autowired
    private HotelRepository hotelRepository;

    @Autowired
    private HotelService hotelService;

    @Autowired
    private UsuarioRepository usuarioRepository;


    @ModelAttribute("hotel")
    public HotelBusquedaDTO devolverNuevoHotelDTO() {
        return new HotelBusquedaDTO();
    }

    @GetMapping("")
    public String index(Model model) {
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

        model.addAttribute("rol", rol.toString());
        String fecha = LocalDate.now().toString();
        String fecha2 = LocalDate.now().plusDays(1).toString();
        model.addAttribute("fecha", fecha);
        model.addAttribute("fecha2", fecha2);

        return "/index.html";
    }

    @PostMapping("")
    public String filtrarHotel(@ModelAttribute("hotel") HotelBusquedaDTO busquedaDTO, Model model,
                               @ModelAttribute("fecha_inicio") String fechaInicio,
                               @ModelAttribute("fecha_fin") String fechaFin) {

        String fecha1 = fechaInicio;
        String fecha2 = fechaFin;
        List<Hotel> hoteles = hotelService.buscadorcompleto(busquedaDTO.getFecha_inicio(),
                busquedaDTO.getFecha_fin(), busquedaDTO.getCiudad(), busquedaDTO.getN_max_personas());
        model.addAttribute("hoteles", hoteles);
        HotelBusquedaDTO hotelDTO = new HotelBusquedaDTO(busquedaDTO.getFecha_inicio(),
                busquedaDTO.getFecha_fin(), busquedaDTO.getCiudad(), busquedaDTO.getN_max_personas());
        model.addAttribute("hotelDTO", hotelDTO);
        model.addAttribute("fecha_inicio", busquedaDTO.getFecha_inicio());
        model.addAttribute("fecha_fin", busquedaDTO.getFecha_fin());

        if (busquedaDTO.getFecha_inicio().equals("") || busquedaDTO.getFecha_fin().equals("")){
            return "redirect:/?falloFecha";
        }else if(LocalDate.parse(busquedaDTO.getFecha_inicio()).equals(LocalDate.parse(busquedaDTO.getFecha_fin()))){
            return "redirect:/?fallo2";
        }else if(LocalDate.parse(busquedaDTO.getFecha_inicio()).isAfter(LocalDate.parse(busquedaDTO.getFecha_fin()))){
            return "redirect:/?fallo";
        }else if (hoteles.isEmpty()){
            return "redirect:/fallo";
        }else {
            model.addAttribute("ciudadhotel", hoteles.get(0).getCiudad());
            return "/hotelesBusqueda.html";
        }


    }




}

