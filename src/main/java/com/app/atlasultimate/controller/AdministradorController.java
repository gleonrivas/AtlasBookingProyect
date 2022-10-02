package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Administrador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Controller
@RestController
public class AdministradorController {
    @GetMapping("/registeradmin")
    public String showForm(Model model) {
        Administrador nuevo = new Administrador();
        model.addAttribute("Administrador", nuevo);


        return "RegistroInicioSesionHotel";
    }
}
