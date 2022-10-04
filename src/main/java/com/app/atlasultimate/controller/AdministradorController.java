package com.app.atlasultimate.controller;

import com.app.atlasultimate.model.Administrador;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("Admin")
public class AdministradorController {

    @GetMapping("Inicio")
    public String inicio(){
        return "/InicioAdministrador.html";
    }

    @GetMapping("Servicios")
    public String servs(){
        return "/ofertas_servicios_admin.html";
    }

    @GetMapping("Informes")
    public String informes(){
        return "/InformesAdmin.html";
    }
    @GetMapping("Facturas")
    public String facturas(){
        return "/FacturasAdmin.html";
    }

    @GetMapping("Registro")
    public String regis(Model model){
        model.addAttribute("administrador", new Administrador());
        return "/registro_admin.html";
    }

    @RequestMapping(value="/crear", method= RequestMethod.POST)
    public ModelAndView form(Administrador administrador){
        ModelAndView model = new ModelAndView();
        model.addObject("administrador", administrador);
        return model;

    }
    }
