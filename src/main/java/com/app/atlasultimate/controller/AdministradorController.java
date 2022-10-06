package com.app.atlasultimate.controller;

import com.app.atlasultimate.controller.DTO.AdministradorRegistroDTO;
import com.app.atlasultimate.model.Administrador;
import com.app.atlasultimate.service.AdministradorService;
import com.app.atlasultimate.service.AdministradorServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
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


    @Autowired
    private AdministradorServiceImpl adminService;


    @ModelAttribute("administrador")
    public AdministradorRegistroDTO retornarNuevoAdmin (){
            return new AdministradorRegistroDTO();
    }

    @GetMapping("Registro")
    public String regis(){
        return "/registro_admin.html";
    }
    @PostMapping
    public String registrarCuentaAdmin(@ModelAttribute("administrador") AdministradorRegistroDTO adminDTO){
        adminService.save(adminDTO);
        return "redirect:/Registro?exito";
    }

    }
