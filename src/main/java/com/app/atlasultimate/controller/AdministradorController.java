package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Administrador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RestController
@RequestMapping("/Admin")
public class AdministradorController {
    @RequestMapping("/RegistroAdministrador")
    public String showUserForm(Model model){
        model.addAttribute("administrador", new Administrador());
        return "/RegistroeInicioSesionHotel.html";
    }
    @RequestMapping("/create")
    public ModelAndView createUser(Administrador admin, BindingResult result) {
        ModelAndView model = new ModelAndView();
        model.addObject("administrador", admin);
        model.setViewName(result.hasErrors() ? "userForm" : "userReady");
        return model;
    }

    }
